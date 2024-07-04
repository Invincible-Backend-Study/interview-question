import {Button, Card, CardBody, CardFooter, CardHeader, Divider, Image} from "@nextui-org/react";
import {InterviewCreateFormCommand} from "@/types/interview";
import {useCallback} from "react";
import {QuestionSet} from "@/types/questionSet";


interface QuestionSetItemProps {
  questionSet: QuestionSet
  openInterviewSetting: (command: InterviewCreateFormCommand) => void;
}

const QuestionSetItem = ({questionSet:{questionSetId, title, description, tailQuestionDepth, count, thumbnailUrl}, openInterviewSetting}: QuestionSetItemProps) => {

  const handleOpenSettings = useCallback(() => {
    openInterviewSetting({
      questionSetId,
      count,
      tailQuestionDepth
    })
  },[])
  return (
    <Card className="max-w-[270px] ">
      <CardHeader className="flex flex-col items-start ">
        <span className="text-blue-500">NEW</span>
        <span>{title}</span>
        <span className="text-xs">{description}</span>
      </CardHeader>
      <CardBody>
        <Image
          width={270}
          height={200}
          src={ thumbnailUrl === null ? "https://velog.velcdn.com/images/pak4184/post/98ba8b4f-7b89-4d28-8376-0dc8d1be805a/image.png" : thumbnailUrl}
        />
      </CardBody>
      <Divider/>
      <CardFooter className="flex flex-row-reverse">
        <Button color="primary" variant="light" onClick={handleOpenSettings}>시작하기</Button>
      </CardFooter>
    </Card>
  )
}


export default QuestionSetItem;
