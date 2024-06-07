package in.backend.domain.questionset.entity;


import in.backend.domain.question.entity.QuestionEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Questions {

    @OneToMany
    @JoinColumn(nullable = false)
    private List<QuestionEntity> questions = new ArrayList<>();


    public static Questions empty() {
        return new Questions();
    }

}
