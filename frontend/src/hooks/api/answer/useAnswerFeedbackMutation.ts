import {useMutation} from "@tanstack/react-query";


interface AIFeedbackResponse {
  tailQuestion: string;
  originalContent: string;
  score: number;
  feedback: string;
}
interface AIFeedbackRequest {
  question: string;
  answer: string;
}


const mock = (request: AIFeedbackRequest): Promise<AIFeedbackResponse> => {
  return new Promise((resolve, reject) => {
    resolve({
      tailQuestion: "꼬리질문입니다",
      originalContent: "원본 컨텐츠 입니다",
      score: 100,
      feedback: "피드백입니다",
    });
  })
}

const useAnswerFeedbackMutation = () => {
  return useMutation({
    mutationKey: ['answerFeedback'],
    mutationFn: mock,
    gcTime: 60 * 60 * 1000,
  })

}

export default useAnswerFeedbackMutation;
