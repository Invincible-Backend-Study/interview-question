import {Button,  Kbd} from "@nextui-org/react";


interface InterviewControllerProps {
  onSubmit: () => void
  onPass: () => void
}


const InterviewController = ({onSubmit, onPass}: InterviewControllerProps) => {
  return <div className="flex flex-row-reverse gap-3 p-3">
    <Button onClick={onSubmit}>
      제출 <Kbd keys={["option", "command"]}>S</Kbd>
    </Button>
    <Button onClick={onPass}>
      패스 <Kbd keys={["option", "command"]}>A</Kbd>
    </Button>
    <Button isDisabled>일시정지</Button>
    <Button>
      그만두기
      <Kbd keys={["option", "command"]}>P</Kbd>
    </Button>
  </div>
}

export default InterviewController;
