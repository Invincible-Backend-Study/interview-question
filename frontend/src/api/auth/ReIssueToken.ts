import {axiosInstance} from "@/api/AxiosInstance";
import {AccessTokenResponse} from "@/types/auth";
import {END_POINT} from "@/constants/api";


export const reIssueToken = async () => {
  const {data} = await axiosInstance.get<AccessTokenResponse>(END_POINT.TOKEN_REISSUE);
  return data;
}
