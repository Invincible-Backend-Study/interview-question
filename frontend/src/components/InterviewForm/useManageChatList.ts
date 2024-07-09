import {useCallback, useEffect, useState} from "react";
import {Chat} from "@/types/question";


interface IUseManageChatList{
  mainQuestion: string;
}

export const useManageChatList = ({mainQuestion}: IUseManageChatList) => {
  const [chatList, setChatList] = useState<Chat[]>([])

  useEffect(() => {
    setChatList([{type: "MainQuestion", content: mainQuestion}])
  }, [mainQuestion]);


  const handleAppendUserChat = useCallback(({answer}: {answer:string}) => {
    setChatList(chatList => [...chatList, {type: "Answer", content: answer}]);
  }, [chatList])


  const handleAppendComputerChat = useCallback( ({tailQuestion}: {tailQuestion:string}) => {
    setChatList(chatList => [...chatList, {type: "TailQuestion", content: tailQuestion}])
  }, [chatList])


  const resetChatList = useCallback(() => {
    setChatList( []);
  }, [chatList])

  return {
    chatList,
    resetChatList,
    handleAppendUserChat,
    handleAppendComputerChat,
  }
}
