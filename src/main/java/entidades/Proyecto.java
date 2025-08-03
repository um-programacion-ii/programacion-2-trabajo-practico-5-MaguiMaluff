package entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "proyectos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaFina;

    @ManyToMany(mappedBy = "proyectos")
    private Set<Empleado> empleados = new HashSet<>();

}
