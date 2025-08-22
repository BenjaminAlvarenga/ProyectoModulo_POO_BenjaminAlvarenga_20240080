package ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Controllers;

import ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Models.DTO.librosDTO;
import ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Service.librosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/libros")
public class librosController {

    @Autowired
    librosService service;

    //Metodo de consulta
    @GetMapping("/getLibros")
    //Nomas pide la informacion de los libros
    public List<librosDTO> getLibros(){ return service.getAllLibros(); }

    //Metodo de insertar
    @PostMapping("/insertLibro")
    public ResponseEntity<?> insertLibro(@Valid @RequestBody librosDTO dto, HttpServletRequest request){
        try{
            //Se lleva los datos al service para que entren a la base
            librosDTO response = service.insertLibro(dto);
            if(response == null){
                //Proceso por si han hecho un bad request y metieron un put o delete
                return ResponseEntity.badRequest().body(Map.of(
                        "status", "Inserción incorrecta",
                        "errorType", "VALIDATION_ERROR",
                        "message", "Datos no validos"
                ));
            }
            //Aqui devuelve que ya se ha creado el JSON y se ha metido a la base
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "data", response
            ));
        }catch (Exception e){
            //Proceso de error interno del servidor, error no controlado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "error",
                    "message", "Error el ingresar el libro",
                    "detail", e.getMessage()
            ));
        }
    }

    //Metodo de Actualizar
    @PutMapping("/updateLibro/{id}")
    public ResponseEntity<?> updateLibro(@PathVariable Long id, @Valid @RequestBody librosDTO json, BindingResult bindingResult){
        //Proceso donde que verifica si el bindingResult tiene errores
        if(bindingResult.hasErrors()){
            Map<String,String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        //Si no tiene, pasa aca a actualizar el libro
        try{
            //Se hace el update en el service que lleva los datos y el id
            librosDTO dto = service.updateLibro(id, json);
            return ResponseEntity.ok(json);
        }catch (IllegalArgumentException e){
            //Por si no se encuentra id para actualizar
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            //Aqui agarra una excepcion por si los datos estan duplicados, o no los cambia
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "Error", "Datos duplicados",
                    "Campo", e.getMessage()
            ));
        }
    }

    //Metodo de eliminar
    @DeleteMapping("/deleteLibro/{id}")
    public ResponseEntity<?> deleteLibro(@PathVariable Long id){
        try{
            //Proceso donde el libro no existe
            if(!service.deleteLibro(id)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Mensaje error", "Libro no encontrdao").body(Map.of(
                        "Error", "Not Found",
                        "Message", "Libro no encontrado",
                        "timestamp", Instant.now().toString()
                ));
            }
            //Aqui el libro si existe y se elimina
            //Proceso de eliminación completa
            return ResponseEntity.ok().body(Map.of(
                    "status", "Proceso Completado",
                    "message", "Libro eliminado exitosamente"
            ));
        }catch (Exception e){
            //Aqui es un proceso donde el servidor no puede eliminar el libro
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",
                    "message", "Error al eliminar el libro",
                    "detail", e.getMessage()
            ));
        }
    }
}
