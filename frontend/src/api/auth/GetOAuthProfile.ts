import {OAuthProfileRequest, OAuthProfileResponse} from "@/types/auth";
import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";


export const getOAuthProfile = async (request: OAuthProfileRequest) => {
  const {data} = await axiosInstance.get<OAuthProfileResponse>(END_POINT.PROFILE(request));

  return data;
}
