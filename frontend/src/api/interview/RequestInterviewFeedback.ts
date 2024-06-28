import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";
import {FeedbackRequest, FeedbackResponse} from "@/types/interview";


export const requestInterviewFeedback = async (request: FeedbackRequest) => {
  const {data} = await axiosInstance.post<FeedbackResponse>(END_POINT.INTERVIEWS_FEEDBACK, request)
  return data;
}
