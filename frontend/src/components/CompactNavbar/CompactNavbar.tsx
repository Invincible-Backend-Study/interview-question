import {Button, Spacer, Tooltip} from "@nextui-org/react";
import {MdHistory} from "react-icons/md";
import {FaHome} from "react-icons/fa";
import {IoSettingsOutline} from "react-icons/io5";
import ProfileAvatar from "@/components/ProfileAvatar/ProfileAvatar";
import { SlLogout } from "react-icons/sl";
import {useLogoutMutation} from "@/hooks/api/auth/useLogoutMutation";
import {useCallback} from "react";



interface CompactNavbarProps {
  profile: {
    nickname: string;
    avatarUrl: string;
  }
}

const CompactNavbar = ({profile}: CompactNavbarProps) => {
  const logoutMutation = useLogoutMutation();

  const handleLogout = useCallback(() => logoutMutation.mutate(), [])
  return (
    <nav
      className="z-1 flex flex-col items-center w-16 py-4  min-h-screen " style={{
        borderRight: "1px solid rgb(54, 54, 54)"
    }}>
      <div className="flex flex-col items-center flex-1 p-2 space-y-4">
        <Button color="success" isIconOnly>
          <FaHome size={25}/>
        </Button>

        <Button isIconOnly>
          <MdHistory size={25}/>
        </Button>
      </div>

      <div className={"p-2 flex flex-col items-center"}>
        <ProfileAvatar profile={profile}/>
        <Spacer y={5}/>
        <Button color="danger" isIconOnly>
          <IoSettingsOutline size={25}/>
        </Button>

        <Spacer y={5}/>
        <Tooltip placement="right" content={"로그아웃"} color={"secondary"}>
        <Button color="danger" isIconOnly onClick={handleLogout}>
          <SlLogout size={20}/>
        </Button>
        </Tooltip>
      </div>
    </nav>
  )
}

export default CompactNavbar;
