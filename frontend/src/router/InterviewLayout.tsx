import {Outlet, useNavigate} from "react-router-dom";
import {useRecoilState} from "recoil";
import {isLoggedInState} from "@/store/auth";
import {useEffect} from "react";
import {PATH} from "@/constants/path";
import {ErrorBoundary, FallbackProps} from "react-error-boundary";
import {HTTPError} from "@/api/Interceptors";

const FallbackComponent = ({ error, resetErrorBoundary }: FallbackProps) => {

  console.log(">>>", error);
  if(error instanceof  HTTPError) {
    if(error.code === 403) {
      location.assign(PATH.MAIN_PAGE);
      return;
    }
  }

  return (
    <div>
      <h2>잘못된 접근 방식</h2>
      <p>{error.message}</p>
      <button onClick={resetErrorBoundary}>다시 시도</button>
    </div>
  );
};


const InterviewLayout = () => {
  const [isLoggedIn,] = useRecoilState(isLoggedInState);
  const navigate = useNavigate();

  useEffect(() => {
    if(!isLoggedIn) {
      navigate(PATH.MAIN_PAGE)
    }
  }, [isLoggedIn])

  return (
    <ErrorBoundary fallbackRender={FallbackComponent}>
      <Outlet/>
    </ErrorBoundary>
  )
}


export default InterviewLayout;
