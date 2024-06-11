package in.backend.global.exception;

public class RefreshTokenException extends GlobalException {
    public RefreshTokenException(GlobalExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
