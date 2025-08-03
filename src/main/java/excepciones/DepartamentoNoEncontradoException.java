package excepciones;

public class DepartamentoNoEncontradoException extends RuntimeException {
    public DepartamentoNoEncontradoException(String message) {
        super(message);
    }
}
