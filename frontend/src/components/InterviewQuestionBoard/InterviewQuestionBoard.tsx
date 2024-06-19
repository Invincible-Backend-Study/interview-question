import {ScrollShadow} from "@nextui-org/react";
import ComputerChat from "@/components/Chat/ComputerChat";
import UserChat from "@/components/Chat/UserChat";


interface InterviewQuestionBoardProps {
  question: string;
  remainTailQuestionCount: number;
}

const InterviewQuestionBoard = ({question, remainTailQuestionCount}: InterviewQuestionBoardProps) => {

  return (
    <>
      <div className='row-span-4 col-auto'>
        <p className="text-3xl">{question}</p>
        <span>{remainTailQuestionCount}개의 꼬리질문이 남아 있습니다.</span>

        <ScrollShadow className="h-[55vh] p-5 flex flex-col gap-4">
          <ComputerChat type={"TailQuestion"} score={100} feedback={""}
                        tailQuestion={"HashMap에 대해서 설명해주세요"}></ComputerChat>
          <UserChat type={"Answer"} content={"hashCode가 해시맵에서 사용됩니다"}/>
          <ComputerChat type={"TailQuestion"} score={100} feedback={""}
                        tailQuestion={"HashMap에 대해서 설명해주세요"}></ComputerChat>
          <UserChat type={"Answer"} content={"hashCode가 해시맵에서 사용됩니다"}/>
          <ComputerChat type={"TailQuestion"} score={100} feedback={""}
                        tailQuestion={"HashMap에 대해서 설명해주세요"}></ComputerChat>
          <UserChat type={"Answer"} content={"hashCode가 해시맵에서 사용됩니다"}/>
        </ScrollShadow>
      </div>
    </>
  )
}

export default InterviewQuestionBoard;
