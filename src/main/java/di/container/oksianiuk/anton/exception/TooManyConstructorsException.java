package di.container.oksianiuk.anton.exception;

public class TooManyConstructorsException extends RuntimeException {

    public TooManyConstructorsException(String message) {
        super(message);
    }

    public TooManyConstructorsException() {
    }
}
