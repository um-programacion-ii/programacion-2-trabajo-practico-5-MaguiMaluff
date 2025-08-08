package controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import servicios.DepartamentoService;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {
    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService){
        this.departamentoService = departamentoService;
    }
}
