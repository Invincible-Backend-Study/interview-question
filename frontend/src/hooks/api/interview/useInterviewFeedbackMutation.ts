import {useMutation} from "@tanstack/react-query";
import {requestInterviewFeedback} from "@/api/interview/RequestInterviewFeedback";


export const useInterviewFeedbackMutation = () => {
  return useMutation({
    mutationKey: ['interview feedback'],
    mutationFn: requestInterviewFeedback
  });
}
