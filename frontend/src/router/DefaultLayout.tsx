import {useMyProfileQuery} from "@/hooks/api/member/useMyProfileQuery";
import CompactNavbar from "@/components/CompactNavbar/CompactNavbar";
import {Outlet} from "react-router-dom";
import {Divider, ScrollShadow} from "@nextui-org/react";
import ErrorFallback from "@/components/ErrorFallback/ErrorFallback";
import { ErrorBoundary } from "react-error-boundary";
import {useMediaQuery} from "@/hooks/useMediaQuery";
import {MyProfileResponse} from "@/types/member";
import CompactNavigator from "@/components/CompactNavigator/CompactNavigator";


const DefaultMobileLayout = ({profile}: {profile:MyProfileResponse}) => {
  return (
    <div className="flex flex-col">
      <ScrollShadow className='h-[calc(100vh-80px)] w-full'>
        <Outlet/>
      </ScrollShadow>
      <Divider/>
      <CompactNavigator profile={profile}/>
    </div>
  )
}


const DefaultLayout = () => {
  const {profile} = useMyProfileQuery();
  const {isMobile} = useMediaQuery();
  return (
    <ErrorBoundary fallbackRender={ErrorFallback}>
      {isMobile ? <DefaultMobileLayout profile={profile}/> : (
        <div className='flex flex-shrink-0 '>
          <CompactNavbar profile={profile}/>
          <ScrollShadow className='h-screen w-full'>
            <Outlet/>
          </ScrollShadow>
        </div>
      )}
    </ErrorBoundary>
  )
}

export default DefaultLayout;
