package in.backend.core.questionset.entity;


import in.backend.core.question.entity.QuestionEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Questions {


    @OneToMany(mappedBy = "questionSet")
    private List<QuestionEntity> value = new ArrayList<>();

    public static Questions empty() {
        return new Questions();
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }

    public boolean hasSameSize(int count) {
        return value.size() == count;
    }

    public List<QuestionEntity> getValue() {
        return Collections.unmodifiableList(value);
    }

    public int size() {
        return value.size();
    }

    public List<QuestionEntity> shuffle() {

        var temp = new ArrayList<>(this.getValue());

        Collections.shuffle(temp);

        return temp;
    }

    public List<QuestionEntity> getOrderedSequenceValue() {
        return this.value.stream()
                .sorted(Comparator.comparingInt(QuestionEntity::getSequence))
                .toList();
    }
}
