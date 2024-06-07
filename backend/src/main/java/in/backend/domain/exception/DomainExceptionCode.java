package in.backend.domain.exception;


import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DomainExceptionCode {
    QUESTION(100, "질문"),

    QUESTION_SET(200, "질문 집합"),
    QUESTION_SET_GENERATION_FAIL(QUESTION_SET.code + 1, "질문 집합 만들 수 없습니다."),
    QUESTION_SET_RULES_GENERATION_FAIL(QUESTION_SET.code + 1, "질문 집합 규칙을 만들 수 없습니다..");

    private final int code;
    private final String message;


    public void invoke() {
        throw new DomainException(this.code, this.message);
    }

    public void invokeByCondition(Supplier<Boolean> supplier) {
        this.invokeByCondition(supplier.get());
    }

    public void invokeByCondition(boolean condition) {
        if (condition) {
            invoke();
        }
    }
}
