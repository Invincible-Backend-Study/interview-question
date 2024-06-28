import {useMutation} from "@tanstack/react-query";
import {signin} from "@/api/auth/Signin";
import {useNavigate} from "react-router-dom";
import {TOKEN} from "@/constants/api";
import {PATH} from "@/constants/path";
import {useRecoilState} from "recoil";
import {isLoggedInState} from "@/store/auth";


export const useSignInMutation = () => {
  const navigate = useNavigate();
  const [_, setLoggedIn] = useRecoilState(isLoggedInState);
  return useMutation({
    mutationKey: ["signin"],
    mutationFn: signin,
    gcTime: 60 * 60 * 60 * 1000,
    onSuccess: ({accessToken}) => {
      localStorage.setItem(TOKEN.ACCESS, accessToken);
      setLoggedIn(true)
      navigate(PATH.MAIN_PAGE)
    },
    onError: () => {
      throw Error("알 수 없는 이유로 로그인에 실패했습니다.");
    }
  })
}
