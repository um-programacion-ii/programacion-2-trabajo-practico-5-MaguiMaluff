package controladores;

import entidades.Departamento;
import org.springframework.web.bind.annotation.*;
import servicios.DepartamentoService;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {
    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService){
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public List<Departamento> obtenerTodos() {
        return departamentoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Departamento obtenerPorId(@PathVariable Long id) {
        return departamentoService.buscarPorId(id);
    }

    @PostMapping
    public Departamento crear(@RequestBody Departamento departamento) {
        return departamentoService.guardar(departamento);
    }

    @PutMapping("/{id}")
    public Departamento actualizar(@PathVariable Long id, @RequestBody Departamento departamento) {
        return departamentoService.actualizar(id, departamento);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        departamentoService.eliminar(id);
    }

    @GetMapping("/empleado/{id}")
    public List<Departamento> obtenerPorEmpleadoId(@PathVariable Long id) {
        return departamentoService.buscarPorEmpleadoId(id);
    }
}

