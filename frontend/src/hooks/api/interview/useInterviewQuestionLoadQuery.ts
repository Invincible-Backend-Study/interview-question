import {useSuspenseQuery} from "@tanstack/react-query";
import {loadByCurrentInterviewQuestion} from "@/api/interview/LoadByCurrentInterviewQuestion";


export const useInterviewQuestionLoadQuery = (interviewId: number) => {
  // TODO: 변수명 바꾸기
  const { data: interview, refetch, error, isFetching } = useSuspenseQuery({
    queryKey: ['loadInterviewQuestion', interviewId],
    queryFn: () => loadByCurrentInterviewQuestion(interviewId),
    refetchOnWindowFocus:false,
    gcTime: Infinity,
    staleTime: Infinity
  });

  return {
    interview,
    refetch,
    error,
    isLoading: isFetching
  };
}



