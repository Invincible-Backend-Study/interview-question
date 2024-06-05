import {useMutation} from "@tanstack/react-query";
import {answerQuestionPost} from "@/api/question/AnswerQuestionPost";
import {Strings} from "@/utils/Strings";


const SEPARATOR = "---------";


export const useAnswerQuestionMutation = (setTailQuestion: (score: number, feedback: string, tailQuestion: string) => void) => {
  return useMutation({
    mutationKey: ['answerQuestion'],
    mutationFn: answerQuestionPost,
    onSuccess: (data) => {
      console.log(data.result);
      const filteredResponse = data.result
        .split(SEPARATOR)
        .map(Strings.trim)
        .filter(Strings.isNotBlank)
        .map(s => s.split(":")[1])
        .map(Strings.trim)

      console.log(filteredResponse)

      if (filteredResponse.length !== 3) {
        throw Error("입력 형식 처리과정에서 에러가 발생했습니다.");
      }

      setTailQuestion(Number(filteredResponse[0].replace("점", "")), filteredResponse[1], filteredResponse[2])
    },
    onError: (error, variables, context) => {
      console.log(error);
      console.log(variables)
      console.log(context);
    }
  })
}
