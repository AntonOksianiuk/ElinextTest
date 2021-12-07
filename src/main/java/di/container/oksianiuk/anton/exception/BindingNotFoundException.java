package di.container.oksianiuk.anton.exception;

public class BindingNotFoundException extends RuntimeException {
    public BindingNotFoundException() {
    }

    public BindingNotFoundException(String message) {
        super(message);
    }
}
