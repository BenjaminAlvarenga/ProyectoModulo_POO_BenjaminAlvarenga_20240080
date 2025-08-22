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

    @GetMapping("/consultarLibros")
    public List<librosDTO> getLibros(){ return service.getAllLibros(); }

    @PostMapping("/ingresarLibro")
    public ResponseEntity<?> insertLibro(@Valid @RequestBody librosDTO dto, HttpServletRequest request){
        try{
            librosDTO response = service.insertLibro(dto);
            if(response == null){
                return ResponseEntity.badRequest().body(Map.of(
                        "status", "Inserción incorrecta",
                        "errorType", "VALIDATION_ERROR",
                        "message", "Datos no validos"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "data", response
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "error",
                    "message", "Error el ingresar el libro",
                    "detail", e.getMessage()
            ));
        }
    }

    @PutMapping("/updateLibro/{id}")
    public ResponseEntity<?> updateLibro(@PathVariable Long id, @Valid @RequestBody librosDTO json, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String,String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        try{
            librosDTO dto = service.updateLibro(id, json);
            return ResponseEntity.ok(json);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "Error", "Datos duplicados",
                    "Campo", e.getMessage()
            ));
        }
    }

    @DeleteMapping("/deleteLibro/{id}")
    public ResponseEntity<?> deleteLibro(@PathVariable Long id){
        try{
            if(!service.deleteLibro(id)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Mensaje error", "Libro no encontrdao").body(Map.of(
                        "Error", "Not Found",
                        "Message", "Libro no encontrado",
                        "timestamp", Instant.now().toString()
                ));
            }
            return ResponseEntity.ok().body(Map.of(
                    "status", "Proceso Completado",
                    "message", "Libro eliminado exitosamente"
            ));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",
                    "message", "Error al eliminar el libro",
                    "detail", e.getMessage()
            ));
        }
    }
}
