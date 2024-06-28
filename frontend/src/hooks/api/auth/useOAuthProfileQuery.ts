import {OAuthProfileRequest, OAuthProfileResponse} from "@/types/auth";
import {useSuspenseQuery} from "@tanstack/react-query";
import {AxiosError} from "axios";
import {getOAuthProfile} from "@/api/auth/GetOAuthProfile";
import {useNavigate} from "react-router-dom";
import {PATH} from "@/constants/path";


export const useOAuthProfileQuery = (request: OAuthProfileRequest)  => {
  const navigate = useNavigate()
  const {data: profile, isError} = useSuspenseQuery<OAuthProfileResponse, AxiosError>({
    queryKey: ['oauthProfile' + request.providerId],
    queryFn: () => getOAuthProfile(request),
    gcTime: 60 * 60 * 60 * 60 * 10,
    staleTime: 60 * 60 * 60  * 60 * 10
  })
  if(isError) {
    navigate(PATH.AUTH);
  }
  return {profile};
}
