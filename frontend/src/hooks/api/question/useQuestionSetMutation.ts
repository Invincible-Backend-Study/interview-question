import {useMutation} from "@tanstack/react-query";
import {postAdminQuestionSet} from "@/api/question/PostAdminQuestionSet";
import {toast} from "sonner";

export const useQuestionSetMutation = () => {
  return useMutation({
    mutationKey: ['save question set'],
    mutationFn: postAdminQuestionSet,
    onError: error => {
      toast.error(error.message);
    }
  })
}
