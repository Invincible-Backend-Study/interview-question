import {useCallback, useMemo, useState} from "react";
import {QuestionSetRow} from "@/types/admin/questionSet";


const INITIAL_STATE = {
  title: "",
  description: "",
  thumbnailUrl: "",
  questionSetId: -1,
  defaultTailQuestionDepth: 0,
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
  const memoizedValues = useMemo(() => ({
    form,
    updateInputValue
  }), [form, updateInputValue]);


  return {
    form: memoizedValues.form, updateInputValue:memoizedValues.updateInputValue
  }

}

export default useQuestionSetEditForm;
