
export interface Question {
  questionId: number;
  questionSetId: number;
  question: string;
  sequence: number;
}


export interface QuestionSearchResponse {
  questions: Question[]
}


export interface QuestionRow{
  questionId: number;
  questionSetId: number;
  question: string;
  sequence: number;
}
