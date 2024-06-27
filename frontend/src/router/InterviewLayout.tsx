import {Outlet, useNavigate} from "react-router-dom";
import {useRecoilState} from "recoil";
import {isLoggedInState} from "@/store/auth";
import {useEffect} from "react";
import {PATH} from "@/constants/path";


const InterviewLayout = () => {
  const [isLoggedIn,] = useRecoilState(isLoggedInState);
  const navigate = useNavigate();

  useEffect(() => {
    if(!isLoggedIn) {
      navigate(PATH.MAIN_PAGE)
    }
  }, [isLoggedIn])

  return (
    <>
      <Outlet/>
    </>
  )
}

export default InterviewLayout;
