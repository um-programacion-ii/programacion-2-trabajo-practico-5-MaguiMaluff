## 1. Prompts utilizados para crear entidades JPA
   
### No se utilizaron

## 2. Prompts para implementar repositories y services

## Prompt 1: creación de repositorio 

### Prompt Utilizado:

Necesito un @Repository para la clase Proyecto:
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaFina;

    @ManyToMany(mappedBy = "proyectos")
    private Set<Empleado> empleados = new HashSet<>();

}

### Respuesta recibida:

```java name=ProyectoRepository.java
@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    Optional<Proyecto> findByNombre(String nombre);

    List<Proyecto> findByFechaInicioAfter(LocalDate fechaInicio);

    List<Proyecto> findByFechaFinaBefore(LocalDate fechaFina);

    List<Proyecto> findByEmpleados_Id(Long empleadoId);
}
```

**Explicación:**
- `findByNombre`: Busca un proyecto por nombre.
- `findByFechaInicioAfter`: Proyectos que empiezan después de una fecha dada.
- `findByFechaFinaBefore`: Proyectos que terminan antes de una fecha dada.
- `findByEmpleados_Id`: Proyectos asociados a un empleado por su ID.


### Modificaciones Realizadas:
- [Lista de cambios realizados al código generado]
  - Se agregó findById
- [Explicación de por qué se modificó]
  - Necesitaba una forma de encontrar Proyectos por Id.

### Explicación del Prompt:
[Explicar por qué se usó este prompt específico]
Se utilizó el prompt así por qué explicaba bien el fin.

### Aprendizajes Obtenidos:
- [Lista de aprendizajes]
  - Ninguno, era una tarea simple.
- [Conceptos nuevos comprendidos]
- [Buenas prácticas identificadas]
  - Nombrar las funciones con minúscula al principio y mayúscula en el resto de palabras.

---


## 3. Prompts para configurar controllers

## Prompt 1: Ayuda con el Issue 22, manejo de excepciones HTTP

### Prompt Utilizado: 
    
Estoy en el Issue 22 del proyecto, donde debo configurar el manejo de excepciones HTTP para los controladores de Empleado, Departamento y Proyecto.

*Se enviaron a la par del prompt las entidades, las excepciones, los controladores y las implementaciones de los servicios*

### Respuesta recibida:

¡Perfecto!  
Ya vi tus archivos y el patrón de excepciones personalizadas que usas en los servicios (`DepartamentoNoEncontradoException`, `DepartamentoDuplicadoException`, etc.).

Para el **Issue 22: Manejo de Excepciones HTTP**, necesitas una clase global que escuche esos errores y devuelva una respuesta HTTP estructurada, con el código correcto y un mensaje útil.

---

## **¿Cómo lo implemento?**

1. **Crea un archivo `GlobalExceptionHandler.java` en el paquete `excepciones`**
2. **Maneja los siguientes casos**:
    - Validaciones fallidas (`MethodArgumentNotValidException`)
    - Tus excepciones personalizadas (`DepartamentoNoEncontradoException`, `DepartamentoDuplicadoException`, etc.)
    - Otros errores generales (`Exception`)

3. **Devuelve un JSON con**:
    - `timestamp`
    - `status`
    - `error`
    - (opcional) detalles de los campos si es validación

---

## **Propuesta de archivo**

```java name=excepciones/GlobalExceptionHandler.java
package excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Validación @Valid fallida
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("errors", fieldErrors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Departamento no encontrado
    @ExceptionHandler(DepartamentoNoEncontradoException.class)
    public ResponseEntity<Object> handleDepartamentoNoEncontrado(DepartamentoNoEncontradoException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Departamento duplicado
    @ExceptionHandler(DepartamentoDuplicadoException.class)
    public ResponseEntity<Object> handleDepartamentoDuplicado(DepartamentoDuplicadoException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // Operación no permitida
    @ExceptionHandler(OperacionNoPermitidaException.class)
    public ResponseEntity<Object> handleOperacionNoPermitida(OperacionNoPermitidaException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    // Empleado no encontrado
    @ExceptionHandler(EmpleadoNoEncontradoException.class)
    public ResponseEntity<Object> handleEmpleadoNoEncontrado(EmpleadoNoEncontradoException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Email duplicado
    @ExceptionHandler(EmailDuplicadoException.class)
    public ResponseEntity<Object> handleEmailDuplicado(EmailDuplicadoException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // Proyecto no encontrado
    @ExceptionHandler(ProyectoNoEncontradoException.class)
    public ResponseEntity<Object> handleProyectoNoEncontrado(ProyectoNoEncontradoException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Proyecto duplicado
    @ExceptionHandler(ProyectoDuplicadoException.class)
    public ResponseEntity<Object> handleProyectoDuplicado(ProyectoDuplicadoException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // Otros errores generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        return buildErrorResponse("Error interno: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Método auxiliar para construir la respuesta
    private ResponseEntity<Object> buildErrorResponse(String mensaje, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", mensaje);

        return new ResponseEntity<>(body, status);
    }
}
```

---

## **¿Dónde lo pongo?**
- **Ubicación recomendada:**  
  `src/main/java/excepciones/GlobalExceptionHandler.java`

---

## **¿Qué logras con esto?**
- Todos tus errores y excepciones devolverán una respuesta HTTP clara y estructurada.
- El código HTTP será el correcto (`404`, `409`, `403`, etc.).
- Los errores de validación mostrarán los campos y el motivo.

---

¿Te gustaría que te proponga el archivo listo para tu repo, o necesitas ayuda para testearlo con Postman/Insomnia?

### Modificaciones Realizadas:
- No hubo modificaciones

### Explicación del Prompt:
[Explicar por qué se usó este prompt específico]
Utilizé el prompt por qué no sabía como manejar las excepciones HTTP, y agregué los archivos porque aportan contexto y las respuestas son más eficientes.

### Aprendizajes Obtenidos:
- [Lista de aprendizajes]
  - Manejo de excepciones de forma global en HTTP
- [Conceptos nuevos comprendidos]
  - @ExceptionHandler: indica quién será el encargado de manejar una excepción específica.
- [Buenas prácticas identificadas]
  - Manejar las excepciones, errores y validaciones personalizadas de forma global con un mensaje adecuado de respuesta.

---

## 4. Explicación de las decisiones de diseño tomadas

