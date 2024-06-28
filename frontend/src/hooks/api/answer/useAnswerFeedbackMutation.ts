import {useMutation} from "@tanstack/react-query";
import {requestInterviewFeedback} from "@/api/interview/RequestInterviewFeedback";


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
    setTimeout(() => {
      resolve({
        tailQuestion: "꼬리질문입니다",
        originalContent: "원본 컨텐츠 입니다",
        score: 100,
        feedback: "피드백입니다",
      });
    }, parseInt(String(Math.random() * 10000)))
  })
}

const useAnswerFeedbackMutation = () => {
  return useMutation({
    mutationKey: ['answerFeedback'],
    mutationFn: requestInterviewFeedback,
    gcTime: 60 * 60 * 1000,
  })

}

export default useAnswerFeedbackMutation;
