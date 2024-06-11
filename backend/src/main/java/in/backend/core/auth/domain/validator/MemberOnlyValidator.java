package in.backend.core.auth.domain.validator;


import static in.backend.global.exception.GlobalExceptionCode.UNAUTHORIZED_MEMBER;

import in.backend.core.auth.domain.Visitor;
import in.backend.global.exception.UnAuthorizedException;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MemberOnlyValidator {


    @Before("@annotation(in.backend.core.auth.domain.attributes.MemberOnly)")
    public void check(final JoinPoint joinPoint) {
        log.info("{}", joinPoint.getArgs());
        Arrays.stream(joinPoint.getArgs())
                .filter(Visitor.class::isInstance)
                .map(Visitor.class::cast)
                .filter(Visitor::isMember)
                .findFirst()
                .orElseThrow(() -> new UnAuthorizedException(UNAUTHORIZED_MEMBER));
    }
}
