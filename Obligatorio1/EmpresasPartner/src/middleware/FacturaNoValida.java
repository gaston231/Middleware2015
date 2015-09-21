package middleware;

public class FacturaNoValida extends Exception {
    public FacturaNoValida(String message, Throwable cause) {
        super(message, cause);
    }
}
