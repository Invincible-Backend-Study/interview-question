import {useCallback, useState} from "react";
import {QuestionSet, QuestionSetRow} from "@/types/admin/questionSet";


const useQuestionSetManage = (questions: QuestionSet[]) => {
  // 임시 id
  const [id, setId] = useState(-1);
  const [newRows, setNewRows] = useState<QuestionSetRow[]>([])
  const [deleteRows, setDeleteRows] = useState<number[]>([]);


  const handlePrependQuestionSet = useCallback(() => {
    setNewRows(r => [{title: "", description: "", questionSetId: id, defaultTailQuestionCount: 10}, ...r])
    setId(id => id - 1);
  }, [questions, newRows])


  const rows: QuestionSetRow[] = [...newRows, ...questions];

  return {
    rows,
    handlePrependQuestionSet
  }
}

export default useQuestionSetManage;
