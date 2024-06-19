import {PropsWithChildren, useLayoutEffect} from "react";
import {useRecoilState} from "recoil";
import {TOKEN} from "@/constants/api";
import {isLoggedInState} from "@/store/auth";


interface LogInProps extends PropsWithChildren{}

const LogIn = ({children }:LogInProps) => {
  const [_, setIsLoggedIn] = useRecoilState(isLoggedInState);

  useLayoutEffect(() => {
    if(localStorage.getItem(TOKEN.ACCESS)){
      setIsLoggedIn(true);
    }
  }, [setIsLoggedIn]);

  return <>{children}</>
}

export default LogIn;
