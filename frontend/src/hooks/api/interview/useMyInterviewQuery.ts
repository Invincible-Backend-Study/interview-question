import {useInfiniteQuery, useSuspenseInfiniteQuery} from "@tanstack/react-query";
import {myInterview} from "@/api/interview/MyInterview";


const size = 10;


export const useMyInterviewQuery = () => {
  const {data, isLoading, fetchNextPage} = useInfiniteQuery({
    queryKey: ['my interview'],
    queryFn: ({pageParam}) => myInterview({page: Number(pageParam), size}),
    getNextPageParam: response => response.last ? undefined : response.number + 1,
    initialPageParam: 0,
    gcTime: 60 * 60 * 60 * 1000,
    staleTime: 60 * 60 * 60 * 1000,
  });

  export default{
    data,
    isLoading,
    fetchNextPage
  }
}
