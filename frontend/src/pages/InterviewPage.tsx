import {useParams} from "react-router-dom";
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
