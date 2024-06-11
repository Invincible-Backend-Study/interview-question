package in.backend.global.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(0, "유효하지 않은 요쳥입니다."));
    }


    public record ErrorResponse(
            int code,
            String message
    ) {

    }
}
