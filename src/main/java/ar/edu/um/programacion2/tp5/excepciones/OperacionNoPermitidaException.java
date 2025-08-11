package ar.edu.um.programacion2.tp5.excepciones;

public class OperacionNoPermitidaException extends RuntimeException {
  public OperacionNoPermitidaException(String message) {
    super(message);
  }
}
