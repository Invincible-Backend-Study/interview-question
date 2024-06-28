import {useMutation} from "@tanstack/react-query";
import {FeedbackRequest} from "@/types/interview";
import {requestInterviewFeedback} from "@/api/interview/RequestInterviewFeedback";


export const useInterviewFeedbackMutation = () => {
  return useMutation({
    mutationKey: ['interview feedback'],
    mutationFn: requestInterviewFeedback
  });
}
