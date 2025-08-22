package ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Controllers;

import ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Models.DTO.autoresDTO;
import ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Service.autoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class autoresController {

    @Autowired
    autoresService service;

    @GetMapping("/getAutores")
    public List<autoresDTO> getAutores(){return service.getAllAutores();}
}
