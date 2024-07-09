package in.backend.core.member.application;


import in.backend.core.auth.domain.Visitor;
import in.backend.core.member.entity.MemberEntity;
import in.backend.core.member.infrastructure.MemberManager;
import in.backend.core.member.infrastructure.MemberReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberReader memberReader;
    private final MemberManager memberManager;


    public MemberEntity getMe(Visitor visitor) {
        return memberReader.read(visitor.memberId());
    }

    public void withDraw(Visitor visitor) {
        memberManager.widthDraw(visitor.memberId());
    }
}
