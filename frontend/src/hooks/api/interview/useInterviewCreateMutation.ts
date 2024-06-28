import {useMutation} from "@tanstack/react-query";
import {createInterview} from "@/api/interview/CreateInterview";
import {useNavigate} from "react-router-dom";
import {PATH} from "@/constants/path";
import {toast} from "sonner";


export const useInterviewCreateMutation = () => {
  const navigate = useNavigate();
  return useMutation({
    mutationKey: ['create interview'],
    mutationFn: createInterview,
    onSuccess: ({interviewId}) => {
      navigate(PATH.INTERVIEW(interviewId));
    },
    onError:(error) => {
      console.log(error.message);
      toast.error(error.message)
    }

  })
}
