import {AnswerState} from "@/types/answer";

/**
 * 꼬리 질문 답변
 */
export interface TailQuestionSubmitRequest {
  /**
   * 인터뷰 질문 id
   */
  interviewQuestionId: number;
  /**
   * 꼬리 질문 아이디
   */
  tailQuestionId: number;

  /**
   * 답변 상태
   */
  answerState: AnswerState;

  /**
   * GPT가 넘겨준 ai feedback
   */
  aiFeedback: string;
  /**
   * GPT가 넘겨준 꼬리질문
   */
  tailQuestion: string;

  /**
   * 답변에 걸린 시간
   */
  timeToAnswer?: number;

  /**
   * 내가 작성한 답변
   */
  answerContent: string;

}

export interface TailQuestionSubmitResponse {
  tailQuestionId: number | null;
}
