import {PageResponse} from "@/types/api";

export interface QuestionSet {
  questionSetId: number;
  thumbnailUrl: string;
  title: string;
  description: string;
  defaultTailQuestionDepth: number;
}

export interface QuestionSetRow {
  questionSetId: number;
  thumbnailUrl: string;
  title: string;
  description: string;
  defaultTailQuestionDepth:number;
}

export type QuestionSetSaveBody = Omit<QuestionSetRow, "questionSetId" >



export interface QuestionSetResponse extends PageResponse<QuestionSet>{
}
