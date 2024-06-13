package in.backend.core.auth.presentation;


import in.backend.core.auth.presentation.payload.request.SignupRequest;

public class AuthApiFixture {

    public static SignupRequest signupRequest() {
        return SignupRequest.builder()
                .providerId("providerId")
                .avatarUrl("avatarUrl")
                .nickname("nickname")
                .build();
    }
}
