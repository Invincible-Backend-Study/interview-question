import {useInterviewQuestionLoadQuery} from "@/hooks/api/interview/useInterviewQuestionLoadQuery";
import {useInterviewSubmitMutation} from "@/hooks/api/interview/useInterviewSubmitMutation";
import {useCallback, useEffect, useRef, useState} from "react";
import useTailQuestionSubmitMutation from "@/hooks/api/question/useTailQuestionSubmitMutation";
import useAnswerFeedbackMutation from "@/hooks/api/answer/useAnswerFeedbackMutation";
import {useNavigate} from "react-router-dom";
import {PATH} from "@/constants/path";
import {HTTPError} from "@/api/Interceptors";
import {toast} from "sonner";
import {useManageChatList} from "@/components/InterviewForm/useManageChatList";
import {FeedbackResponse} from "@/types/interview";

interface InterviewForm {
  tailQuestionId?: number;
  currentTailQuestion: string;
  submitType: 'Question' | 'TailQuestion';
  remainTailQuestionCount: number;
  answer: string;
}


export const useInterviewForm = (interviewId: number) => {
  const navigate = useNavigate();
  const {interview, refetch, error, isLoading: interviewLoading} = useInterviewQuestionLoadQuery(interviewId);
  const interviewSubmitMutation = useInterviewSubmitMutation();
  const answerFeedbackMutation = useAnswerFeedbackMutation();
  const tailQuestionSubmitMutation = useTailQuestionSubmitMutation();
  const {chatList, handleAppendComputerChat, handleAppendUserChat} = useManageChatList({
    mainQuestion: interview.question
  });
  const [interviewForm, setInterviewForm] = useState<InterviewForm>({
    currentTailQuestion: "",
    submitType: 'Question',
    answer: "",
    remainTailQuestionCount:  interview.remainTailQuestionCount,
  })
  const focusRef = useRef<HTMLTextAreaElement>(null);

  useEffect(() => {
    // 컴포넌트가 마운트되면 input에 포커스 설정
    focusRef.current?.focus();
  }, [interviewForm, interview]);


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

  const clearInput = useCallback(() => {
    setInterviewForm(interviewForm => ({...interviewForm, answer: ""}));
    focusRef.current?.focus();
  }, [interviewForm])


  const requestAiFeedback = (
    {answer, question}: {answer: string, question: string},
    {onSuccess} : {onSuccess: (feedback: FeedbackResponse) => void}
  ) => {
    answerFeedbackMutation.mutate({
      answer,
      question,
      tailQuestions: chatList.filter(chat => chat.type !== "Answer")
        .map(chat => chat.content)
        .filter(str => str.length !== 0)
    }, {onSuccess, onError: () => {
        toast.error("답변을 피드백 하는데 실패했거나 서버가 불안정하여 실패했습니다. 재시도 하거나 복구 후 시도해주세요.")
    }})
  }




  const resetTailQuestionCount = () => {
    setInterviewForm(interviewForm => ({...interviewForm, submitType: "Question", remainTailQuestionCount: interview.remainTailQuestionCount}));
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
      await refetch();

      focusRef.current?.focus();
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
  const handleSubmit = useCallback(() => {

    if(interviewForm.answer.trim() === "") {
      toast.error("답변칸이 빈칸입니다.")
      return ;
    }
    if(interviewForm.submitType === 'TailQuestion'){

      handleAppendUserChat({answer:interviewForm.answer})

      requestAiFeedback({answer: interviewForm.answer, question: interviewForm.currentTailQuestion,}, {
        onSuccess: ({tailQuestion, feedback,score, referenceLinks}) => {
          if(interviewForm.tailQuestionId === undefined) {
            throw Error("알 수 없는 에러가 발생했습니다")
          }

          tailQuestionSubmitMutation.mutate({
            aiFeedback: feedback,
            answerContent: interviewForm.answer,
            answerState: "COMPLETE",
            interviewQuestionId: interview.interviewQuestionId,
            referenceLinks,
            tailQuestion: tailQuestion,
            tailQuestionId:  interviewForm.tailQuestionId,
            timeToAnswer: 1,
            score: score
          }, {
            onSuccess: ({tailQuestionId}) =>  {
              if(interviewForm.remainTailQuestionCount !== 0){
                registerTailQuestion(tailQuestion);
                handleAppendComputerChat({tailQuestion})
              }
              submitAfterCleanup(tailQuestionId)
            }
          })
        }
      })
      return ;
    }

    if(interviewForm.submitType === 'Question') {
      handleAppendUserChat({answer: interviewForm.answer});
      requestAiFeedback({
        answer: interviewForm.answer,
        question: interview.question
      }, {
        onSuccess: ({ tailQuestion, feedback,score , referenceLinks}) => {
          interviewSubmitMutation.mutate({
            ...interview,
            referenceLinks,
            answerState: "COMPLETE",
            answerContent: interviewForm.answer,
            timeToAnswer: 0,
            aiFeedback: feedback,
            currentIndex: interview.index,
            tailQuestion: tailQuestion,
            score
          }, {
            onSuccess: ({tailQuestionId}) => {
              if(interviewForm.remainTailQuestionCount !== 0){
                handleAppendComputerChat({tailQuestion});
                registerTailQuestion(tailQuestion);
              }
              submitAfterCleanup(tailQuestionId)
            },
            onError: (error) => toast.error(error.message)
          })
        }
      })
    }
  }, [interview, interviewForm, chatList]);


  const handlePass = useCallback(() => {

    if(interviewForm.submitType === 'Question') {
      interviewSubmitMutation.mutate({
        ...interview,
        answerState: "PASS",
        answerContent: "",
        timeToAnswer: 0,
        aiFeedback: "",
        referenceLinks: [],
        currentIndex: interview.index,
        tailQuestion: "",
        score: 0
      }, {
        onSuccess: ({tailQuestionId}) => {
          submitAfterCleanup(tailQuestionId)
          setInterviewForm(form => ({...form, submitType: 'Question'}))
        },
        onError: (error) => toast.error(error.message)
      })
      return ;
    }

    if(interviewForm.submitType === 'TailQuestion') {
      if(interviewForm.tailQuestionId === undefined) {
        //해당 에러 발생하면 잘못 작성한거임
        throw Error("알 수 없는 에러가 발생했습니다")
      }
      tailQuestionSubmitMutation.mutate ({
        aiFeedback: "",
        answerContent: interviewForm.answer,
        answerState: "PASS",
        interviewQuestionId: interview.interviewQuestionId,
        referenceLinks: [],
        tailQuestion: "",
        tailQuestionId:  interviewForm.tailQuestionId,
        timeToAnswer: 0,
        score: 0
      }, {
        onSuccess: ({tailQuestionId}) => {
          submitAfterCleanup(tailQuestionId)
          setInterviewForm(form => ({...form, submitType: 'Question'}))
        },
        onError: (error) => toast.error(error.message)
      })
    }
  }, [interview, interviewForm, chatList])


  return {
    focusRef,
    interview,
    interviewLoading,
    handleSubmit,
    handlePass,
    feedbackWaiting: answerFeedbackMutation.isPending || (interviewSubmitMutation.isPending || tailQuestionSubmitMutation.isPending),
    remainTailQuestionCount: interviewForm.remainTailQuestionCount,
    chatList: chatList,
    answer: interviewForm.answer,
    handleAnswerChange
  }
}
