import {axiosInstance} from "@/api/AxiosInstance";


export interface AnswerQuestionRequest {
  question: string;
  response: string;
}

export interface AnswerQuestionResponse {
  result: string;
}

export const answerQuestionPost = async (request: AnswerQuestionRequest) => {
  const {data: interviewResult} = await axiosInstance.post<AnswerQuestionResponse>("/interview", request);

  return interviewResult;
}
