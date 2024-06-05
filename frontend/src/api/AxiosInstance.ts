import axios from "axios";
import {NETWORK} from "@/constants/api";

export const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/api/v1',
  timeout: NETWORK.TIMEOUT,
  withCredentials: true
})
