export interface QuestionSetSearchResponse {
  /**
   * question set id
   */
  questionSetId: number;

  /**
   * question set title
   */
  title: string;

  /**
   * 설명
   */
  description: string;

  /**
   * problem count
   */
  count: number;

  thumbnailUrl: string;

  tailQuestionDepth: number;
}


export interface QuestionSet {
  questionSetId: number;
  title: string;
  description: string;
  count: number;
  tailQuestionDepth: number;
  thumbnailUrl: string;
}


