import {keepPreviousData, useQuery} from "@tanstack/react-query";
import {getQuestionsByAdmin} from "@/api/question/GetQuestionsByAdmin";
import {useEffect} from "react";

export const useAdminQuestionsQuery = (questionSetId: number | undefined) => {
  const {data: questions, refetch, isLoading, isRefetching} = useQuery({
    queryKey: ['admin questions', questionSetId],
    queryFn: () =>  getQuestionsByAdmin(questionSetId),
    placeholderData: keepPreviousData,
  });


  useEffect(() => {
    refetch()
  }, [questionSetId]);


  return {
    questions: questions === undefined ? [] : questions,
    refetch,
    isLoading: isLoading || isRefetching
  }
}
