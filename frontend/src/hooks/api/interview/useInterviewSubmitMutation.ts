import {useMutation} from "@tanstack/react-query";
import submitInterview from "@/api/interview/SubmitInterview";


export const useInterviewSubmitMutation = () => {
  return useMutation({
    mutationKey: ['useInterviewSubmitMutation'],
    mutationFn: submitInterview,
    gcTime: 60 * 60 * 1000,
  })
}
