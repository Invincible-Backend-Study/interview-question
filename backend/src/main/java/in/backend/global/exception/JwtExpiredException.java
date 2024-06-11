package in.backend.global.exception;

public class JwtExpiredException extends GlobalException {

    public JwtExpiredException(GlobalExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
