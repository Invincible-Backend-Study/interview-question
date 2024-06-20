import {Button, Card, CardBody, CardFooter, CardHeader, Divider, Image} from "@nextui-org/react";
import {InterviewCreateFormCommand} from "@/types/interview";
import {useCallback} from "react";


interface QuestionSetItemProps {
  questionSetId: number;
  title: string;
  description: string;
  count: number;
  tailQuestionDepth: number;

  openInterviewSetting: (command: InterviewCreateFormCommand) => void;
}

const QuestionSetItem = ({questionSetId, title, description, tailQuestionDepth, count, openInterviewSetting}: QuestionSetItemProps) => {

  const handleOpenSettings = useCallback(() => {
    openInterviewSetting({
      questionSetId,
      count,
      tailQuestionDepth
    })
  },[])
  return (
    <Card className="max-w-[300px] ">
      <CardHeader className="flex flex-col items-start ">
        <span className="text-blue-500">NEW</span>
        <span>{title}</span>
        <span>{description}</span>
      </CardHeader>
      <CardBody>
        <Image
          isBlurred
          width={300}
          height={200}
          src="https://velog.velcdn.com/images/pak4184/post/98ba8b4f-7b89-4d28-8376-0dc8d1be805a/image.png"/>
      </CardBody>
      <Divider/>
      <CardFooter className="flex flex-row-reverse">
        <Button color="primary" variant="light" onClick={handleOpenSettings}>시작하기</Button>
      </CardFooter>
    </Card>
  )
}


export default QuestionSetItem;
