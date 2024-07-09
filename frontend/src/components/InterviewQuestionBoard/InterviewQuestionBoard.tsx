import {ScrollShadow} from "@nextui-org/react";
import ComputerChat from "@/components/Chat/ComputerChat";
import UserChat from "@/components/Chat/UserChat";
import {Answer, Chat} from "@/types/question";
import {useCallback, useEffect,useRef} from "react";
import MainQuestionChat from "@/components/Chat/MainQuestionChat";

interface InterviewQuestionBoardProps {
  remainTailQuestionCount: number;
  chatList: Chat[],
}

const InterviewQuestionBoard = ({remainTailQuestionCount, chatList}: InterviewQuestionBoardProps) => {
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


  const displayChat = useCallback((chat: Chat, index: number)=> {
    if(chat.type === "TailQuestion") {
      return <ComputerChat key={index} {...chat}/>
    }

    if(chat.type === "MainQuestion") {
      return <MainQuestionChat  key={index} {...chat}/>
    }

    return <UserChat key={index} {...chat as Answer}/>;
  }, [chatList])

  return (
    <div className="max-w-full min-w-full ">
      <div className='row-span-4 col-auto'>
        <TailQuestionMessage/>

        <ScrollShadow className='h-[calc(100vh-450px)] p-5 flex flex-col gap-4' ref={scrollRef}>
          {chatList.map((chat, index) => displayChat(chat, index))}
        </ScrollShadow>
      </div>
    </div>
  )
}

export default InterviewQuestionBoard;
