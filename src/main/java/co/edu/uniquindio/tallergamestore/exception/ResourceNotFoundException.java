package co.edu.uniquindio.tallergamestore.exception;

// Esta excepción se utiliza cuando no encontramos algo en la base de datos
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
