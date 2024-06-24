import {useMyProfileQuery} from "@/hooks/api/member/useMyProfileQuery";
import CompactNavbar from "@/components/CompactNavbar/CompactNavbar";
import {Outlet} from "react-router-dom";
import {ScrollShadow} from "@nextui-org/react";


const DefaultLayout = () => {
  const {profile} = useMyProfileQuery();
  return (
    <>
      <div className="flex flex-shrink-0 ">
        <CompactNavbar profile={profile}/>
        <ScrollShadow className="h-screen w-full">
          <Outlet/>
        </ScrollShadow>
      </div>
    </>
  )
}

export default DefaultLayout;
