import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";
import {InterviewSubmitRequest, InterviewSubmitResponse} from "@/types/interview";


const submitInterview = async (request: InterviewSubmitRequest) => {
  const {data} = await axiosInstance.post<InterviewSubmitResponse>(END_POINT.INTERVIEWS_SUBMIT, request);
  return data;
}

export default submitInterview;
