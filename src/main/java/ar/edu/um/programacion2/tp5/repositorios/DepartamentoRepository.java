package ar.edu.um.programacion2.tp5.repositorios;

import ar.edu.um.programacion2.tp5.entidades.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    Optional<Departamento> findByNombre(String nombre);
    List<Departamento> findByEmpleados_Id(Long empleadoId);


    @Query("SELECT d FROM Departamento d WHERE d.empleados IS EMPTY")
    List<Departamento> findDepartamentosSinEmpleados();

    @Query("SELECT d FROM Departamento d WHERE SIZE(d.empleados) > :cantidad")
    List<Departamento> findDepartamentosConMasDeXEmpleados(@Param("cantidad") int cantidad);

}
