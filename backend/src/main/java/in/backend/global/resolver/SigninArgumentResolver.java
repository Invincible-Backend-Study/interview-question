package in.backend.global.resolver;

import static in.backend.global.exception.GlobalExceptionCode.INVALID_REQUEST;
import static in.backend.global.exception.GlobalExceptionCode.NOT_FOUND_REFRESH_TOKEN;

import in.backend.core.auth.domain.Visitor;
import in.backend.core.auth.domain.attributes.Auth;
import in.backend.core.auth.infrastrcutrue.RefreshTokenReader;
import in.backend.global.exception.BadRequestException;
import in.backend.global.exception.RefreshTokenException;
import in.backend.global.provider.JwtProvider;
import in.backend.global.utils.CookieProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
@RequiredArgsConstructor
public class SigninArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtProvider jwtProvider;
    private final RefreshTokenReader refreshTokenReader;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.withContainingClass(Visitor.class)
                .hasParameterAnnotation(Auth.class);
    }

    @Override
    public Visitor resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {

        final var httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        if (Objects.isNull(httpServletRequest)) {
            throw new BadRequestException(INVALID_REQUEST);
        }

        return extractMemberId(httpServletRequest.getCookies())
                .filter(memberIdToRefreshToken -> {
                    var memberId = jwtProvider.decodeAccessToken(jwtProvider.extractToken(httpServletRequest));
                    return Objects.equals(memberId, memberIdToRefreshToken);
                })
                .map(Visitor::member)
                .orElseGet(Visitor::guest);
    }

    private Optional<Long> extractMemberId(Cookie... cookies) {
        try {
            if (Objects.isNull(cookies)) {
                throw new RefreshTokenException(NOT_FOUND_REFRESH_TOKEN);
            }
            return Optional.of(Arrays.stream(cookies)
                    .filter(cookie -> CookieProvider.REFRESH_TOKEN_KEY.equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .map(jwtProvider::decodeRefreshToken)
                    .filter(refreshTokenReader::existsBy)
                    .findFirst()
                    .orElseThrow(() -> new RefreshTokenException(NOT_FOUND_REFRESH_TOKEN)));
        } catch (RefreshTokenException refreshTokenException) {
            return Optional.empty();
        }


    }
}
