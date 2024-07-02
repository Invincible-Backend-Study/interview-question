import {PropsWithChildren} from "react";


const Agent = ({children}: PropsWithChildren) => {
  const isMobile = window.matchMedia("only screen and (max-width: 760px)").matches;

  if(isMobile){
    return <div>모바일 기기는 지원하지 않습니다.</div>
  }
  return <>{children}</>

}

export default Agent;
