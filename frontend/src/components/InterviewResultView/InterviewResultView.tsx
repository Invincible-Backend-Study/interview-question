import {useInterviewResultQuery} from "@/hooks/api/interview/useInterviewResultQuery";
import interviewResultPage from "@/pages/InterviewResultPage";


interface InterviewResultViewProps {
  interviewId: number;
}

const InterviewResultView = ({interviewId}: InterviewResultViewProps) => {
  const {interviewResult} = useInterviewResultQuery(interviewId);
  console.log(interviewResult);
  return <div>{JSON.stringify(interviewResult)}</div>
}

export default InterviewResultView;
