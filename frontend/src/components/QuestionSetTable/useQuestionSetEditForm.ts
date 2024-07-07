import {useCallback, useState} from "react";
import {QuestionSetRow} from "@/types/admin/questionSet";


const INITIAL_STATE = {
  title: "",
  description: "",
  thumbnailUrl: "",
  questionSetId: -1
}

export type ChangeQuestionForm = <K extends keyof QuestionSetRow>(key: K, value: QuestionSetRow[K]) => void;

const useQuestionSetEditForm = () => {
  const [form, setForm] = useState<QuestionSetRow>(INITIAL_STATE);

  const updateInputValue = useCallback( <K extends keyof QuestionSetRow>(key: K, value: QuestionSetRow[K]) => {
    setForm((prev) => ({
      ...prev,
      [key]: value
    }))
  }, [form]);


  return {
    form, updateInputValue
  }

}

export default useQuestionSetEditForm;
