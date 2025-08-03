package repositorios;

import entidades.Departamento;
import entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    Optional<Departamento> findByNombre(String nombre);
    List<Empleado> findByEmpleadoId(Long empleadoId);
}
