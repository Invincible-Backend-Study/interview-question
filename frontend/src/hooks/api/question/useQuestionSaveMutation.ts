import {useMutation} from "@tanstack/react-query";
import {postAdminQuestion} from "@/api/question/PostAdminQuestion";

export const useQuestionSaveMutation = () => {
  return useMutation({
    mutationKey: ['question save'],
    mutationFn: postAdminQuestion
  })
}
