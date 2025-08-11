package ar.edu.um.programacion2.tp5.controladores;

import ar.edu.um.programacion2.tp5.entidades.Departamento;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ar.edu.um.programacion2.tp5.servicios.DepartamentoService;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
@Validated
public class DepartamentoController {
    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService){
        this.departamentoService = departamentoService;
    }

    /**
     * Obtiene la lista de todos los departamentos.
     * @return Lista de entidades Departamento.
     */
    @GetMapping
    public List<Departamento> obtenerTodos() {
        return departamentoService.obtenerTodos();
    }

    /**
     * Obtiene un departamento por su ID.
     * @param id ID del departamento a buscar.
     * @return Entidad Departamento correspondiente al ID.
     */
    @GetMapping("/{id}")
    public Departamento obtenerPorId(@PathVariable Long id) {
        return departamentoService.buscarPorId(id);
    }

    /**
     * Crea un nuevo departamento.
     * @param departamento Entidad Departamento a crear.
     * @return Departamento creado.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Departamento crear(@Valid @RequestBody Departamento departamento) {
        return departamentoService.guardar(departamento);
    }

    /**
     * Actualiza un departamento existente por su ID.
     * @param id ID del departamento a actualizar.
     * @param departamento Datos actualizados del departamento.
     * @return Departamento actualizado.
     */
    @PutMapping("/{id}")
    public Departamento actualizar(@PathVariable Long id,@Valid @RequestBody Departamento departamento) {
        return departamentoService.actualizar(id, departamento);
    }

    /**
     * Elimina un departamento por su ID.
     * @param id ID del departamento a eliminar.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        departamentoService.eliminar(id);
    }

    /**
     * Obtiene los departamentos asociados a un empleado por su ID.
     * @param id ID del empleado.
     * @return Lista de departamentos asociados al empleado.
     */
    @GetMapping("/empleado/{id}")
    public List<Departamento> obtenerPorEmpleadoId(@PathVariable Long id) {
        return departamentoService.buscarPorEmpleadoId(id);
    }
}

