import {useMutation} from "@tanstack/react-query";
import {logout} from "@/api/auth/Logout";
import {useNavigate} from "react-router-dom";
import {TOKEN} from "@/constants/api";
import {PATH} from "@/constants/path";


export const useLogoutMutation = () => {
  const navigate = useNavigate();
  return useMutation({
    mutationKey: ["logout"],
    mutationFn: logout,
    gcTime: 60 * 60 * 1000,
    onSuccess:() => {
      localStorage.removeItem(TOKEN.ACCESS);
      navigate(PATH.AUTH)
    },
    onError: () => {

    }
  });
}
