package in.backend.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GlobalExceptionCode {
    INVALID_REQUEST(10000, "유효하지 않은 요청입니다."),

    NOT_FOUND_ACCESS_TOKEN(10001, "access token이 존재하지 않습니다."),
    NOT_FOUND_REFRESH_TOKEN(10002, "refresh token이 존재하지 않습니다."),

    EXPIRED_ACCESS_TOKEN(10003, "access token 만료됐습니다."),
    EXPIRED_REFRESH_TOKEN(10004, "refresh token 만료됐습니다."),

    INVALID_ACCESS_TOKEN(10005, "유효하지 않은 access token 입니다."),
    INVALID_REFRESH_TOKEN(10006, "유효하지 않은 refresh token 입니다."),
    UNAUTHORIZED_MEMBER(10007, "허가받지 않은 사용자입니다."),
    INVALID_OAUTH_CODE(10008, "유효하지 않은 OAUTH 요청입니다."),
    INVALID_OAUTH_SERVER(10009, "현재 서버 연결이 불안정합니다."),

    IMAGE_SAVE_FAIL(10010, "이미지 저장에 실패했습니다."),

    GPT_API_DID_NOT_WORK(10010, "알 수 없는 이유로 GPT 요청이 동작하지 않습니다");

    private final int code;
    private final String message;


}
