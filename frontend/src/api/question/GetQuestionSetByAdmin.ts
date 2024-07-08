import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";
import {Pageable} from "@/types/api";
import {QuestionSetResponse} from "@/types/admin/questionSet";


export const getQuestionSetByAdmin = async (pageable: Pageable) => {
  const {data} = await axiosInstance.get<QuestionSetResponse>(END_POINT.admin.QUESTION_SETS(pageable))
  return data;
}

