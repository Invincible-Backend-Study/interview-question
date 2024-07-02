import {AnswerState} from "@/types/answer";

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
   * 답변에 걸린 시간
   */
  timeToAnswer: number;

  /**
   * 내가 작성한 답변
   */
  answerContent: string;


  /**
   * 몇점인지
   */
  score: number;
}

export interface InterviewSubmitResponse {
  tailQuestionId: number | null;
}

export type InterviewState =  "PROGRESS" | "DONE";
export interface MyInterviewResponse {
  interviewId: number;
  title: string;
  interviewState: InterviewState;
  questionCount: number;
  createdAt: string;
  updatedAt: string;
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
  interviewState: InterviewState
  questionCount: number;
  createdAt: string;
  updatedAt: string;
}


export interface MyInterviewResult {
  interviewId: number;
  title: string;

  interviewState: InterviewState
  interviewQuestions: InterviewQuestionDetail[]
}

export interface InterviewQuestionDetail {
  interviewQuestionId: number;
  answerState: "INIT" | "PASS" | "COMPLETE",
  question: string;
  answer: string;
  referenceLinks: string;
  feedback: string;
  remainTailQuestionCount: number;
  score: number;
  tailQuestions: TailQuestionDetail[]
}

export interface TailQuestionDetail {
  tailQuestionId: number;
  answerState: "INIT" | "PASS" | "COMPLETE";
  question: string;
  answer: string;
  score: number;
  feedback: string;
}



export interface FeedbackRequest{
  question: string;
  answer: string;
}

export interface FeedbackResponse {
  score: number;
  feedback: string;
  tailQuestion: string;
}
