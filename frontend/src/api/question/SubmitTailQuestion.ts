import {TailQuestionSubmitRequest} from "@/types/tailQuestion";
import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";


export const submitTailQuestion = async (request: TailQuestionSubmitRequest) => {
  await axiosInstance.post(END_POINT.TAIL_QUESTION_SUBMIT, request);
}
