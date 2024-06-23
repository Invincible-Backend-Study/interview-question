import {useParams} from "react-router-dom";
import InterviewResultView from "@/components/InterviewResultView/InterviewResultView";


const InterviewResultPage = () => {
  const {interviewId} = useParams();

  if(interviewId === undefined || interviewId === null || isNaN(Number(interviewId))) {
    location.assign("/");
    return;
  }

  return (
    <InterviewResultView interviewId={Number(interviewId)}/>
  )
}

export default InterviewResultPage;
