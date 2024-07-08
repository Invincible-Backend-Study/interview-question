package in.backend.acceptance;

import in.backend.core.interview.application.InterviewSubmitResult;
import in.backend.core.interview.presentation.payload.InterviewQuestionResponse;
import in.backend.core.interview.presentation.payload.InterviewSubmitRequest;
import in.backend.core.question.entity.AnswerState;
import in.backend.core.question.presentation.payload.TailQuestionSubmitRequest;
import in.backend.core.question.presentation.payload.TailQuestionSubmitResponse;
import io.restassured.RestAssured;

public class RequestFixtures {

    public static class 인터뷰 {
        public static InterviewQuestionResponse 질문_가져오기(Long interviewId) {
            return RestAssured.given().log().all()
                    .when().log().all()
                    .get(STR."/api/interviews/\{interviewId}/current/problem")
                    .then().log().all()
                    .statusCode(200)
                    .extract()
                    .as(InterviewQuestionResponse.class);
        }

        public static InterviewSubmitResult 제출(InterviewSubmitRequest interviewSubmitRequest) {
            return RestAssured.given().log().all()
                    .body(interviewSubmitRequest)
                    .when().log().all()
                    .post("/api/interviews/submit")
                    .then()
                    .statusCode(200)
                    .extract()
                    .as(InterviewSubmitResult.class);
        }

        public static class 답변요청 {
            public static InterviewSubmitRequest 완료(Long interviewId, Long interviewQuestionId, int index) {
                return InterviewSubmitRequest.builder()
                        .interviewId(interviewId)
                        .currentIndex(index)
                        .interviewQuestionId(interviewQuestionId)
                        .answerState(AnswerState.COMPLETE.name())
                        .tailQuestion("꼬리 질문")
                        .timeToAnswer(1)
                        .aiFeedback("")
                        .answerContent("")
                        .score(1)
                        .build();
            }

            public static InterviewSubmitRequest 패스(Long interviewId, Long interviewQuestionId, int index) {
                return InterviewSubmitRequest.builder()
                        .interviewId(interviewId)
                        .currentIndex(index)
                        .interviewQuestionId(interviewQuestionId)
                        .answerState(AnswerState.PASS.name())
                        .timeToAnswer(1)
                        .score(0)
                        .build();

            }
        }
    }

    public static class 꼬리질문 {
        public static TailQuestionSubmitResponse 제출(TailQuestionSubmitRequest tailQuestionSubmitRequest) {
            return RestAssured.given().log().all()
                    .body(tailQuestionSubmitRequest)
                    .when().log().all()
                    .post("/api/tail-questions/submit")
                    .then().log().all()
                    .statusCode(200)
                    .extract()
                    .as(TailQuestionSubmitResponse.class);
        }

        public static class 답변요청 {
            public static TailQuestionSubmitRequest 완료(Long interviewQuestionId, Long tailQuestionId) {
                return TailQuestionSubmitRequest.builder()
                        .interviewQuestionId(interviewQuestionId)
                        .tailQuestionId(tailQuestionId)
                        .answerState(AnswerState.COMPLETE.toString())
                        .score(1)
                        .aiFeedback("피드백")
                        .tailQuestion("꼬리질문")
                        .timeToAnswer(1)
                        .answerContent("질문에 대한 답")
                        .build();
            }

            public static TailQuestionSubmitRequest 패스(Long interviewQuestionId, Long tailQuestionId) {
                return TailQuestionSubmitRequest.builder()
                        .interviewQuestionId(interviewQuestionId)
                        .tailQuestionId(tailQuestionId)
                        .score(0)
                        .answerState(AnswerState.PASS.toString())
                        .timeToAnswer(1)
                        .build();
            }

        }
    }
}
