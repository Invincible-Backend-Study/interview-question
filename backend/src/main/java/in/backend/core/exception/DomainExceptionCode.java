package in.backend.core.exception;


import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DomainExceptionCode {
    MEMBER(100, "사용자"),
    PROVIDER_ID_EXISTS(MEMBER.code + 1, "이미 회원가입 한 사용자입니다."),
    PROVIDER_ID_NOT_EXISTS(MEMBER.code + 2, "등록되지 않은 사용자입니다."),


    QUESTION_SET(200, "질문 집합"),
    QUESTION_SET_GENERATION_FAIL(QUESTION_SET.code + 1, "질문 집합 만들 수 없습니다."),
    QUESTION_SET_RULES_GENERATION_FAIL(QUESTION_SET.code + 1, "질문 집합 규칙을 만들 수 없습니다.."),


    INTERVIEW(300, "인터뷰"),
    INTERVIEW_CREATE_FAIL(INTERVIEW.code + 1, "인터뷰를 생성할 수 없습니다."),
    INTERVIEW_NOT_FOUND(INTERVIEW.code + 2, "면접을 찾을 수 없습니다."),


    TAIL_QUESTION(400, "꼬리질문"),
    TAIL_QUESTION_CREATE_FAIL(INTERVIEW.code + 1, "꼬리 질문을 생성할 수 없습니다.");


    private final int code;
    private final String message;


    public DomainException create() {
        return new DomainException(this.code, this.message);
    }

    public void invoke() {
        throw create();
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
