import {Button, ModalBody, ModalContent, ModalHeader} from "@nextui-org/react";

const IndividualSettingForm = () => {
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

            <p className='text-xs'>탈퇴 기능은 미구현 상태입니다</p>
            <Button color='danger'>탈퇴하기</Button>
          </ModalBody>
        </>
      )}
    </ModalContent>
  )
}

export default IndividualSettingForm;
