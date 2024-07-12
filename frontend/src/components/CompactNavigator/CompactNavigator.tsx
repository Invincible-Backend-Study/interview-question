import {Button, Modal, useDisclosure} from "@nextui-org/react";
import {PATH} from "@/constants/path";
import {FaHome} from "react-icons/fa";
import {MdHistory} from "react-icons/md";
import {useLogoutMutation} from "@/hooks/api/auth/useLogoutMutation";
import {useNavigate} from "react-router-dom";
import {PropsWithChildren, useCallback} from "react";
import ProfileAvatar from "@/components/ProfileAvatar/ProfileAvatar";
import {IoSettingsOutline} from "react-icons/io5";
import {SlLogout} from "react-icons/sl";
import IndividualSettingForm from "@/components/IndividualSettingForm/IndividualSettingForm";

interface CompactNavigatorProps{
  profile: {
    nickname: string;
    avatarUrl: string;
  }
}

const CompactNavigator = ({profile}: CompactNavigatorProps) => {
  const logoutMutation = useLogoutMutation();
  const navigate = useNavigate();
  const {isOpen, onClose, onOpen} = useDisclosure();
  const handleLogout = useCallback(() => logoutMutation.mutate(), [])

  return (
    <div className="h-[80px] w-full flex flex-row justify-around items-center">
      <CompactButton text={"메인"}>
        <Button color="success" isIconOnly onClick={() => navigate(PATH.MAIN_PAGE)}>
          <FaHome size={25}/>
        </Button>
      </CompactButton>

      <CompactButton text={"면접 이력"}>
        <Button isIconOnly onClick={() => navigate(PATH.MY_INTERVIEW)}>
          <MdHistory size={25}/>
        </Button>
      </CompactButton>

      <ProfileAvatar profile={profile}/>


      <CompactButton text={"설정"}>
        <Button color="danger" isIconOnly onClick={() => onOpen()}>
          <IoSettingsOutline size={25}/>
        </Button>
      </CompactButton>


      <CompactButton text={"로그아웃"}>
        <Button color='danger' isIconOnly onClick={handleLogout}>
          <SlLogout size={20}/>
        </Button>
      </CompactButton>
      <Modal backdrop='blur' isDismissable={false} isKeyboardDismissDisabled={true} isOpen={isOpen} placement="bottom-center" onClose={onClose}>
        <IndividualSettingForm isOpen />
      </Modal>
    </div>
  )
}

interface CompactButtonProps extends PropsWithChildren{
  text: string;
}
const CompactButton = ({text, children}: CompactButtonProps) => {
  return (
    <div className="flex flex-col gap-1 items-center">
      {children}
      <span className="text-xs">{text}</span>
  </div>
  )


}

export default CompactNavigator;
