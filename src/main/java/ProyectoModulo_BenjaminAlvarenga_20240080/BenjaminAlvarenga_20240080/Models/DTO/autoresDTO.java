package ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @EqualsAndHashCode @ToString
public class autoresDTO {

    private Long id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    private String nacionalidad;
    private Date fecha_nacimiento;
}
