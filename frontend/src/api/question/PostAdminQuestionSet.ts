import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";
import {QuestionSetSaveBody} from "@/types/admin/questionSet";


interface QuestionSaveResponse{
  questionSetId: number;
}

export const postAdminQuestionSet = async (body: QuestionSetSaveBody) => {
  const { data } = await axiosInstance.post<QuestionSaveResponse>(END_POINT.admin.QUESTION_SET, body);
  return data;
}
