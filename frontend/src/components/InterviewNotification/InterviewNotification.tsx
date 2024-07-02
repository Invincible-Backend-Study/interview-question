import {Modal, ModalBody, ModalContent, ModalHeader} from "@nextui-org/react";

const messages = [
  "새로 고침 혹은 패스 버튼을 누르게 되면 다음 꼬리 질문이 아닌 다음 질문으로 넘어갑니다.",
  "네트워크 지연 혹은 서버의 문제로 꼬리 질문이 생성되지 않고 다음 문제로 넘어갈 수 있습니다.",
  "평가를 위해서 만든 것이 아닌 면접 혹은 기술에 대한 지식을 점검하는 목적으로 만들어졌습니다.",
  "답변은 1,000자로 제한합니다.",
  "중간에 나갈 수 있지만, 결과를 볼 수 없습니다.",
  "결과를 보기 위해서는 모든 문제를 풀거나 패스 시켜야 합니다.",
];


interface InterviewNotificationProps{
  isOpen?: boolean;
  onOpen?: () => void;
  onClose?: () => void;
  onOpenChange?: () => void;
  isControlled?: boolean;
}
const InterviewNotification = (props: InterviewNotificationProps) => {
  return (
    <Modal backdrop="blur"
           {...props}
           isDismissable={false} isKeyboardDismissDisabled={true} placement={"top"} className={`text-foreground bg-background dark`} >
      <ModalContent className={"w-[600px]"}>
        {() => (
          <>
            <ModalHeader>
              주의사항
            </ModalHeader>
            <ModalBody>
              {messages.map((message, key) => <p key={key} className={"text-sm text-warning"}>{key+1}. {message}</p>)}
            </ModalBody>
          </>
        )}
      </ModalContent>
    </Modal>
  )
}

export default InterviewNotification;
