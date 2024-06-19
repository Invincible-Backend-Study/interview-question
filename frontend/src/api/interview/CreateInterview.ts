import {InterviewCreateRequest, InterviewCreateResponse} from "@/types/interview";
import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";


export const createInterview = (request: InterviewCreateRequest ) => {
  return axiosInstance.post<InterviewCreateResponse>(END_POINT.INTERVIEWS, request);
}
