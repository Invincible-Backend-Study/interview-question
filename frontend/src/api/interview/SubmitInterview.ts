import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";
import {InterviewSubmitRequest} from "@/types/interview";


const submitInterview = async (request: InterviewSubmitRequest) => {
  await axiosInstance.post(END_POINT.INTERVIEWS_SUBMIT, request);
}

export default submitInterview;
