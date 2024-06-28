import {
  Button,
  Modal,
  ModalBody,
  ModalContent,
  ModalHeader,
  Spacer,
  Tooltip,
  useDisclosure
} from "@nextui-org/react";
import {MdHistory} from "react-icons/md";
import {FaHome} from "react-icons/fa";
import {IoSettingsOutline} from "react-icons/io5";
import ProfileAvatar from "@/components/ProfileAvatar/ProfileAvatar";
import { SlLogout } from "react-icons/sl";
import {useLogoutMutation} from "@/hooks/api/auth/useLogoutMutation";
import {useCallback} from "react";
import {PATH} from "@/constants/path";
import {useNavigate, useParams} from "react-router-dom";



interface CompactNavbarProps {
  profile: {
    nickname: string;
    avatarUrl: string;
  }
}

const CompactNavbar = ({profile}: CompactNavbarProps) => {
  const logoutMutation = useLogoutMutation();
  const navigate = useNavigate();
  const {isOpen, onClose, onOpen} = useDisclosure();
  const handleLogout = useCallback(() => logoutMutation.mutate(), [])
  return (
    <nav
      className="z-1 flex flex-col items-center w-16 py-4  min-h-screen " style={{
      borderRight: "1px solid rgb(54, 54, 54)"
    }}>
      <div className="flex flex-col items-center flex-1 p-2 space-y-4">
        <Tooltip placement="right" content={"메인 페이지"} color={"secondary"}>
          <Button color="success" isIconOnly onClick={() => navigate(PATH.MAIN_PAGE)}>
            <FaHome size={25}/>
          </Button>
        </Tooltip>

        <Tooltip placement="right" content={"면접 이력보기"} color={"secondary"}>
          <Button isIconOnly onClick={() => navigate(PATH.MY_INTERVIEW)}>
            <MdHistory size={25}/>
          </Button>
        </Tooltip>
      </div>

      <div className={"p-2 flex flex-col items-center"}>
        <ProfileAvatar profile={profile}/>
        <Spacer y={5}/>

        <Tooltip placement="right" content={"설정"} color={"secondary"}>
          <Button color="danger" isIconOnly onClick={() => onOpen()}>
            <IoSettingsOutline size={25}/>
          </Button>

        </Tooltip>

        <Spacer y={5}/>
        <Tooltip placement="right" content={"로그아웃"} color={"secondary"}>
          <Button color="danger" isIconOnly onClick={handleLogout}>
            <SlLogout size={20}/>
          </Button>
        </Tooltip>
      </div>

      <Modal backdrop="blur" isDismissable={false} isKeyboardDismissDisabled={true} isOpen={isOpen} placement={"top"} className={`text-foreground bg-background dark`} onClose={onClose}>
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader>
                설정
              </ModalHeader>
              <ModalBody>
              </ModalBody>
            </>
          )}
        </ModalContent>
      </Modal>
    </nav>
  )
}

export default CompactNavbar;
