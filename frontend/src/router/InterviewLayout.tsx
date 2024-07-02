import {Outlet, useNavigate} from "react-router-dom";
import {useRecoilState} from "recoil";
import {isLoggedInState} from "@/store/auth";
import {useEffect} from "react";
import {PATH} from "@/constants/path";
import {ErrorBoundary} from "react-error-boundary";
import ErrorFallback from "@/components/ErrorFallback/ErrorFallback";


const InterviewLayout = () => {
  const [isLoggedIn,] = useRecoilState(isLoggedInState);
  const navigate = useNavigate();

  useEffect(() => {
    if(!isLoggedIn) {
      navigate(PATH.MAIN_PAGE)
    }
  }, [isLoggedIn])

  return (
    <ErrorBoundary fallbackRender={ErrorFallback}>
      <Outlet/>
    </ErrorBoundary>
  )
}


export default InterviewLayout;
