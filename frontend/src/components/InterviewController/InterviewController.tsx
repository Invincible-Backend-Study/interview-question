import {Button, Divider, Kbd, Spacer} from "@nextui-org/react";
import {useMediaQuery} from "@/hooks/useMediaQuery";


interface InterviewControllerProps {
  onSubmit: () => void
  onPass: () => void
  onQuit: () => void
  disabled: boolean
  info?: () => void
}


const InterviewController = ({onSubmit, onQuit, onPass, disabled, info}: InterviewControllerProps) => {
  const {isMobile} = useMediaQuery();
  return <div className="flex flex-row-reverse gap-3 p-3">
    <Button color="success" onClick={onSubmit} isLoading={disabled}>
      제출
      {isMobile ? <></> :<Kbd keys={["command"]}>s</Kbd>}
    </Button>

    <Spacer x={1}/>
    <Divider orientation="vertical"/>
    <Spacer x={1}/>

    <Button color="danger" onClick={onPass} isLoading={disabled}>
      패스
      {isMobile ? <></> : <Kbd keys={["command"]}>p</Kbd>}
    </Button>
    <Button onClick={onQuit}>
      그만두기
      {isMobile ? <></> : <Kbd keys={["command"]}>q</Kbd>}
    </Button>

    {!info ?? <Button onClick={info} color={"warning"}>
      주의사항(꼭 읽어주세요)
    </Button>}
  </div>
}

export default InterviewController;
