import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";
import {Pageable, PageResponse} from "@/types/api";
import {MyInterviewResponse} from "@/types/interview";


export const myInterview = async (pageRequest: Pageable) => {
  const {data} = await axiosInstance.get<PageResponse<MyInterviewResponse>>(
    END_POINT.INTERVIEWS_ME(pageRequest)
  );
  return data;
}
