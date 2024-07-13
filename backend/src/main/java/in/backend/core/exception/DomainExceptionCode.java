package in.backend.core.exception;


import java.util.function.Supplier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DomainExceptionCode {

    USER(100, "사용자"),
    PROVIDER_ID_NOT_EXISTS(USER.code + 1, "사용자가 존재하지 않습니다."),
    MEMBER_ID_NOT_EXISTS(USER.code + 2, "사용자가 존재하지 않습니다."),
    PROVIDER_ID_EXISTS(USER.code + 4, "이미 회원가입 했습니다."),
    NICK_NAME_EXISTS(USER.code + 3, "이미 사용중인 닉네임니다."),

    QUESTION(200, "질문"),

    QUESTION_SET(300, "질문 집합"),
    QUESTION_SET_GENERATION_FAIL(QUESTION_SET.code + 1, "질문 집합 만들 수 없습니다."),
    QUESTION_SET_RULES_GENERATION_FAIL(QUESTION_SET.code + 2, "질문 집합 규칙을 만들 수 없습니다.."),
    QUESTION_SET_NOT_FOUND(QUESTION_SET.code + 3, "질문 집합을 찾을 수 없습니다."),


    INTERVIEW(400, "인터뷰"),
    INTERVIEW_CREATE_FAIL(INTERVIEW.code + 1, "인터뷰를 생성할 수 없습니다."),
    INTERVIEW_NOT_FOUND(INTERVIEW.code + 2, "인터뷰를 찾을 수 없습니다."),
    INTERVIEW_STATE_IS_DONE(INTERVIEW.code + 3, "종료된 인터뷰입니다."),
    INTERVIEW_STATE_DID_NOT_MATCH(INTERVIEW.code + 4, "현재 풀고 있는 문제가 이미 풀었거나 풀어야 할 문제가 아닙니다."),

    INTERVIEW_QUESTION(500, "인터뷰 질문"),
    INTERVIEW_QUESTION_NOT_FOUND(INTERVIEW_QUESTION.code + 1, "인터뷰 질문을 찾을 수 없습니다."),
    INTERVIEW_QUESTION_CREATE_FAIL(INTERVIEW_QUESTION.code + 2, "인터뷰 질문을 생성할 수 없습니다."),


    TAIL_QUESTION(600, "꼬리 질문"),
    TAIL_QUESTION_NOT_FOUND(TAIL_QUESTION.code + 1, "꼬리질문을 찾을 수 없습니다"),
    TAIL_QUESTION_CREATE_FAIL(TAIL_QUESTION.code + 2, "꼬리질문을 생성할 수 없습니다."),
    ;


    private final int code;
    @Getter
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
