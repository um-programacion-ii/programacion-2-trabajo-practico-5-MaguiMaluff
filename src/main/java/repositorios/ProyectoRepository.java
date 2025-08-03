package repositorios;

import entidades.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    Optional<Proyecto> findByNombre(String nombre);
    List<Proyecto> findByFechaInicioAfter(LocalDate fechaInicio);
    List<Proyecto> findByFechaFinaBefore(LocalDate fechaFina);
    List<Proyecto> findByEmpleados_Id(Long empleadoId);
}
