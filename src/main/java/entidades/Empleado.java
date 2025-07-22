package entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private LocalDate fechaContratacion;
    private BigDecimal salario;
    private Departamento departamento;

}
