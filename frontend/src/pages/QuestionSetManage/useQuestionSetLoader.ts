import {useCallback, useEffect, useState} from "react";
import {useAdminQuestionSetQuery} from "@/hooks/api/question/useAdminQuestionSetQuery";
import {QuestionSetRow} from "@/types/admin/questionSet";


export const useQuestionSetLoader = () => {
  const [selectedQuestionSetId, setSelectedQuestionSetId] = useState<number | undefined>();
  const [selectedQuestionSet, setSelectedQuestionSet] = useState<QuestionSetRow | undefined>();
  const [page, setPage] = useState<number>(0);
  const {data: questionSetList, refetch, isLoading, totalPages} = useAdminQuestionSetQuery(page);

  useEffect(() => {
    refetch()
  }, [page])

  const changePage = useCallback((n: number) => setPage(n) , [page])
  const changeSelectedQuestionSet = useCallback((n: number) => {

    // close
    if(n <= 0){
      setSelectedQuestionSet(undefined);
      setSelectedQuestionSetId(undefined);
      return ;
    }

    setSelectedQuestionSetId(n);

    if(questionSetList === undefined) {
      return ;
    }
    const selectedIndex = questionSetList?.findIndex(questionSet=> questionSet.questionSetId === n);
    if(selectedIndex === -1) {
      return ;
    }
    setSelectedQuestionSet(questionSetList[selectedIndex]);
  }, [selectedQuestionSetId]);

  return {
    questionSetList,
    pageInfo: {
      page,
      isLoading,
      totalPages,
      changePage,
    },

    selected: {
      selectedQuestionSet,
      selectedQuestionSetId,
      changeSelectedQuestionSet
    }
  }
}
