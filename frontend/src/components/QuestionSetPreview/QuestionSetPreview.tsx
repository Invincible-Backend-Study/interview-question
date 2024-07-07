import QuestionSetItem from "@/components/QuestionSetItem/QuestionSetItem";
import {QuestionSetRow} from "@/types/admin/questionSet";

interface QuestionSetPreviewProps {
  questionSet?: QuestionSetRow
}

const QuestionSetPreview = ({questionSet}: QuestionSetPreviewProps) => {
  console.log(questionSet);
  return (
    <div className='w-full h-[50%] p-3'>
      <span>미리보기</span>
      <div className="flex flex-row gap-3">
        {questionSet && <QuestionSetItem
            questionSet={{
              questionSetId: 1,
              count: 10,
              title: questionSet.title,
              tailQuestionDepth: 10,
              description: questionSet.description,
              thumbnailUrl: questionSet.thumbnailUrl
            }}
            openInterviewSetting={() => {}}/>}
        <QuestionSetItem questionSet={{
          questionSetId: 1,
          count: 10,
          title: "여기서 편집해세요",
          tailQuestionDepth: 10,
          description: "여기서 자유롭게 편집하시죠",
          thumbnailUrl: "https://storage.googleapis.com/mubaegseu/jpa.png"
        }} openInterviewSetting={() => {}}/>
      </div>
    </div>
  )
}


export default QuestionSetPreview;
