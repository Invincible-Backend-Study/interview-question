import {useMutation} from "@tanstack/react-query";
import {logout} from "@/api/auth/Logout";
import {useNavigate} from "react-router-dom";
import {TOKEN} from "@/constants/api";
import {PATH} from "@/constants/path";
import {toast} from "sonner";
import {useRecoilState} from "recoil";
import {isLoggedInState} from "@/store/auth";


export const useLogoutMutation = () => {
  const navigate = useNavigate();
  const [_, setLoggedIn] = useRecoilState(isLoggedInState);
  return useMutation({
    mutationKey: ["logout"],
    mutationFn: logout,
    gcTime: 60 * 60 * 1000,
    onSuccess:() => {
      setLoggedIn(false);
      localStorage.removeItem(TOKEN.ACCESS);
      toast.success("잘가요~");
      navigate(PATH.AUTH);
    },
    onError: () => {

    }
  });
}
