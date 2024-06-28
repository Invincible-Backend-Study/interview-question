import {keepPreviousData, useInfiniteQuery, useQuery, useSuspenseInfiniteQuery} from "@tanstack/react-query";
import {myInterview} from "@/api/interview/MyInterview";


const size = 10;


export const useMyInterviewQuery = (page: number) => {

  const {data, refetch} = useQuery({
    queryKey: ['my interview'],
    queryFn: () => myInterview({page: page - 1, size}),
    placeholderData: keepPreviousData,
  });



  return {
    totalPages: data?.totalPages,
    data: data?.content,
    refetch
  }
}
