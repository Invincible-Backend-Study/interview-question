import {PageResponse} from "@/types/api";

export interface QuestionSet {
  questionSetId: number;
  thumbnailUrl: string;
  title: string;
  description: string;
  defaultTailQuestionCount: number;
  defaultTailQuestionDepth: number;
}

export interface QuestionSetRow {
  questionSetId: number;
  thumbnailUrl?: string;
  title: string;
  description: string;
  defaultTailQuestionCount: number;
}


export interface QuestionSetResponse extends PageResponse<QuestionSet>{
}
