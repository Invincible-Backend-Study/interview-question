import axios from "axios";
import {NETWORK} from "@/constants/api";
import {checkAndSetToken, handleTokenError} from "@/api/Interceptors";

export const axiosInstance = axios.create({
  baseURL: `${import.meta.env.VITE_API}/api`,
  timeout: NETWORK.TIMEOUT,
  withCredentials: true,
  useAuth: true
})

axiosInstance.interceptors.request.use(checkAndSetToken);
axiosInstance.interceptors.response.use(response => response, handleTokenError);
