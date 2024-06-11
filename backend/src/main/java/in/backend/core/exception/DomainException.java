package in.backend.core.exception;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DomainException extends RuntimeException {
    private final int code;
    private final String message;

}
