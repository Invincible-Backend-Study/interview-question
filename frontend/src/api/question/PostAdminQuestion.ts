import {Question} from "@/types/admin/question";
import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";


export const postAdminQuestion = async (question: Question) => {
  await axiosInstance.post(END_POINT.admin.QUESTION, {...question, action: "CREATE"}) ;
}
