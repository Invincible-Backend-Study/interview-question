package in.backend.global.exception;


import in.backend.core.exception.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.retry.NonTransientAiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(0, "유효하지 않은 요쳥입니다."));
    }

    @ExceptionHandler({UnAuthorizedException.class})
    public ResponseEntity<ErrorResponse> exception(UnAuthorizedException exception) {
        log.info("code:{} message:{}", exception.getCode(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler({GlobalException.class})
    public ResponseEntity<ErrorResponse> exception(GlobalException exception) {
        log.info("code:{} message:{}", exception.getCode(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler({DomainException.class})
    public ResponseEntity<ErrorResponse> exception(DomainException exception) {
        log.info("code:{} message:{}", exception.getCode(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler({NonTransientAiException.class})
    public ResponseEntity<ErrorResponse> exception(NonTransientAiException exception) {
        log.info("gpt retry fails message: {}", exception.getMessage());
        var exceptionType = GlobalExceptionCode.GPT_API_DID_NOT_WORK;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(exceptionType.getCode(), exception.getMessage()));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponse> exception(RuntimeException exception) {
        log.info("message: {}", exception.getMessage());
        var exceptionType = GlobalExceptionCode.INVALID_REQUEST;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(exceptionType.getCode(), exception.getMessage()));
    }

    public record ErrorResponse(
            int code,
            String message
    ) {

    }
}
