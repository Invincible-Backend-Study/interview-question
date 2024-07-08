import axios from "axios";
import {NETWORK} from "@/constants/api";
import {checkAndSetToken, delayFulfilled, handleTokenError, waitingFulfilled} from "@/api/Interceptors";

export const axiosInstance = axios.create({
  baseURL: `${import.meta.env.VITE_API}/api`,
  timeout: NETWORK.TIMEOUT,
  withCredentials: true,
  useAuth: true,
})

axiosInstance.interceptors.request.use(checkAndSetToken);
axiosInstance.interceptors.request.use(delayFulfilled);
axiosInstance.interceptors.response.use(waitingFulfilled, handleTokenError);
