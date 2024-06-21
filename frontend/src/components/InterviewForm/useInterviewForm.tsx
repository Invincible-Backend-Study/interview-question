import {useInterviewQuestionLoadQuery} from "@/hooks/api/interview/useInterviewQuestionLoadQuery";
import {useInterviewSubmitMutation} from "@/hooks/api/interview/useInterviewSubmitMutation";
import {useCallback, useState} from "react";
import useTailQuestionSubmitMutation from "@/hooks/api/question/useTailQuestionSubmitMutation";
import useAnswerFeedbackMutation from "@/hooks/api/answer/useAnswerFeedbackMutation";
import {Chat} from "@/types/question";


interface InterviewForm {
  tailQuestionId?: number;
  currentTailQuestion: string;
  submitType: 'Question' | 'TailQuestion',
  remainTailQuestionCount: number,
  chatList: Chat[]
}


export const useInterviewForm = (interviewId: number) => {
  const {interview, refetch} = useInterviewQuestionLoadQuery(interviewId);
  const interviewSubmitMutation = useInterviewSubmitMutation();
  const answerFeedbackMutation = useAnswerFeedbackMutation();
  const tailQuestionSubmitMutation = useTailQuestionSubmitMutation();

  const [interviewForm, setInterviewForm] = useState<InterviewForm>({
    currentTailQuestion: "",
    submitType: 'Question',
    chatList: [],
    remainTailQuestionCount:  interview.remainTailQuestionCount
  })


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

    console.log(interviewForm, interview)
    if(interviewForm.submitType === 'TailQuestion'){
      setInterviewForm(interviewForm => ({...interviewForm,
        chatList: [
          ...interviewForm.chatList, {type: 'Answer', content: "답변입니다"}
        ]}))

      const {tailQuestion} = await answerFeedbackMutation.mutateAsync({
        answer: "답변입니다",
        question: interviewForm.currentTailQuestion
      })

      setInterviewForm(interviewForm => ({...interviewForm, chatList: [
          ...interviewForm.chatList, {type: 'TailQuestion', content: tailQuestion}
        ]}))

      const {tailQuestionId} = await tailQuestionSubmitMutation.mutateAsync({
        aiFeedback: "피드백입니다",
        answerContent: "내 답변입니다",
        answerState: "COMPLETE",
        interviewQuestionId: interview.interviewQuestionId,
        tailQuestion: "꼬리질문입니다",
        tailQuestionId:  interviewForm.tailQuestionId,
        timeToAnswer: 1
      })

      console.log(tailQuestionId);

      if(tailQuestionId === null) {
        console.log("꼬리질문 남은 개수 없음")
        if(interview.index + 1 === interview.size) {
          alert("면접 종료");
          return;
        }

        setInterviewForm(interviewForm => ({...interviewForm, submitType: "Question", chatList: []}))
        await refetch();
        setInterviewForm(interviewForm => ({...interviewForm, remainTailQuestionCount: interview.remainTailQuestionCount}));
        return ;
      }
      setInterviewForm(interviewForm => ({...interviewForm, tailQuestionId,
        remainTailQuestionCount: interviewForm.remainTailQuestionCount - 1,
      }))


      return ;
    }

    if(interviewForm.submitType === 'Question') {
      setInterviewForm(interviewForm => ({...interviewForm, chatList: [
          ...interviewForm.chatList, {type: 'Answer', content: "답변입니다"}
        ]}))

      const {originalContent,  tailQuestion, feedback} = await answerFeedbackMutation.mutateAsync({
        answer: "답변입니다",
        question: "질문입니다"
      })

      setInterviewForm(interviewForm => ({...interviewForm, chatList: [...interviewForm.chatList, {type: 'TailQuestion', content: tailQuestion}]}))

      const {tailQuestionId} = await interviewSubmitMutation.mutateAsync({
        ...interview,
        answerState: "COMPLETE",
        answerContent: "",
        originalContent,
        timeToAnswer: 0,
        aiFeedback: feedback,
        currentIndex: interview.index,
        tailQuestion: tailQuestion,
      })

      if(tailQuestionId === null) {
        if(interview.index + 1 === interview.size) {
          alert("면접 종료");
          return;
        }

        setInterviewForm(interviewForm => ({...interviewForm, submitType: "Question", chatList: []}))
        await refetch();
        setInterviewForm(interviewForm => ({...interviewForm, remainTailQuestionCount: interview.remainTailQuestionCount}));
        return;
      }
      setInterviewForm(interviewForm => ({
        ...interviewForm,  tailQuestionId, submitType: "TailQuestion", remainTailQuestionCount: interviewForm.remainTailQuestionCount - 1
      }))
    }
  }, [interview, interviewForm]);


  const handlePass = useCallback(async () => {
    interviewSubmitMutation.mutate({
      ...interview,
      answerState: "PASS",
      answerContent: "",
      originalContent: "",
      timeToAnswer: 0,
      aiFeedback: "aaa",
      currentIndex: interview.index,
      tailQuestion: "다음 질문은 이거에용",
    })

    await refetch();
  }, [interview, interviewForm])


  return {
    interview,
    handleSubmit,
    handlePass,
    remainTailQuestionCount: interviewForm.remainTailQuestionCount,
    chatList: interviewForm.chatList
  }
}
