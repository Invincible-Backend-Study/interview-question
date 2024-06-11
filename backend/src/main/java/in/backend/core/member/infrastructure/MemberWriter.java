package in.backend.core.member.infrastructure;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberWriter {

    private final MemberRepository memberRepository;

    public Long write(MemberInfo memberInfo) {
        return memberRepository.save(memberInfo.toEntity())
                .getId();
    }
}
