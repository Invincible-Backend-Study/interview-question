package in.backend.core.exception;


import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DomainExceptionCode {

    USER(100, "사용자"),
    PROVIDER_ID_NOT_EXISTS(USER.code + 1, "사용자가 존재하지 않습니다."),
    PROVIDER_ID_EXISTS(USER.code + 2, "이미 회원가입 했습니다."),


    QUESTION(200, "질문"),

    QUESTION_SET(300, "질문 집합"),
    QUESTION_SET_GENERATION_FAIL(QUESTION_SET.code + 1, "질문 집합 만들 수 없습니다."),
    QUESTION_SET_RULES_GENERATION_FAIL(QUESTION_SET.code + 2, "질문 집합 규칙을 만들 수 없습니다.."),
    QUESTION_SET_NOT_FOUND(QUESTION_SET.code + 3, "질문 집합을 찾을 수 없습니다."),


    INTERVIEW(400, "인터뷰"),
    INTERVIEW_CREATE_FAIL(INTERVIEW.code + 1, "인터뷰를 생성할 수 없습니다."),

    ;


    private final int code;
    private final String message;


    public DomainException create() {
        return new DomainException(this.code, this.message);
    }


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
