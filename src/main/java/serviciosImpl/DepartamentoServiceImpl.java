package serviciosImpl;

import entidades.Departamento;
import org.springframework.transaction.annotation.Transactional;
import repositorios.DepartamentoRepository;
import excepciones.DepartamentoNoEncontradoException;
import excepciones.DepartamentoDuplicadoException;
import excepciones.OperacionNoPermitidaException;
import org.springframework.stereotype.Service;
import servicios.DepartamentoService;

import java.util.List;

@Service
@Transactional
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public Departamento guardar(Departamento departamento) {
        if (departamentoRepository.findByNombre(departamento.getNombre()).isPresent()) {
            throw new DepartamentoDuplicadoException("Ya existe un departamento con el nombre: " + departamento.getNombre());
        }
        return departamentoRepository.save(departamento);
    }

    @Override
    public Departamento buscarPorId(Long id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new DepartamentoNoEncontradoException("Departamento no encontrado con ID: " + id));
    }

    @Override
    public List<Departamento> buscarPorEmpleadoId(Long empleadoId) {
        List<Departamento> departamentos = departamentoRepository.findByEmpleadoId(empleadoId);
        if (departamentos.isEmpty()) {
            throw new DepartamentoNoEncontradoException("No hay departamentos asociados al empleado ID: " + empleadoId);
        }
        return departamentos;
    }

    @Override
    public List<Departamento> obtenerTodos() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        if (departamentos.isEmpty()) {
            throw new DepartamentoNoEncontradoException("No hay departamentos registrados.");
        }
        return departamentos;
    }

    @Override
    public Departamento actualizar(Long id, Departamento departamento) {
        Departamento existente = departamentoRepository.findById(id)
                .orElseThrow(() -> new DepartamentoNoEncontradoException("Departamento no encontrado con ID: " + id));
        if (!existente.getNombre().equals(departamento.getNombre())
                && departamentoRepository.findByNombre(departamento.getNombre()).isPresent()) {
            throw new DepartamentoDuplicadoException("Ya existe un departamento con el nombre: " + departamento.getNombre());
        }
        departamento.setId(id);
        return departamentoRepository.save(departamento);
    }

    @Override
    public void eliminar(Long id) {
        Departamento existente = departamentoRepository.findById(id)
                .orElseThrow(() -> new DepartamentoNoEncontradoException("Departamento no encontrado con ID: " + id));
        if (existente.getEmpleados() != null && !existente.getEmpleados().isEmpty()) {
            throw new OperacionNoPermitidaException("No se puede eliminar el departamento con empleados asignados.");
        }
        departamentoRepository.deleteById(id);
    }
}