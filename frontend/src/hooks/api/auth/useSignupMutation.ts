import {useMutation} from "@tanstack/react-query";
import {signup} from "@/api/auth/Signup";
import {TOKEN} from "@/constants/api";
import {useNavigate} from "react-router-dom";
import {PATH} from "@/constants/path";


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
      alert("에러~");
    }
  })
}
