package in.backend.global.fixture;


import in.backend.core.question.entity.QuestionEntity;
import in.backend.core.questionset.domain.QuestionSetEntity;
import java.util.List;
import java.util.stream.IntStream;

public class QuestionEntityFixture {

    public static QuestionEntity create(QuestionSetEntity questionSet) {
        return QuestionEntity.builder()
                .content("질문")
                .questionSet(questionSet)
                .sequence(0)
                .build();
    }

    public static List<QuestionEntity> create(QuestionSetEntity questionSet, int count) {

        return IntStream.iterate(1, i -> i + 1)
                .limit(count)
                .mapToObj(i -> QuestionEntity.builder()
                        .content(STR."질문\{i}")
                        .questionSet(questionSet)
                        .sequence(i)
                        .referenceLinks("")
                        .build()
                )
                .toList();

    }
}
