import {useMutation} from "@tanstack/react-query";
import {signup} from "@/api/auth/Signup";
import {TOKEN} from "@/constants/api";
import {useNavigate} from "react-router-dom";
import {PATH} from "@/constants/path";
import {toast} from "sonner";


export const useSignupMutation = () => {
  const navigate = useNavigate();
  return useMutation({
    mutationKey: ['signup'],
    mutationFn: signup,
    onSuccess: ({accessToken})=> {
      localStorage.setItem(TOKEN.ACCESS, accessToken);
      navigate(PATH.MAIN_PAGE)
    },
    gcTime: 100000,
    onError: ()=>{
      toast.error("에러 발생")
    }
  })
}
