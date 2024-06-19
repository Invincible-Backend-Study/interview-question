import {AccessTokenResponse, SigninRequest} from "@/types/auth";
import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";


export const signin = async (request: SigninRequest) => {
  const {data} = await axiosInstance.post<AccessTokenResponse>(END_POINT.SIGNIN, request, {
    useAuth: false
  });
  return data;
}
