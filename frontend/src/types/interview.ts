import {AnswerState} from "@/types/answer";
import {Interview} from "@/types/question";

/**
 * 인터뷰 생성 요청
 */
export interface InterviewCreateRequest{
  /**
   * 사용할 question-set id
   */
  questionSetId: number;

  /**
   *  꼬리질문 depth
   */
  tailQuestionDepth: number;

  /**
   * 면접에 포함시킬 문제 숫자
   */
  totalProblemCount: number;

  timeToAnswer?:number;

  timeToThink?:number;
}

export interface InterviewCreateResponse {
  /**
   * 인터뷰 id
   * */
  interviewId: number;
}

/**
 * 인터뷰 질문 답변 요청
 */

export interface InterviewSubmitRequest {

  /**
   * 인터뷰 id
   */
  interviewId: number;

  /**
   * 인터뷰 질문 id
   */
  interviewQuestionId: number;

  currentIndex: number;
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
   * GPT가 넘겨준 답변 전체
   */
  originalContent: string;

  /**
   * 답변에 걸린 시간
   */
  timeToAnswer: number;

  /**
   * 내가 작성한 답변
   */
  answerContent: string;

}

export interface InterviewSubmitResponse {
  tailQuestionId: number | null;
}

export type InterviewState =  "INIT" | "STOP" | "COMPLETE";
export interface MyInterviewResponse {
  interviewId: number;
  title: string;
  interviewState: InterviewState;
  questionCount: number;
  createdAt: Date;
  updatedAt: Date;
}


export interface InterviewQuestionResponse {
  interviewId: number;
  interviewQuestionId: number;
  question: string;
  index: number;
  size: number;
  remainTailQuestionCount: number;
}


export interface InterviewCreateFormCommand {
  questionSetId: number;
  count: number;
  tailQuestionDepth: number;
}

export interface InterviewSettings{
  questionSetId: number;
  count: number;
  tailQuestionDepth: number;
}



export interface MyInterview {
  interviewId: number;
  title: string;
  interviewState: "INIT" | "STOP" | "COMPLETE";
  questionCount: number;
  createdAt: Date;
  updatedAt: Date;
}


export interface MyInterviewResult {
  interviewId: number;
  title: string;
  interviewState: "INIT" | "STOP" | "COMPLETE";
  interviewQuestions: InterviewQuestionDetail[]
}

interface InterviewQuestionDetail {
  interviewQuestionId: number;
  answerState: "INIT" | "PASS" | "COMPLETE",
  question: string;
  answer: string;
  referenceLinks: string;
  feedback: string;
  remainTailQuestionCount: number;
  tailQuestions: TailQuestionDetail[]
}

interface TailQuestionDetail {
  tailQuestionId: number;
  answerState: "INIT" | "PASS" | "COMPLETE";
  question: string;
  answer: string;
  feedback: string;
}
