import {useSuspenseQuery} from "@tanstack/react-query";
import {loadByCurrentInterviewQuestion} from "@/api/interview/LoadByCurrentInterviewQuestion";


export const useInterviewQuestionLoadQuery = (interviewId: number) => {
  // TODO: 변수명 바꾸기
  const {data: interview, refetch} = useSuspenseQuery({
    queryKey: ['loadInterviewQuestion'],
    queryFn: () => loadByCurrentInterviewQuestion(interviewId),
    gcTime: 60 * 60 * 60 * 100,
    staleTime: 60 * 60 * 60  * 100,
  });

  return {interview, refetch}
}
