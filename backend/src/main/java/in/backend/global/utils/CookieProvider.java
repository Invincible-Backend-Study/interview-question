package in.backend.global.utils;


import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;


@Component
public class CookieProvider {
    public static final String REFRESH_TOKEN_KEY = "refreshToken";
    private static final int COOKIE_AGE_SECONDS = 604800;

    public ResponseCookie createCookie(String refreshToken) {
        return ResponseCookie.from(REFRESH_TOKEN_KEY, refreshToken)
                .maxAge(COOKIE_AGE_SECONDS)
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .path("/")
                .build();
    }
}
