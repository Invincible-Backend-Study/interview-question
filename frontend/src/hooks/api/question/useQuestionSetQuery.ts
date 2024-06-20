import {useSuspenseQuery} from "@tanstack/react-query";
import {getQuestionSet} from "@/api/question/GetQuestionSet";


export const useQuestionSetQuery = () => {
  const {data: {content: questionSetList}, isLoading} = useSuspenseQuery({
    queryKey: ['question set'],
    queryFn: () => getQuestionSet({size: 30, page: 0}),
    staleTime: 60 * 60 * 60 * 100,
    gcTime: 60 * 60 * 60 * 100,
  })

  return {questionSetList,isLoading};
}
