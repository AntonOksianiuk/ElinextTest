package di.container.oksianiuk.anton.exception;

public class ConstructorNotFoundException extends RuntimeException {
    public ConstructorNotFoundException(String s) {
        super(s);
    }

    public ConstructorNotFoundException() {
    }
}
