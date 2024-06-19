import {useParams} from "react-router-dom";
import InterviewController from "@/components/InterviewController/InterviewController";
import {Accordion, AccordionItem,  ScrollShadow, Textarea} from "@nextui-org/react";
import ComputerChat from "@/components/Chat/ComputerChat";
import UserChat from "@/components/Chat/UserChat";
import InterviewForm from "@/components/InterviewForm/InterviewForm";

const InterviewPage = () => {
  const {interviewId} = useParams();

  if(interviewId === undefined) {
    location.assign("/");
    return;
  }



  return (
     <InterviewForm interviewId={Number(interviewId)}/>
  )
}

export default InterviewPage;
