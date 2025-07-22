package entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {
    private Long id;
    private String nombre;
    private String  descripcion;
    private List<Empleado> empleados = new ArrayList<>();

}
