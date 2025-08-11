package ar.edu.um.programacion2.tp5.controladores;

import ar.edu.um.programacion2.tp5.entidades.Proyecto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ar.edu.um.programacion2.tp5.servicios.ProyectoService;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@Validated
public class ProyectoController {
    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService){
        this.proyectoService = proyectoService;
    }

    /**
     * Obtiene la lista de todos los proyectos.
     * @return Lista de entidades Proyecto.
     */
    @GetMapping
    public List<Proyecto> obtenerTodos(){
        return proyectoService.obtenerTodos();
    }

    /**
     * Obtiene un proyecto por su ID.
     * @param id ID del proyecto a buscar.
     * @return Entidad Proyecto correspondiente al ID.
     */
    @GetMapping("/{id}")
    public Proyecto obtenerPorId(@PathVariable Long id){
        return proyectoService.buscarPorId(id);
    }

    /**
     * Obtiene un proyecto por su nombre.
     * @param nombre Nombre del proyecto a buscar.
     * @return Entidad Proyecto correspondiente al nombre.
     */
    @GetMapping("/nombre/{nombre}")
    public Proyecto obtenerPorNombre(@PathVariable String nombre){
        return proyectoService.buscarPorNombre(nombre);
    }

    /**
     * Crea un nuevo proyecto.
     * @param proyecto Entidad Proyecto a crear.
     * @return Proyecto creado.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proyecto crear(@Valid @RequestBody Proyecto proyecto){
        return proyectoService.guardar(proyecto);
    }

    /**
     * Actualiza un proyecto existente por su ID.
     * @param id ID del proyecto a actualizar.
     * @param proyecto Datos actualizados del proyecto.
     * @return Proyecto actualizado.
     */
    @PutMapping("/{id}")
    public Proyecto actualizar(@PathVariable Long id,@Valid @RequestBody Proyecto proyecto){
        return proyectoService.actualizar(id, proyecto);
    }

    /**
     * Elimina un proyecto por su ID.
     * @param id ID del proyecto a eliminar.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        proyectoService.eliminar(id);
    }

    /**
     * Obtiene los proyectos cuya fecha de inicio es posterior a la indicada.
     * @param fechaInicio Fecha mínima de inicio.
     * @return Lista de proyectos con inicio después de la fecha indicada.
     */
    @GetMapping("/fecha/despues")
    public List<Proyecto> obtenerFechaInicioDespues(@RequestParam LocalDate fechaInicio){
        return proyectoService.buscarPorFechaInicioDespues(fechaInicio);
    }

    /**
     * Obtiene los proyectos cuya fecha de fin es anterior a la indicada.
     * @param fechaFin Fecha máxima de fin.
     * @return Lista de proyectos con fin antes de la fecha indicada.
     */
    @GetMapping("/fecha/antes")
    public List<Proyecto> obtenerFechaFinAntes(@RequestParam LocalDate fechaFin){
        return proyectoService.buscarPorFechaFinAntes(fechaFin);
    }

    /**
     * Obtiene los proyectos en los que participa un empleado específico.
     * @param id ID del empleado.
     * @return Lista de proyectos asociados al empleado.
     */
    @GetMapping("/empleado/{id}")
    public List<Proyecto> obtenerPorEmpleadoId(@PathVariable Long id){
        return proyectoService.buscarPorEmpleadoId(id);
    }
}
