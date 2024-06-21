import {TailQuestionSubmitRequest, TailQuestionSubmitResponse} from "@/types/tailQuestion";
import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";


export const submitTailQuestion = async (request: TailQuestionSubmitRequest) => {
  const {data} = await axiosInstance.post<TailQuestionSubmitResponse>(END_POINT.TAIL_QUESTION_SUBMIT, request);
  return data;
}
