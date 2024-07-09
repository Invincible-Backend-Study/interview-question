import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";

export const deleteMe = async () => {
  await axiosInstance.delete(END_POINT.MEMBERS);
}
