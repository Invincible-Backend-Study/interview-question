package in.backend.core.member.infrastructure;


import in.backend.core.exception.DomainExceptionCode;
import in.backend.core.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberReader {

    private final MemberRepository memberRepository;

    public boolean isMember(String providerId) {
        return memberRepository.existsByProviderId(providerId);
    }

    public MemberEntity read(String providerId) {
        return memberRepository.findByProviderId(providerId)
                .orElseThrow(DomainExceptionCode.PROVIDER_ID_NOT_EXISTS::create);
    }
}
