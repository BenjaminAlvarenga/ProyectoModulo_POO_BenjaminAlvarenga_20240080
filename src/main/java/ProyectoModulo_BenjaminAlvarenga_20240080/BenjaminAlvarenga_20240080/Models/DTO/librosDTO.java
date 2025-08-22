package ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter@EqualsAndHashCode @ToString
public class librosDTO {

    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String isbn;
    private Long año_publicacion;
    private String genero;
    private Long autor_id;
}
