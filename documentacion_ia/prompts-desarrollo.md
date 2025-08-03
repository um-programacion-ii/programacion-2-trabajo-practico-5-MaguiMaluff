## 1. Prompts utilizados para crear entidades JPA
   
### No se utilizaron

## 2. Prompts para implementar repositories y services

```text
Necesito un @Repository para la clase Proyecto:
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

```
### Respuesta

```java name=ProyectoRepository.java
@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    Optional<Proyecto> findByNombre(String nombre);

    List<Proyecto> findByFechaInicioAfter(LocalDate fechaInicio);

    List<Proyecto> findByFechaFinaBefore(LocalDate fechaFina);

    List<Proyecto> findByEmpleados_Id(Long empleadoId);
}
```

**Explicación:**
- `findByNombre`: Busca un proyecto por nombre.
- `findByFechaInicioAfter`: Proyectos que empiezan después de una fecha dada.
- `findByFechaFinaBefore`: Proyectos que terminan antes de una fecha dada.
- `findByEmpleados_Id`: Proyectos asociados a un empleado por su ID.

## 3. Prompts para configurar controllers

## 4. Explicación de las decisiones de diseño tomadas