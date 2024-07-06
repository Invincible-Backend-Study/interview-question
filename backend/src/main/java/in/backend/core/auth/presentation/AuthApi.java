package in.backend.core.auth.presentation;


import static com.google.common.net.HttpHeaders.SET_COOKIE;
import static org.springframework.http.HttpStatus.CREATED;

import in.backend.core.auth.application.LogOutRunner;
import in.backend.core.auth.application.SocialLoginProcessor;
import in.backend.core.auth.application.TokenReissue;
import in.backend.core.auth.domain.Visitor;
import in.backend.core.auth.domain.attributes.Auth;
import in.backend.core.auth.domain.attributes.MemberOnly;
import in.backend.core.auth.presentation.payload.request.OAuthProfileRequest;
import in.backend.core.auth.presentation.payload.request.SigninRequest;
import in.backend.core.auth.presentation.payload.request.SignupRequest;
import in.backend.core.auth.presentation.payload.response.AccessTokenResponse;
import in.backend.core.auth.presentation.payload.response.OAuthProfileResponse;
import in.backend.global.utils.CookieProvider;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthApi {

    private final SocialLoginProcessor socialLoginProcessor;
    private final CookieProvider cookieProvider;
    private final LogOutRunner logOutRunner;
    private final TokenReissue tokenReissue;


    @PostMapping("/signup")
    public ResponseEntity<AccessTokenResponse> signup(
            @Valid @RequestBody SignupRequest signupRequest,
            HttpServletResponse response
    ) {
        var issuedToken = socialLoginProcessor.signup(signupRequest.toDto());

        response.addHeader(SET_COOKIE, cookieProvider.createCookie(issuedToken.refreshToken()).toString());
        return ResponseEntity.status(CREATED)
                .body(new AccessTokenResponse(issuedToken.accessToken()));
    }


    @PostMapping("/signin")
    public ResponseEntity<AccessTokenResponse> signin(
            @Valid @RequestBody SigninRequest signinRequest,
            HttpServletResponse response
    ) {
        var issuedToken = socialLoginProcessor.signin(signinRequest);

        response.addHeader(SET_COOKIE, cookieProvider.createCookie(issuedToken.refreshToken()).toString());
        return ResponseEntity.status(CREATED)
                .body(new AccessTokenResponse(issuedToken.accessToken()));
    }

    @GetMapping("/profile")
    public OAuthProfileResponse getProfile(@ModelAttribute OAuthProfileRequest profile) {
        return socialLoginProcessor.findProfile(profile);
    }

    @PostMapping("/token/reissue")
    public ResponseEntity<AccessTokenResponse> reIssue(
            @CookieValue("refreshToken") final String refreshToken
    ) {
        var accessToken = tokenReissue.publish(refreshToken);

        return ResponseEntity.status(CREATED)
                .body(new AccessTokenResponse(accessToken));
    }


    @MemberOnly
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@Auth Visitor visitor) {
        logOutRunner.run(visitor);
        return ResponseEntity.noContent()
                .build();
    }


}
