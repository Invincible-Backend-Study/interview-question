import {Button, Spacer} from "@nextui-org/react";
import {MdHistory} from "react-icons/md";
import {FaHome} from "react-icons/fa";
import {IoSettingsOutline} from "react-icons/io5";
import ProfileAvatar from "@/components/ProfileAvatar/ProfileAvatar";



interface CompactNavbarProps {
  profile: {
    nickname: string;
    avatarUrl: string;
  }
}

const CompactNavbar = ({profile}: CompactNavbarProps) => {


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
      </div>
    </nav>
  )
}

export default CompactNavbar;
