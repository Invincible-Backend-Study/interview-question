import {useSuspenseQuery} from "@tanstack/react-query";
import {getMe} from "@/api/member/GetMe";


export const useMyProfileQuery = () => {
  const {data: profile} = useSuspenseQuery({
    queryKey: ['myProfile'],
    queryFn: () => getMe(),
    gcTime: 60 * 60 * 60 * 10,
    staleTime: 60 * 60 * 60 * 10,
  })

  return {profile};
}
