package controladores;

import entidades.Departamento;
import entidades.Proyecto;
import org.springframework.web.bind.annotation.*;
import servicios.ProyectoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {
    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService){
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public List<Proyecto> obtenerTodos(){
        return proyectoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Proyecto obtenerPorId(@PathVariable Long id){
        return proyectoService.buscarPorId(id);
    }

    @GetMapping("/nombre/{nombre}")
    public Proyecto obtenerPorNombre(@PathVariable String nombre){
        return proyectoService.buscarPorNombre(nombre);
    }

    @PostMapping
    public Proyecto crear(@RequestBody Proyecto proyecto){
        return proyectoService.guardar(proyecto);
    }

    @PutMapping("/{id}")
    public Proyecto actualizar(@PathVariable Long id, @RequestBody Proyecto proyecto){
        return proyectoService.actualizar(id, proyecto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        proyectoService.eliminar(id);
    }

    @GetMapping("/fecha/despues")
    public List<Proyecto> obtenerFechaInicioDespues(@RequestParam LocalDate fechaInicio){
        return proyectoService.buscarPorFechaInicioDespues(fechaInicio);
    }

    @GetMapping("/fecha/antes")
    public List<Proyecto> obtenerFechaFinAntes(@RequestParam LocalDate fechaFin){
        return proyectoService.buscarPorFechaFinAntes(fechaFin);
    }


    @GetMapping("/empleado/{id}")
    public List<Proyecto> obtenerPorEmpleadoId(@PathVariable Long id){
        return proyectoService.buscarPorEmpleadoId(id);
    }
}
