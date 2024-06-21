import {useMutation} from "@tanstack/react-query";
import {submitTailQuestion} from "@/api/question/SubmitTailQuestion";


export const useTailQuestionSubmitMutation = () => {
  return useMutation({
    mutationKey: ['tail question submit'],
    mutationFn: submitTailQuestion,
    gcTime: 60 * 60 * 1000,
  })
}

export default useTailQuestionSubmitMutation;
