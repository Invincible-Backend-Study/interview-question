package in.backend.domain.exception;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DomainException extends RuntimeException {
    private final int code;
    private final String message;

}
