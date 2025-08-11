package ar.edu.um.programacion2.tp5.controladores;

import ar.edu.um.programacion2.tp5.entidades.Empleado;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ar.edu.um.programacion2.tp5.servicios.EmpleadoService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@Validated
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    /**
     * Obtiene la lista de todos los empleados.
     * @return Lista de entidades Empleado.
     */
    @GetMapping
    public List<Empleado> obtenerTodos() {
        return empleadoService.obtenerTodos();
    }

    /**
     * Obtiene un empleado por su ID.
     * @param id ID del empleado a buscar.
     * @return Entidad Empleado correspondiente al ID.
     */
    @GetMapping("/{id}")
    public Empleado obtenerPorId(@PathVariable Long id) {
        return empleadoService.buscarPorId(id);
    }

    /**
     * Crea un nuevo empleado.
     * @param empleado Entidad Empleado a crear.
     * @return Empleado creado.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado crear(@Valid @RequestBody Empleado empleado) {
        return empleadoService.guardar(empleado);
    }

    /**
     * Actualiza un empleado existente por su ID.
     * @param id ID del empleado a actualizar.
     * @param empleado Datos actualizados del empleado.
     * @return Empleado actualizado.
     */
    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
        return empleadoService.actualizar(id, empleado);
    }

    /**
     * Elimina un empleado por su ID.
     * @param id ID del empleado a eliminar.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
    }

    /**
     * Obtiene una lista de empleados que pertenecen a un departamento específico.
     * @param nombre Nombre del departamento.
     * @return Lista de empleados del departamento.
     */
    @GetMapping("/departamento/{nombre}")
    public List<Empleado> obtenerPorDepartamento(@PathVariable String nombre) {
        return empleadoService.buscarPorDepartamento(nombre);
    }

    /**
     * Obtiene una lista de empleados cuyo salario está dentro de un rango específico.
     * @param min Salario mínimo.
     * @param max Salario máximo.
     * @return Lista de empleados en el rango de salario.
     */
    @GetMapping("/salario")
    public List<Empleado> obtenerPorRangoSalario(@RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return empleadoService.buscarPorRangoSalario(min, max);
    }

    /**
     * Calcula el salario promedio de los empleados en un departamento específico.
     * @param departamentoId ID del departamento.
     * @return Salario promedio del departamento.
     */
    @GetMapping("/promedio/{departamentoId}")
    public BigDecimal salarioPromedioDepartamento(@PathVariable Long departamentoId){
        return empleadoService.obtenerSalarioPromedioPorDepartamento(departamentoId);
    }
}
