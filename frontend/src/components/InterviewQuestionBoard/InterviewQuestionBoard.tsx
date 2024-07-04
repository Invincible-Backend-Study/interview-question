import {ScrollShadow} from "@nextui-org/react";
import ComputerChat from "@/components/Chat/ComputerChat";
import UserChat from "@/components/Chat/UserChat";
import {Chat} from "@/types/question";
import {useCallback, useEffect, useRef} from "react";

interface InterviewQuestionBoardProps {
  question: string;
  remainTailQuestionCount: number;
  chatList: Chat[],
  isLoading: boolean;
}

const InterviewQuestionBoard = ({question, remainTailQuestionCount, chatList, isLoading}: InterviewQuestionBoardProps) => {
  const scrollRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    scrollToBottom();
  }, [chatList]);

  const scrollToBottom = () => {
    if (scrollRef.current) {
      scrollRef.current.scrollTo({
        top: scrollRef.current.scrollHeight,
        behavior: 'smooth'
      });
    }
  };

  const TailQuestionMessage = useCallback(() => {
    if(remainTailQuestionCount === 0) {
      return <span>다음 문제로 넘어갑니다.</span>
    }
    return <span>{`남은 꼬리질문: ${remainTailQuestionCount}개`}</span>
  }, [remainTailQuestionCount, chatList])

  return (
    <div className="max-w-full min-w-full ">
      <div className='row-span-4 col-auto'>
        <p className='text-3xl'>{isLoading ? "질문을 가져옵니다....": question}</p>
        <TailQuestionMessage/>


        <ScrollShadow className='h-[calc(100vh-450px)] p-5 flex flex-col gap-4' ref={scrollRef}>
          {chatList.map((chat, index) => chat.type === "TailQuestion" ? <ComputerChat key={index} {...chat}/> :
            <UserChat key={index}{...chat}/>)}
        </ScrollShadow>
      </div>
    </div>
  )
}

export default InterviewQuestionBoard;
