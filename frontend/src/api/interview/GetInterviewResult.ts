import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";
import {MyInterviewResult} from "@/types/interview";


export const getInterviewResult = async (interviewId: number) => {
  const {data} = await axiosInstance.get<MyInterviewResult>(END_POINT.INTERVIEWS_GET_RESULT(interviewId))
  return data;
}
