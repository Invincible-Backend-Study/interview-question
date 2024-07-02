import {AxiosError, InternalAxiosRequestConfig} from "axios";
import {TOKEN} from "@/constants/api";
import {PATH} from "@/constants/path";
import {reIssueToken} from "@/api/auth/ReIssueToken";
import {axiosInstance} from "@/api/AxiosInstance";

export interface ErrorResponseData {
  statusCode: number;
  message: string;
  code: number;
}

export class HTTPError {
  constructor(readonly statusCode: number, readonly message: string, readonly code: number) {
  }
}

export const checkAndSetToken = (config: InternalAxiosRequestConfig) => {

  if(!config.useAuth || !config.headers || config.headers.Authorization) return config;

  const accessToken = localStorage.getItem(TOKEN.ACCESS);

  if(!accessToken){
    window.location.href = PATH.AUTH
    throw new Error("유효하지 않은 토큰입니다.");
  }

  config.headers.Authorization = TOKEN.bearer(accessToken);
  return config;
}

export const handleTokenError = async(error: AxiosError<ErrorResponseData>) => {
  const originalRequest = error.config;

  if (!error.response || !originalRequest) throw new Error('에러가 발생했습니다.');

  const { data, status } = error.response;

  if (status === 401 && data.code === 10003){
    const { accessToken } = await reIssueToken();
    originalRequest.headers.Authorization = TOKEN.bearer(accessToken);
    localStorage.setItem(TOKEN.ACCESS, accessToken);

    return axiosInstance(originalRequest);
  }

  if (
    status === 401 && data.code >= 10000
  ) {
    localStorage.removeItem(TOKEN.ACCESS);
    window.location.href = PATH.AUTH;
  }
  throw new HTTPError(status, data.message, data.code);

}
