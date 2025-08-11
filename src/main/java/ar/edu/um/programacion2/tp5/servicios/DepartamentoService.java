package ar.edu.um.programacion2.tp5.servicios;

import ar.edu.um.programacion2.tp5.entidades.Departamento;

import java.util.List;

public interface DepartamentoService {
    Departamento guardar(Departamento departamento);
    Departamento buscarPorId(Long Id);
    List<Departamento> buscarPorEmpleadoId(Long id);
    List<Departamento> obtenerTodos();
    Departamento actualizar(Long id, Departamento departamento);
    void eliminar(Long id);
}
