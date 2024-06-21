import {ScrollShadow} from "@nextui-org/react";
import ComputerChat from "@/components/Chat/ComputerChat";
import UserChat from "@/components/Chat/UserChat";
import {Chat} from "@/types/question";


interface InterviewQuestionBoardProps {
  question: string;
  remainTailQuestionCount: number;
  chatList: Chat[]
}

const InterviewQuestionBoard = ({question, remainTailQuestionCount, chatList}: InterviewQuestionBoardProps) => {

  return (
    <>
      <div className='row-span-4 col-auto'>
        <p className="text-3xl">{question}</p>
        <span>{remainTailQuestionCount}개의 꼬리질문이 남아 있습니다.</span>

        <ScrollShadow className="h-[55vh] p-5 flex flex-col gap-4">
          {chatList.map((chat, index)=> chat.type === "TailQuestion" ? <ComputerChat  key={index} {...chat}/> : <UserChat key={index}{...chat}/>)}
        </ScrollShadow>
      </div>
    </>
  )
}

export default InterviewQuestionBoard;
