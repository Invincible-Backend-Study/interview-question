import {useAdminQuestionsQuery} from "@/hooks/api/question/useAdminQuestionsQuery";
import {useCallback, useEffect, useState} from "react";
import {Question} from "@/types/admin/question";
import {useQuestionSaveMutation} from "@/hooks/api/question/useQuestionSaveMutation";


export const useQuestionsManage = (questionSetId: number | undefined) => {
  const questionSaveMutation = useQuestionSaveMutation();
  const [tempId, setTempId] = useState<number>(-1)
  const {questions,  isLoading, refetch} = useAdminQuestionsQuery(questionSetId)
  const [newRows, setNewRows] = useState<Question[]>([]);

  const handlePrependRow =  useCallback(() => {
    if(questionSetId === undefined){
      return ;
    }
    setNewRows(rows => [{questionId: tempId, sequence: 1, question: "", questionSetId: questionSetId}, ...rows])
    setTempId(t => t - 1);
  }, [newRows,tempId]);


  const handlePrependRows = useCallback((questions: string[]) => {
    if(questionSetId === undefined){
      return ;
    }

    setNewRows(rows => [
      ...questions.map((question, sequence) => ({questionSetId, sequence: sequence + 1, question, questionId: tempId - sequence}) as Question),
      ...rows
    ])

    setTempId(t => t + -questions.length);

  }, [questionSetId, newRows, tempId]);

  const handleRemove = useCallback((id: number) => {
    console.log(id);
  }, [newRows])

  const handleSave = useCallback(() => {
    newRows.forEach((question, index, arr) => questionSaveMutation.mutate(question, {onSuccess: () => {
        if(index === arr.length - 1) {
          setNewRows([])
          refetch();
        }
      }}))
  }, [newRows]);

  useEffect(() => {
    setNewRows([]);
  } , [questionSetId])

  useEffect(() => {
  }, [questions]);

  const rows = [...newRows, ...questions];

  return {
    questions: rows,
    handlePrependRow,
    handlePrependRows,
    handleRemove,
    handleSave,
    isLoading
  }
}
