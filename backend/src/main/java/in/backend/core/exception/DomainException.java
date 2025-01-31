package in.backend.core.exception;


import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private final int code;
    private final String message;


    public DomainException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
