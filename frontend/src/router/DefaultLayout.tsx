import {useMyProfileQuery} from "@/hooks/api/member/useMyProfileQuery";
import CompactNavbar from "@/components/CompactNavbar/CompactNavbar";
import {Outlet} from "react-router-dom";


const DefaultLayout = () => {
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

export default DefaultLayout;
