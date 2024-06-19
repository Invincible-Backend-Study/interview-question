import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";
import {MyProfileResponse} from "@/types/member";


export const getMe = async () => {
  const {data} = await axiosInstance.get<MyProfileResponse>(END_POINT.ME)

  return data;
}
