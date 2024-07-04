import 'axios';

declare module 'axios' {
  export interface AxiosRequestConfig {
    useAuth: boolean;
  }

  export interface InternalAxiosRequestConfig {
    p0: number;
  }
}
