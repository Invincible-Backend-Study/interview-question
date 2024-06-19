import {OAuthProfileRequest, OAuthProfileResponse} from "@/types/auth";
import {useSuspenseQuery} from "@tanstack/react-query";
import {AxiosError} from "axios";
import {getOAuthProfile} from "@/api/auth/GetOAuthProfile";


export const useOAuthProfileQuery = (request: OAuthProfileRequest)  => {
  const {data: profile} = useSuspenseQuery<OAuthProfileResponse, AxiosError>({
    queryKey: ['oauthProfile' + request.providerId],
    queryFn: () => getOAuthProfile(request),
    gcTime: 60 * 60 * 60 * 60 * 10,
    staleTime: 60 * 60 * 60  * 60 * 10
  })
  return {profile};
}
