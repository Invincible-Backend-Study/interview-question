import {InterviewCreateRequest, InterviewCreateResponse} from "@/types/interview";
import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";


export const createInterview = async (request: InterviewCreateRequest ) => {
  const {data} = await axiosInstance.post<InterviewCreateResponse>(END_POINT.INTERVIEWS, request);

  return data;
}
