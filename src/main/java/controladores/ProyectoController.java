package controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import servicios.ProyectoService;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {
    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService){
        this.proyectoService = proyectoService;
    }
}
