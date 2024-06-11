package in.backend.global.exception;


public class BadRequestException extends GlobalException {
    public BadRequestException(GlobalExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
