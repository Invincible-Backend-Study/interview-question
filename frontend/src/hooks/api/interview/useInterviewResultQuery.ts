import {useSuspenseQuery} from "@tanstack/react-query";
import {getInterviewResult} from "@/api/interview/GetInterviewResult";


export const useInterviewResultQuery = (interviewId: number) => {
  const {data: interviewResult} = useSuspenseQuery({
    queryKey: ['interview result', interviewId],
    queryFn: () => getInterviewResult(interviewId),
    staleTime: 60 * 60 * 100,
    gcTime: 60 * 60 * 100
  })

  return {
    interviewResult
  };
}
