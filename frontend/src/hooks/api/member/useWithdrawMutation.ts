import {useMutation} from "@tanstack/react-query";
import {deleteMe} from "@/api/member/deleteMe";

export const useWithdrawMutation = () => {
  return useMutation({
    mutationFn: deleteMe,
    mutationKey: ['withdraw me']
  })
}
