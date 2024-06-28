import {useInfiniteQuery} from "@tanstack/react-query";
import {getQuestionSet} from "@/api/question/GetQuestionSet";


export const useQuestionSetQuery = () => {
  const {data ,isLoading, fetchNextPage} = useInfiniteQuery({
    queryKey: ['question set'],
    queryFn: ({pageParam}) => getQuestionSet({size: 30, page: Number(pageParam)}),
    staleTime: 60 * 60 * 10,
    gcTime: 60 * 60 * 10,
    getNextPageParam: response => response.last ? undefined : response.number + 1,
    initialPageParam: 0,
  })

  return {data,isLoading, fetchNextPage};
}
