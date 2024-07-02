import {FallbackProps} from "react-error-boundary";
import {HTTPError} from "@/api/Interceptors";
import {PATH} from "@/constants/path";

const ErrorFallback= ({ error}: FallbackProps) => {

  if(error instanceof  HTTPError) {
    if(error.code === 403) {
      location.assign(PATH.MAIN_PAGE);
      return;
    }
  }

  return (
    <div className="h-screen">
      <h2>에러가 발생했어요</h2>
      <p>{error.message}</p>
    </div>
  );
};


export default ErrorFallback;
