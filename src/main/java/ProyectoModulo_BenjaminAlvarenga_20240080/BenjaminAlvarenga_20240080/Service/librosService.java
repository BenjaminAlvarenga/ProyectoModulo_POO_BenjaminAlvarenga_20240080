package ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Service;

import ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Entity.librosEntity;
import ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Models.DTO.librosDTO;
import ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Repository.librosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class librosService {

    @Autowired
    librosRepository repo;

    public List<librosDTO> getAllLibros(){
        List<librosEntity> libros = repo.findAll();
        return libros.stream()
                .map(this::convertToLibrosDTO)
                .collect(Collectors.toList());
    }

    private librosDTO convertToLibrosDTO(librosEntity librosEntity) {
        librosDTO dto = new librosDTO();
        dto.setId(librosEntity.getId());
        dto.setTitulo(librosEntity.getTitulo());
        dto.setIsbn(librosEntity.getIsbn());
        dto.setAño_publicacion(librosEntity.getAño_publicacion());
        dto.setGenero(librosEntity.getGenero());
        dto.setAutor_id(librosEntity.getAutor_id());
        return dto;
    }

    private librosEntity convertToLibrosEntity(librosDTO dto){
        librosEntity entity = new librosEntity();
        entity.setId(dto.getId());
        entity.setTitulo(dto.getTitulo());
        entity.setIsbn(dto.getIsbn());
        entity.setAño_publicacion(dto.getAño_publicacion());
        entity.setGenero(dto.getGenero());
        entity.setAutor_id(dto.getAutor_id());
        return entity;
    }

    public librosDTO insertLibro(librosDTO dto){
        if(dto == null || dto.getTitulo() == null || dto.getTitulo().isBlank() || dto.getIsbn() == null ||
        dto.getIsbn().isBlank() || dto.getAño_publicacion() == null || dto.getGenero() == null||
        dto.getGenero().isBlank() || dto.getAutor_id() == null){
            throw new IllegalArgumentException("Todos los campos deben de estar completados");
        }
        try{
            librosEntity entity = convertToLibrosEntity(dto);
            librosEntity save = repo.save(entity);
            return convertToLibrosDTO(save);
        }catch (Exception e){
            log.error("Error al registrar el libro" + e.getMessage());
            throw new IllegalArgumentException("Error al guardar el libro");
        }
    }

    public librosDTO updateLibro(Long id, librosDTO json){
        librosEntity exists = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de libro no encontrado"));
        exists.setTitulo(json.getTitulo());
        exists.setAño_publicacion(json.getAño_publicacion());
        exists.setGenero(json.getGenero());
        exists.setAutor_id(json.getAutor_id());

        librosEntity
    }
}
