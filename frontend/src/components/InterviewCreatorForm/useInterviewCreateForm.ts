import {useCallback, useState} from "react";


interface InterviewCreate{
  totalProblemCount: number;
  tailQuestionDepth: number;
}

export const useInterviewCreateForm = (defaultValue: InterviewCreate) => {
  const [interviewCreateForm, setInterviewCreateForm] = useState<InterviewCreate>(defaultValue)


  const handleOnChange = useCallback(<K extends keyof InterviewCreate>(key: K, value: number | number[]) => {
    setInterviewCreateForm({
      ...interviewCreateForm,
      [key]: value
    })
  }, [interviewCreateForm])

  return {
    interviewCreateForm, handleOnChange
  }
}
