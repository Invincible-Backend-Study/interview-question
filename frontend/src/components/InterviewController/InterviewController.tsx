import {Button, Kbd} from "@nextui-org/react";


interface InterviewControllerProps {
  onSubmit: () => void
  onPass: () => void
  onQuit: () => void
  disabled: boolean
  info: () => void
}


const InterviewController = ({onSubmit, onQuit, onPass, disabled, info}: InterviewControllerProps) => {
  return <div className="flex flex-row-reverse gap-3 p-3">
    <Button onClick={onSubmit} isLoading={disabled}>
      제출 <Kbd keys={["command"]}>s</Kbd>
    </Button>
    <Button onClick={onPass} isLoading={disabled}>
      패스 <Kbd keys={["command"]}>p</Kbd>
    </Button>
    <Button isDisabled>일시정지</Button>
    <Button onClick={onQuit}>
      그만두기
      <Kbd keys={["command"]}>q</Kbd>
    </Button>

    <Button onClick={info} color={"warning"}>
      주의사항(꼭 읽어주세요)
    </Button>
  </div>
}

export default InterviewController;
