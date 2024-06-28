import {QuestionSet} from "@/types/questionSet";
import {InterviewCreateFormCommand} from "@/types/interview";
import QuestionSetItem from "./QuestionSetItem";


interface QuestionSetItemListProps {
  questionSetItems: QuestionSet[]

  openInterviewSetting: (command: InterviewCreateFormCommand) => void;
}

const QuestionSetItemList = ({questionSetItems, openInterviewSetting}: QuestionSetItemListProps) => {

  return (
    <>
      {questionSetItems.map(questionSetItem => <QuestionSetItem key={questionSetItem.questionSetId} questionSet={questionSetItem} openInterviewSetting={openInterviewSetting}/>)}
    </>
  )

}

export default QuestionSetItemList;
