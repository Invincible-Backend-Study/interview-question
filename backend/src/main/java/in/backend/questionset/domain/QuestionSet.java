package in.backend.questionset.domain;

import in.backend.question.domain.Question;
import java.util.List;

/**
 * 질문셋
 * <p>- 면접과 다른 개념임</p>
 * - 면접 시작을 하면 QuestionSet에서 면접을 만들어내는 것
 */
public class QuestionSet {
    private Long id;
    private String title;
    private String author;
    private List<Question> questions;
}
