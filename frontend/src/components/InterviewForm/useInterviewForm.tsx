import {useInterviewQuestionLoadQuery} from "@/hooks/api/interview/useInterviewQuestionLoadQuery";


export const useInterviewForm = (interviewId: number) => {
  const {interview, refetch} = useInterviewQuestionLoadQuery(interviewId);
  

}
