package in.backend.core.interview.presentation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import in.backend.acceptance.RequestFixtures.꼬리질문;
import in.backend.acceptance.RequestFixtures.인터뷰;
import in.backend.acceptance.RequestFixtures.인터뷰.답변요청;
import in.backend.core.interview.application.InterviewSubmitResult;
import in.backend.core.interview.presentation.payload.InterviewCreateRequest;
import in.backend.core.interview.presentation.payload.InterviewCreateResponse;
import in.backend.core.interview.presentation.payload.InterviewQuestionResponse;
import in.backend.core.interview.presentation.payload.InterviewSubmitRequest;
import in.backend.core.question.entity.AnswerState;
import in.backend.core.question.infrastrcuture.QuestionRepository;
import in.backend.core.question.presentation.payload.TailQuestionSubmitRequest;
import in.backend.core.question.presentation.payload.TailQuestionSubmitResponse;
import in.backend.core.questionset.entity.QuestionSetEntity;
import in.backend.core.questionset.infrastructure.QuestionSetRepository;
import in.backend.global.fixture.QuestionFixture;
import in.backend.global.fixture.QuestionSetFixture;
import in.backend.global.layer.AcceptanceTest;
import in.backend.global.layer.AcceptanceTest.LogIn;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


@LogIn
class InterviewApiAcceptanceTest extends AcceptanceTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionSetRepository questionSetRepository;

    private InterviewSubmitResult submit(Long interviewId, Long interviewQuestionId) {
        var interviewSubmitRequest = InterviewSubmitRequest.builder()
                .interviewId(interviewId)
                .interviewQuestionId(interviewQuestionId)
                .currentIndex(0)
                .score(1)
                .answerState(AnswerState.COMPLETE.toString())
                .aiFeedback("피드백")
                .tailQuestion("꼬리질문")
                .timeToAnswer(1)
                .answerContent("질문에 대한 답변")
                .build();

        return RestAssured.given().log().all()
                .body(interviewSubmitRequest)
                .when().log().all()
                .post("/api/interviews/submit")
                .then()
                .statusCode(200)
                .extract()
                .as(InterviewSubmitResult.class);
    }

    private InterviewQuestionResponse getCurrentInterviewQuestion(Long interviewId) {
        return RestAssured.given().log().all()
                .when().log().all()
                .get(STR."/api/interviews/\{interviewId}/current/problem")
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(InterviewQuestionResponse.class);
    }

    private Long createInterview(QuestionSetEntity questionSet, int totalProblemCount) {
        var interviewCreateRequest = InterviewCreateRequest.builder()
                .questionSetId(questionSet.getId())
                .tailQuestionDepth(5)
                .totalProblemCount(totalProblemCount)
                .build();

        return RestAssured.given().log().all()
                .body(interviewCreateRequest)
                .when().log().all()
                .post("/api/interviews")
                .then().log().all()
                .extract()
                .as(InterviewCreateResponse.class)
                .interviewId();
    }

    @Test
    void 인터뷰를_생성하고_풀어야_할_문제를_조회합니다() {

        // TODO question 생성 api 만들어지면 대체하기
        var questionSet = questionSetRepository.save(QuestionSetFixture.create());
        questionRepository.saveAll(QuestionFixture.creates(questionSet, 10));

        var interviewId = createInterview(questionSet, 10);
        var actual = getCurrentInterviewQuestion(interviewId);

        assertAll(
                () -> assertThat(actual.index()).isEqualTo(0),
                () -> assertThat(actual.interviewId()).isEqualTo(interviewId),
                () -> assertThat(actual.question()).isNotNull(),
                () -> assertThat(actual.remainTailQuestionCount()).isEqualTo(5),
                () -> assertThat(actual.size()).isEqualTo(10)
        );

    }

    @Test
    void 인터뷰를_생성하고_문제를_풀면_다음_질문을_받습니다() {
        // TODO question 생성 api 만들어지면 대체하기
        var questionSet = questionSetRepository.save(QuestionSetFixture.create());
        questionRepository.saveAll(QuestionFixture.creates(questionSet, 10));

        var interviewId = createInterview(questionSet, 10);

        var interviewQuestionResponse = getCurrentInterviewQuestion(interviewId);

        submit(interviewId, interviewQuestionResponse.interviewQuestionId());

        var actual = getCurrentInterviewQuestion(interviewId);

        assertAll(
                () -> assertThat(actual.index()).isEqualTo(1),
                () -> assertThat(actual.interviewId()).isEqualTo(interviewId),
                () -> assertThat(actual.question()).isNotNull(),
                () -> assertThat(actual.remainTailQuestionCount()).isEqualTo(5),
                () -> assertThat(actual.size()).isEqualTo(10)
        );

    }

    @Test
    void 인터뷰를_생성한_후_모든_문제를_풀면_더_이상_참여할_수_없습니다() {
        // TODO question 생성 api 만들어지면 대체하기
        var questionSet = questionSetRepository.save(QuestionSetFixture.create());
        questionRepository.saveAll(QuestionFixture.creates(questionSet, 1));

        var interviewId = createInterview(questionSet, 1);
        var interviewQuestionResponse = getCurrentInterviewQuestion(interviewId);

        submit(interviewId, interviewQuestionResponse.interviewQuestionId());

        RestAssured.given().log().all()
                .when().log().all()
                .get(STR."/api/interviews/\{interviewId}/current/problem")
                .then().log().all()
                .statusCode(400)
                .extract();
    }


    @Test
    void 인터뷰를_생성하고_문제를_푼_다음_꼬리질문에_답변합니다() {
        // TODO question 생성 api 만들어지면 대체하기
        var questionSet = questionSetRepository.save(QuestionSetFixture.create());
        questionRepository.saveAll(QuestionFixture.creates(questionSet, 10));

        var interviewId = createInterview(questionSet, 10);

        var interviewQuestionResponse = getCurrentInterviewQuestion(interviewId);

        var tailQuestionResponse = submit(interviewId, interviewQuestionResponse.interviewQuestionId());

        var tailQuestionSubmitRequest = TailQuestionSubmitRequest.builder()
                .interviewQuestionId(interviewId)
                .tailQuestionId(tailQuestionResponse.tailQuestionId())
                .answerState(AnswerState.COMPLETE.toString())
                .aiFeedback("피드백")
                .score(1)
                .tailQuestion("꼬리질문")
                .timeToAnswer(1)
                .answerContent("질문에 대한 답")
                .build();

        var tailQuestionId = RestAssured.given().log().all()
                .body(tailQuestionSubmitRequest)
                .when().log().all()
                .post("/api/tail-questions/submit")
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(TailQuestionSubmitResponse.class)
                .tailQuestionId();

        assertThat(tailQuestionId).isNotNull();

    }


    @Test
    void 인터뷰를_생성하고_문제를_푼_다음_꼬리질문에_답변하고_다시_꼬리질문에_답변합니다() {
        // TODO question 생성 api 만들어지면 대체하기
        var questionSet = questionSetRepository.save(QuestionSetFixture.create());
        questionRepository.saveAll(QuestionFixture.creates(questionSet, 10));

        var interviewId = createInterview(questionSet, 10);

        var interviewQuestionResponse = getCurrentInterviewQuestion(interviewId);

        var tailQuestionResponse = submit(interviewId, interviewQuestionResponse.interviewQuestionId());

        var tailQuestionSubmitRequest = TailQuestionSubmitRequest.builder()
                .interviewQuestionId(interviewId)
                .tailQuestionId(tailQuestionResponse.tailQuestionId())
                .answerState(AnswerState.COMPLETE.toString())
                .score(1)
                .aiFeedback("피드백")
                .tailQuestion("꼬리질문")
                .timeToAnswer(1)
                .answerContent("질문에 대한 답")
                .build();

        var tailQuestionId = RestAssured.given().log().all()
                .body(tailQuestionSubmitRequest)
                .when().log().all()
                .post("/api/tail-questions/submit")
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(TailQuestionSubmitResponse.class)
                .tailQuestionId();

        assertThat(tailQuestionId).isNotNull();

        TailQuestionSubmitRequest.builder()
                .interviewQuestionId(interviewId)
                .tailQuestionId(tailQuestionId)
                .answerState(AnswerState.COMPLETE.toString())
                .score(1)
                .aiFeedback("피드백")
                .tailQuestion("꼬리질문")
                .timeToAnswer(1)
                .answerContent("질문에 대한 답")
                .build();

        RestAssured.given().log().all()
                .body(tailQuestionSubmitRequest)
                .when().log().all()
                .post("/api/tail-questions/submit")
                .then().log().all()
                .statusCode(200)
                .body("tailQuestionId", Matchers.notNullValue());
    }


    /**
     * <pre>
     * scenario
     * - 1. 질문을 받은 다음 두 번 답변한 다음 패스 함
     * - 2. 받은 질문을 제출 함
     * - 3. 다시 패스 함
     * </pre>
     */
    @Test
    void 테스트_시나리오1() {
        var questionSet = questionSetRepository.save(QuestionSetFixture.create());
        questionRepository.saveAll(QuestionFixture.creates(questionSet, 10));
        var interviewId = createInterview(questionSet, 10);

        var interviewQuestionId = 인터뷰.질문_가져오기(interviewId).interviewQuestionId();
        var tailQuestionId1 = 인터뷰.제출(답변요청.완료(interviewId, interviewQuestionId, 0)).tailQuestionId();
        var tailQuestionId2 = 꼬리질문.제출(꼬리질문.답변요청.완료(interviewQuestionId, tailQuestionId1)).tailQuestionId();
        var tailQuestionId3 = 꼬리질문.제출(꼬리질문.답변요청.완료(interviewQuestionId, tailQuestionId2)).tailQuestionId();

        꼬리질문.제출(꼬리질문.답변요청.패스(interviewId, tailQuestionId3));

        var interviewQuestionId2 = 인터뷰.질문_가져오기(interviewId).interviewQuestionId();

        var tailQuestionId4 = 인터뷰.제출(답변요청.완료(interviewId, interviewQuestionId2, 1)).tailQuestionId();
        var tailQuestionId5 = 꼬리질문.제출(꼬리질문.답변요청.완료(interviewQuestionId2, tailQuestionId4)).tailQuestionId();

        assertThat(tailQuestionId5).isNotNull();


    }
}