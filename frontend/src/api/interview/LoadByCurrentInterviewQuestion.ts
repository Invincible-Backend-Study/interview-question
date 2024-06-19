import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";
import {InterviewQuestionResponse} from "@/types/interview";


export const loadByCurrentInterviewQuestion = async (interviewId: number) => {
  const {data} = await axiosInstance.get<InterviewQuestionResponse>(END_POINT.INTERVIEWS_GET_PROBLEM(interviewId))
  return data;
}
