package in.backend.core.member.presentation;


import in.backend.core.auth.domain.Visitor;
import in.backend.core.auth.domain.attributes.Auth;
import in.backend.core.auth.domain.attributes.MemberOnly;
import in.backend.core.member.application.MemberService;
import in.backend.core.member.presentation.payload.MyProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApi {
    private final MemberService memberService;

    @MemberOnly
    @GetMapping("/me")
    public MyProfileResponse getMe(@Auth Visitor visitor) {
        return MyProfileResponse.from(memberService.getMe(visitor));
    }

    @MemberOnly
    @DeleteMapping
    public ResponseEntity<Void> withdrawal(
            @Auth Visitor visitor
    ) {
        memberService.withDraw(visitor);
        return ResponseEntity.noContent().build();
    }
}
