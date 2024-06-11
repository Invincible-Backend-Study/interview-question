package in.backend.global.exception;

public class InvalidJwtException extends GlobalException {

    public InvalidJwtException(GlobalExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
