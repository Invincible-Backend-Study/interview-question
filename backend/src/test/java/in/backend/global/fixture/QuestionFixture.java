package in.backend.global.fixture;


import in.backend.core.question.entity.QuestionEntity;
import in.backend.core.questionset.entity.QuestionSetEntity;
import java.util.List;
import java.util.stream.IntStream;

public class QuestionFixture {

    public static QuestionEntity create(QuestionSetEntity questionSet, int sequence) {
        return QuestionEntity.builder()
                .referenceLinks("")
                .sequence(sequence)
                .content(STR."질문\{sequence}")
                .questionSet(questionSet)
                .build();
    }

    public static List<QuestionEntity> creates(QuestionSetEntity questionSet, int count) {
        return IntStream.iterate(1, i -> i + 1)
                .limit(count)
                .mapToObj(sequence -> create(questionSet, sequence))
                .toList();
    }
}
