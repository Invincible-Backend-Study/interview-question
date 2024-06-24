import {useMutation} from "@tanstack/react-query";
import {logout} from "@/api/auth/Logout";
import {useNavigate} from "react-router-dom";
import {TOKEN} from "@/constants/api";
import {PATH} from "@/constants/path";
import {toast} from "sonner";


export const useLogoutMutation = () => {
  const navigate = useNavigate();
  return useMutation({
    mutationKey: ["logout"],
    mutationFn: logout,
    gcTime: 60 * 60 * 1000,
    onSuccess:() => {
      toast.info("잘가요~");
      localStorage.removeItem(TOKEN.ACCESS);
      navigate(PATH.AUTH)
    },
    onError: () => {

    }
  });
}
