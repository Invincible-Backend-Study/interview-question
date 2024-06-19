import axios from "axios";
import {NETWORK} from "@/constants/api";
import {checkAndSetToken, handleTokenError} from "@/api/Interceptors";

export const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: NETWORK.TIMEOUT,
  withCredentials: true,
  useAuth: true
})

axiosInstance.interceptors.request.use(checkAndSetToken);
axiosInstance.interceptors.response.use(response => response, handleTokenError);
