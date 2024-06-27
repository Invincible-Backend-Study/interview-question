import {Outlet, useNavigate} from "react-router-dom";
import {useRecoilState} from "recoil";
import {isLoggedInState} from "@/store/auth";
import {useEffect} from "react";
import {PATH} from "@/constants/path";


const LoginLayout = () => {
  const [isLoggedIn,] = useRecoilState(isLoggedInState);
  const navigate = useNavigate();

  useEffect(() => {

    if(isLoggedIn) {
      navigate(PATH.MAIN_PAGE)
    }

  }, [isLoggedIn])
  return  (
    <>
      <div className="flex items-center justify-center min-h-screen">
        <Outlet/>
      </div>
    </>
  )
}

export default LoginLayout;
