import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";
import {AccessTokenResponse, SignupRequest} from "@/types/auth";


export const signup = async (request: SignupRequest) => {
  const {data} = await  axiosInstance.post<AccessTokenResponse>(END_POINT.SIGNUP, request, {
    useAuth:false
  });

  return data;
}
