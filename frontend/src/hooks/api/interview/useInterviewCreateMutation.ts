import {InterviewSettings} from "@/types/interview";
import {useMutation} from "@tanstack/react-query";
import {createInterview} from "@/api/interview/CreateInterview";
import {useNavigate} from "react-router-dom";
import {PATH} from "@/constants/path";


export const useInterviewCreateMutation = () => {
  const navigate = useNavigate();
  return useMutation({
    mutationKey: ['create interview'],
    mutationFn: createInterview,
    onSuccess: ({interviewId}) => {
      navigate(PATH.INTERVIEW(interviewId));
    },

  })
}
