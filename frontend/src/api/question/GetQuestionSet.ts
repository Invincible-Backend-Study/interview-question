import {axiosInstance} from "@/api/AxiosInstance";
import {QuestionSetSearchResponse} from "@/types/questionSet";
import {END_POINT} from "@/constants/api";
import {Pageable, PageResponse} from "@/types/api";


export const getQuestionSet = async (pageable: Pageable) => {
  const {data} = await axiosInstance.get<PageResponse<QuestionSetSearchResponse>>(
    END_POINT.QUESTION_SETS(pageable)
  );

  return data;
}
