package in.backend.interview.domain;


import in.backend.question.domain.Question;
import java.util.List;

public class Interview {
    private Long interviewId;
    private Long userId;

    private String title;
    private int questionCount;
    private List<Question> questions;

    private int tailQuestionDepth;

    // 질문 당 부여된 시간
    private int secondsPerGivenQuestion;

}
