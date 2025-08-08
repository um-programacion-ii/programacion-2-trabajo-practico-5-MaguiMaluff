package controladores;

import entidades.Empleado;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import servicios.EmpleadoService;

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

    @GetMapping
    public List<Empleado> obtenerTodos() {
        return empleadoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Empleado obtenerPorId(@PathVariable Long id) {
        return empleadoService.buscarPorId(id);
    }

    @PostMapping
    public Empleado crear(@Valid @RequestBody Empleado empleado) {
        return empleadoService.guardar(empleado);
    }

    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
        return empleadoService.actualizar(id, empleado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
    }

    @GetMapping("/departamento/{nombre}")
    public List<Empleado> obtenerPorDepartamento(@PathVariable String nombre) {
        return empleadoService.buscarPorDepartamento(nombre);
    }

    @GetMapping("/salario")
    public List<Empleado> obtenerPorRangoSalario(@RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return empleadoService.buscarPorRangoSalario(min, max);
    }

    @GetMapping("/promedio")
    public BigDecimal salarioPromedioDepartamento(@PathVariable Long departamentoId){
        return empleadoService.obtenerSalarioPromedioPorDepartamento(departamentoId);
    }
}
