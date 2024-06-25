package in.backend.core.member.infrastructure;


import static in.backend.core.exception.DomainExceptionCode.NICK_NAME_EXISTS;
import static in.backend.core.exception.DomainExceptionCode.PROVIDER_ID_EXISTS;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberWriter {

    private final MemberRepository memberRepository;

    public Long write(MemberInfo memberInfo) {
        PROVIDER_ID_EXISTS.invokeByCondition(memberRepository.existsByProviderId(memberInfo.providerId()));
        NICK_NAME_EXISTS.invokeByCondition(memberRepository.existsByNickname(memberInfo.nickname()));

        return memberRepository.save(memberInfo.toEntity())
                .getId();
    }
}
