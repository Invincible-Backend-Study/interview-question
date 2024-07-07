import {useCallback, useEffect, useState} from "react";
import {QuestionSet, QuestionSetRow} from "@/types/admin/questionSet";
import useQuestionSetEditForm from "@/components/QuestionSetTable/useQuestionSetEditForm";
import {useQuestionSetMutation} from "@/hooks/api/question/useQuestionSetMutation";


const useQuestionSetManage = (questions: QuestionSet[]) => {
  // 임시 id
  const {form,updateInputValue} = useQuestionSetEditForm();
  const questionSetSaveMutation = useQuestionSetMutation();
  const [newRows, setNewRows] = useState<QuestionSetRow[]>([])


  const handleRegisterNewQuestionSet = useCallback(() => {
    console.log(form);
    questionSetSaveMutation.mutate({...form}, {
      onSuccess: ({questionSetId}) => {
        setNewRows(rows => [{...form, questionSetId}, ...rows]);
      }
    })
  }, [questionSetSaveMutation,form])

  useEffect(() => {
    setNewRows([]);
  }, [questions])


  const rows: QuestionSetRow[] = [...newRows, ...questions];

  return {
    form,
    updateInputValue,
    handleRegisterNewQuestionSet,
    rows,
  }
}

export default useQuestionSetManage;
