package servicios;

import entidades.Proyecto;

import java.time.LocalDate;
import java.util.List;

public interface ProyectoService {
    Proyecto guardar(Proyecto proyecto);
    Proyecto buscarPorId(Long id);
    Proyecto buscarPorNombre(String nombre);
    List<Proyecto> buscarPorFechaInicioDespues(LocalDate fechaInicio);
    List<Proyecto> buscarPorFechaFinAntes(LocalDate fechaFina);
    List<Proyecto> buscarPorEmpleadoId(Long empleadoId);
    List<Proyecto> obtenerTodos();
    Proyecto actualizar(Long id, Proyecto proyecto);
    void eliminar(Long id);
}