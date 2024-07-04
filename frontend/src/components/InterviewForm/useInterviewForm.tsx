import {useInterviewQuestionLoadQuery} from "@/hooks/api/interview/useInterviewQuestionLoadQuery";
import {useInterviewSubmitMutation} from "@/hooks/api/interview/useInterviewSubmitMutation";
import {useCallback, useEffect, useState} from "react";
import useTailQuestionSubmitMutation from "@/hooks/api/question/useTailQuestionSubmitMutation";
import useAnswerFeedbackMutation from "@/hooks/api/answer/useAnswerFeedbackMutation";
import {Chat} from "@/types/question";
import {useNavigate} from "react-router-dom";
import {PATH} from "@/constants/path";
import {HTTPError} from "@/api/Interceptors";
import {toast} from "sonner";


interface InterviewForm {
  tailQuestionId?: number;
  currentTailQuestion: string;
  submitType: 'Question' | 'TailQuestion';
  remainTailQuestionCount: number;
  chatList: Chat[];
  answer: string;
}


export const useInterviewForm = (interviewId: number) => {

  const navigate = useNavigate();
  const {interview, refetch, error, isLoading: interviewLoading} = useInterviewQuestionLoadQuery(interviewId);
  const interviewSubmitMutation = useInterviewSubmitMutation();

  const answerFeedbackMutation = useAnswerFeedbackMutation();
  const tailQuestionSubmitMutation = useTailQuestionSubmitMutation();

  const [interviewForm, setInterviewForm] = useState<InterviewForm>({
    currentTailQuestion: "",
    submitType: 'Question',
    chatList: [],
    answer: "",
    remainTailQuestionCount:  interview.remainTailQuestionCount,
  })

  useEffect(() => {
    if(error instanceof HTTPError) {
      if(error.code === 403) {
        navigate(PATH.MY_INTERVIEW)
      }
    }
  }, [error]);

  const handleAnswerChange = useCallback((answer: string) => {
    setInterviewForm({...interviewForm, answer})
  } , [interviewForm])



  const appendChat = (chat: Chat) => {
    setInterviewForm(interviewForm => ({
        ...interviewForm,
        chatList: [
          ...interviewForm.chatList, chat
        ]}
    ))
  }

  const clearInput = () => {
    setInterviewForm(interviewForm => ({...interviewForm, answer: ""}));
  }

  const requestAiFeedback = async ({answer, question} : {answer: string, question: string}) => {
    return await answerFeedbackMutation.mutateAsync({
      answer,
      question,
      tailQuestions: [
        interview.question === question ? "" : interview.question,
        ...interviewForm.chatList
        .filter(chat => chat.type === "TailQuestion")
        .map(chat => chat.content)
        .filter((_, index, arr) => arr.length - 1 !== index)]
        .filter(str => str.length !== 0)
    })
  }

  const clearChat = () => {
    setInterviewForm(interviewForm => ({...interviewForm, submitType: "Question", chatList: []}))
  }

  const resetTailQuestionCount = () => {
    setInterviewForm(interviewForm => ({...interviewForm, remainTailQuestionCount: interview.remainTailQuestionCount}));
  }

  const consumeTailQuestionCount = (tailQuestionId: number) => {
    setInterviewForm(interviewForm => ({
      ...interviewForm,  tailQuestionId, submitType: "TailQuestion", remainTailQuestionCount: interviewForm.remainTailQuestionCount - 1
    }))
  }

  const registerTailQuestion = (question: string) => {
    setInterviewForm(interviewForm => ({
      ...interviewForm,
      currentTailQuestion: question
    }))
  }

  const submitAfterCleanup =  async (tailQuestionId: number | null) => {
    clearInput()

    if (tailQuestionId === null) {
      if (interview.index + 1 === interview.size) {
        navigate(PATH.INTERVIEW_RESULT(interviewId))
      }

      clearChat();
      await refetch();
      resetTailQuestionCount()
      return;
    }

    consumeTailQuestionCount(tailQuestionId);
  }

  /**
   * - 현재 답변 유형이 질문인 경우
   *   1. 내 답변과 질문을 통해서 (gpt) 피드백을 받습니다.
   *   2. 내 질문과 꼬리질문 피드백을 전부 서버로 보냄
   *   3. 꼬리질문이 있다면 질문 리스트에 넣음
   *   4. 꼬리질문이 없다면 다음 질문으로 넘어간다.
   *   5. 만약 현재 질문이 마지막 질문이면 면접을 종료 함
   * - 현재 답변 우형이 꼬리질문인 경우
   *   1. 내 답변과 질문을 통해서 (gpt) 피드백을 받는다.
   *   2. 내 질문과 현재 질문을 서버로 보냄
   *   3. 새로운 꼬리질문이 생성이 됐다면 현재 질문에서 새로운 꼬리질문을 바탕으로 면접을 이어감
   *   4. 꼬리질문이 생성되지 않았다면 다음 질문으로 넘어감
   *   5. 만약 현재 질문이 마지막 질문이면 면접을 종료 함
   */
  const handleSubmit = useCallback(async () => {

    if(interviewForm.answer.trim() === "") {
      toast.error("답변칸이 빈칸입니다.")
      return ;
    }


    if(interviewForm.submitType === 'TailQuestion'){
      appendChat({type: 'Answer', content: interviewForm.answer});

      const {tailQuestion, feedback, score} = await requestAiFeedback({
        answer: interviewForm.answer,
        question: interviewForm.currentTailQuestion
      });

      appendChat({type: 'TailQuestion', content: tailQuestion});
      registerTailQuestion(tailQuestion);

      if(interviewForm.tailQuestionId === undefined) {
        throw Error("알 수 없는 에러가 발생했습니다")
      }

      const {tailQuestionId} = await tailQuestionSubmitMutation.mutateAsync({
        aiFeedback: feedback,
        answerContent: interviewForm.answer,
        answerState: "COMPLETE",
        interviewQuestionId: interview.interviewQuestionId,
        tailQuestion: tailQuestion,
        tailQuestionId:  interviewForm.tailQuestionId,
        timeToAnswer: 1,
        score: score
      })

      await submitAfterCleanup(tailQuestionId)
      return ;
    }

    if(interviewForm.submitType === 'Question') {

      appendChat({type: 'Answer', content: interviewForm.answer})

      const { tailQuestion, feedback,score} = await requestAiFeedback({
        answer: interviewForm.answer,
        question: interview.question
      });

      setInterviewForm(interviewForm => ({...interviewForm, chatList: [...interviewForm.chatList, {type: 'TailQuestion', content: tailQuestion}]}))
      registerTailQuestion(tailQuestion);

      const {tailQuestionId} = await interviewSubmitMutation.mutateAsync({
        ...interview,
        answerState: "COMPLETE",
        answerContent: interviewForm.answer,
        timeToAnswer: 0,
        aiFeedback: feedback,
        currentIndex: interview.index,
        tailQuestion: tailQuestion,
        score
      })

      await submitAfterCleanup(tailQuestionId);
    }
  }, [interview, interviewForm]);


  const handlePass = useCallback(async () => {
    if(interviewForm.submitType === 'Question') {
      const {tailQuestionId} = await interviewSubmitMutation.mutateAsync({
        ...interview,
        answerState: "PASS",
        answerContent: "",
        timeToAnswer: 0,
        aiFeedback: "",
        currentIndex: interview.index,
        tailQuestion: "",
        score: 0
      })
      await submitAfterCleanup(tailQuestionId);
      return ;
    }

    if(interviewForm.submitType === 'TailQuestion') {

      if(interviewForm.tailQuestionId === undefined) {
        //해당 에러 발생하면 잘못 작성한거임
        throw Error("알 수 없는 에러가 발생했습니다")
      }

      const {tailQuestionId} = await tailQuestionSubmitMutation.mutateAsync({
        aiFeedback: "",
        answerContent: interviewForm.answer,
        answerState: "PASS",
        interviewQuestionId: interview.interviewQuestionId,
        tailQuestion: "",
        tailQuestionId:  interviewForm.tailQuestionId,
        timeToAnswer: 0,
        score: 0
      })
      await submitAfterCleanup(tailQuestionId);
    }
  }, [interview, interviewForm])


  return {
    interview,
    interviewLoading,
    handleSubmit,
    handlePass,
    feedbackWaiting: answerFeedbackMutation.isPending || (interviewSubmitMutation.isPending || tailQuestionSubmitMutation.isPending),
    remainTailQuestionCount: interviewForm.remainTailQuestionCount,
    chatList: interviewForm.chatList,
    answer: interviewForm.answer,
    handleAnswerChange
  }
}
