import {Button, Divider, ModalBody, ModalContent, ModalHeader, Spacer} from "@nextui-org/react";
import {useWithdrawMutation} from "@/hooks/api/member/useWithdrawMutation";
import {useCallback, useEffect, useState} from "react";
import {toast} from "sonner";
import {useNavigate} from "react-router-dom";
import {useRecoilState} from "recoil";
import {isLoggedInState} from "@/store/auth";
import {TOKEN} from "@/constants/api";
import {PATH} from "@/constants/path";

interface IndividualSettingFormProps {
  isOpen: boolean;
}
const IndividualSettingForm = ({isOpen}: IndividualSettingFormProps) => {
  const [activeDeleteButton, setActiveDeleteButton] = useState(false);
  const navigate = useNavigate();
  const [_, setLoggedIn] = useRecoilState(isLoggedInState);
  const withdrawMutation = useWithdrawMutation();

  const handleWithdraw = useCallback(()=> {
    withdrawMutation.mutate(undefined, {
      onSuccess: () => {
        setLoggedIn(false);
        localStorage.removeItem(TOKEN.ACCESS);
        toast.info("이용해주셔서 감사합니다~");
        navigate(PATH.AUTH);
      },
      onError: () => {
        toast.error("알 수 없는 이유로 실패했습니다.")
      }
    })
  }, [])

  useEffect(() => {
    setActiveDeleteButton(false);
  }, [isOpen])



  return (
    <ModalContent>
      {() => (
        <>
          <ModalHeader>
            설정
          </ModalHeader>
          <ModalBody>
            <p className='text-xs'>언어 변경 기능/지원 예정</p>

            <Button color='primary' onClick={() => location.assign("https://github.com/invincible-Backend-Study/")}>무백스 Repository 보러가기</Button>
            <Button color='success' onClick={() => location.assign("https://discord.gg/3MEzKMPm")}>무백스 디스코드 참여하기</Button>
            <Button color='default' onClick={() => location.assign("https://github.com/JaehongDev")}>만든 사람</Button>

            <Spacer y={5}/>
            <Divider/>
            <Spacer y={5}/>
            <Button color='warning' onClick={() => setActiveDeleteButton(state => !state)}> { activeDeleteButton ? '취소하기' : '탈퇴하기' } </Button>
            {activeDeleteButton && <Button color={'danger'} onClick={handleWithdraw}>버튼을 누르면 정말로 탈퇴합니다</Button>}
          </ModalBody>
        </>
      )}
    </ModalContent>
  )
}

export default IndividualSettingForm;
