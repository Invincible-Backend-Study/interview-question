import {useState} from "react";
import {Answer, MainQuestion, Question, TailQuestion} from "@/types/question";

export const useInterview = (mainQuestion: string) => {
  const [lastQuestion, setLastQuestion] = useState(mainQuestion);
  const [questions, setQuestions] = useState<Array<MainQuestion | Question | Answer>>([
    {content: mainQuestion, type: "MainQuestion"} as MainQuestion
  ]);

  const appendAnswer = (answer: string) => {
    console.log(answer);
    setQuestions(questions => [...questions, {content: answer, type: "Answer"} as Answer])
  }

  const appendTailQuestion = (score: number, feedback: string, tailQuestion: string) => {
    console.log(score, feedback, tailQuestion);
    setLastQuestion(tailQuestion);
    setQuestions(questions => [...questions, {score, feedback, tailQuestion, type: "TailQuestion"} as TailQuestion])
  }

  return {
    questions,
    lastQuestion,
    appendAnswer,
    appendTailQuestion
  }
}
