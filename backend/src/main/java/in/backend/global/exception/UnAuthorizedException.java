package in.backend.global.exception;

public class UnAuthorizedException extends GlobalException {

    public UnAuthorizedException(GlobalExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
