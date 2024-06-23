import {useInterviewResultQuery} from "@/hooks/api/interview/useInterviewResultQuery";


interface InterviewResultViewProps {
  interviewId: number;
}

const InterviewResultView = ({interviewId}: InterviewResultViewProps) => {
  const {interviewResult} = useInterviewResultQuery(interviewId);
  return <div>{JSON.stringify(interviewResult)}</div>
}

export default InterviewResultView;
