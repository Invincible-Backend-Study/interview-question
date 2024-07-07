import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";
import {QuestionSearchResponse} from "@/types/admin/question";


export const getQuestionsByAdmin = async (questionSetId: number | undefined) => {
  if(questionSetId === undefined){
    return []
  }
  const {data} = await axiosInstance.get<QuestionSearchResponse>(END_POINT.admin.QUESTIONS({questionSetId}))

  return data.questions;
}
