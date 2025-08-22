package ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "AUTORES")
@Getter @Setter @EqualsAndHashCode @ToString
public class autoresEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_autor")
    @SequenceGenerator(sequenceName = "seq_autor", name = "seq_autor")
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "NACIONALIDAD")
    private String nacionalidad;
    @Column(name = "FECHA_NACIMIENTO")
    private Date fecha_nacimineto;
}
