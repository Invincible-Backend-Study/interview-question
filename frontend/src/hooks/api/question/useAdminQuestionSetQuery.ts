import {keepPreviousData, useQuery} from "@tanstack/react-query";
import {getQuestionSetByAdmin} from "@/api/question/GetQuestionSetByAdmin";

const size = 10;


export const useAdminQuestionSetQuery = (page: number) => {
  const {data, refetch, isLoading, isRefetching} = useQuery({
    queryKey: ['admin question set', page],
    queryFn: () => getQuestionSetByAdmin({page: page - 1, size}),
    placeholderData: keepPreviousData,
  });



  return {
    totalPages: data?.totalPages,
    data: data?.content,
    refetch,
    isLoading: isLoading || isRefetching
  }
}
