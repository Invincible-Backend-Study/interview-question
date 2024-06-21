import {useSuspenseQuery} from "@tanstack/react-query";
import {loadByCurrentInterviewQuestion} from "@/api/interview/LoadByCurrentInterviewQuestion";


export const useInterviewQuestionLoadQuery = (interviewId: number) => {
  // TODO: 변수명 바꾸기
  const {data: interview, refetch} = useSuspenseQuery({
    queryKey: ['loadInterviewQuestion'],
    queryFn: () => loadByCurrentInterviewQuestion(interviewId),
    gcTime: 1000 * 60 * 10,
    staleTime:1000 * 60 * 10
  });

  return {interview, refetch}
}
