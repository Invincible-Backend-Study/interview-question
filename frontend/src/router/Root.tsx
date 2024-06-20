import {Outlet} from "react-router-dom";
import CompactNavbar from "@/components/CompactNavbar/CompactNavbar";
import {useMyProfileQuery} from "@/hooks/api/member/useMyProfileQuery";

function Layout(){
  const {profile} = useMyProfileQuery();
  return (
    <>
      <div className="flex flex-shrink-0 ">
        <CompactNavbar profile={profile}/>
        <Outlet/>
      </div>
    </>
  )
}

function InterviewLayout(){
  return (
    <>
      <Outlet/>
    </>
  )
}

function LoginLayout() {
  return  (
    <>
      <div className="flex items-center justify-center min-h-screen">
        <Outlet/>
      </div>
    </>
  )
}

export default {
  InterviewLayout,
  LoginLayout,
  Layout
};
