package ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Service;

import ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Entity.autoresEntity;
import ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Models.DTO.autoresDTO;
import ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Repository.autoresRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class autoresService {

    @Autowired
    autoresRepository repo;

    public List<autoresDTO> getAllAutores(){
        List<autoresEntity> autores = repo.findAll();
        return autores.stream()
                .map(this::convertToAutoresDTO)
                .collect(Collectors.toList());
    }

    private autoresDTO convertToAutoresDTO(autoresEntity entity) {
        autoresDTO dto = new autoresDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setNacionalidad(entity.getNacionalidad());
        dto.setFecha_nacimiento(entity.getFecha_nacimineto());
        return dto;
    }
}
