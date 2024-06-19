

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

  tailQuestionDepth: number;
}
