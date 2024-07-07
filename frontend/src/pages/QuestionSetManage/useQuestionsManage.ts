import {useAdminQuestionsQuery} from "@/hooks/api/question/useAdminQuestionsQuery";
import {useCallback, useState} from "react";
import {Question} from "@/types/admin/question";


export const useQuestionsManage = (questionSetId: number | undefined) => {
  const [tempId, setTempId] = useState<number>(-1)
  const {questions,  isLoading} = useAdminQuestionsQuery(questionSetId)
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
    console.log(questions);

  }, [questionSetId, newRows, tempId]);

  const handleRemove = useCallback((id: number) => {
    console.log(id);
  }, [newRows])

  const handleSave = useCallback(() => {

  }, [newRows]);

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
