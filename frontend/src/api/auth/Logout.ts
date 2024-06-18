import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";


export const Logout = async () => {
  await axiosInstance.delete(END_POINT.LOGOUT);
}
