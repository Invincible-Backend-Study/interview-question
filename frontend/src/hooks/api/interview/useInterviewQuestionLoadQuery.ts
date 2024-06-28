import {useSuspenseQuery} from "@tanstack/react-query";
import {loadByCurrentInterviewQuestion} from "@/api/interview/LoadByCurrentInterviewQuestion";


export const useInterviewQuestionLoadQuery = (interviewId: number) => {
  // TODO: 변수명 바꾸기
  const {data: interview, refetch, error} = useSuspenseQuery({
    queryKey: ['loadInterviewQuestion', interviewId],
    queryFn: () => loadByCurrentInterviewQuestion(interviewId),
    gcTime: 60 * 60 * 10,
    staleTime: 60* 60 * 10
  });

  return {interview, refetch, error}
}



