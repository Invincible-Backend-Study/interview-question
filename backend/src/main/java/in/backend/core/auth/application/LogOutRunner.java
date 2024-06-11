package in.backend.core.auth.application;


import in.backend.core.auth.domain.Visitor;
import in.backend.core.auth.infrastrcutrue.RefreshTokenWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogOutRunner {
    private final RefreshTokenWriter refreshTokenWriter;


    public void run(Visitor visitor) {
        refreshTokenWriter.delete(visitor.memberId());
    }
}
