package in.backend.core.member.presentation;


import in.backend.core.auth.domain.Visitor;
import in.backend.core.auth.domain.attributes.Auth;
import in.backend.core.auth.domain.attributes.MemberOnly;
import in.backend.core.member.infrastructure.MemberReader;
import in.backend.core.member.presentation.payload.MyProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApi {

    private final MemberReader memberReader;

    @MemberOnly
    @GetMapping("/me")
    public MyProfileResponse getMe(@Auth Visitor visitor) {
        var member = memberReader.read(visitor.memberId());
        return MyProfileResponse.from(member);
    }
}
