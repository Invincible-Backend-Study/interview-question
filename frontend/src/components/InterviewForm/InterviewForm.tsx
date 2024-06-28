import InterviewQuestionHistory from "@/components/InterviewQuestionHistory/InterviewQuestionHistory";
import InterviewQuestionBoard from "@/components/InterviewQuestionBoard/InterviewQuestionBoard";
import {Textarea} from "@nextui-org/react";
import InterviewController from "@/components/InterviewController/InterviewController";
import {useInterviewForm} from "@/components/InterviewForm/useInterviewForm";
import {useShortCut} from "@/hooks/useShortCut";


interface InterviewFormProps{
  interviewId: number;
}

const border = "1px solid rgb(54, 54, 54)";


const InterviewForm = ({interviewId}: InterviewFormProps) => {
  const {
    interview,
    handleSubmit,
    handlePass,
    chatList,
    feedbackWaiting,
    remainTailQuestionCount,
    answer,handleAnswerChange
  } = useInterviewForm(interviewId);

  useShortCut({save: handleSubmit, pass: handlePass, isBlocking:feedbackWaiting});


  return <>
    <div className="grid grid-rows-3 grid-flow-col gap-1 min-h-screen">
      <div className="row-span-3 border-r-1 p-3" style={{
        borderRight: border
      }}>
        <InterviewQuestionHistory size={interview.size} index={interview.index}/>
      </div>
      <div className='row-span-2 col-span-2 grid grid-rows-4 grid-flow-col gap-1 h-full p-3' style={{
        borderBottom: border,
        borderLeft: border
      }}>
        <div className='row-span-4 col-auto'>
          <InterviewQuestionBoard question={interview.question}
                                  chatList={chatList}
                                  remainTailQuestionCount={remainTailQuestionCount}/>
        </div>
      </div>

      <div className="row-span-1 col-span-2 grid grid-rows-4 grid-flow-col gap-1 h-full p-3" style={{
        borderTop: border,
        borderLeft: border
      }}>
        <div className="row-span-3">
          <Textarea
            placeholder="여기에 답을 적어주세요"
            value={answer}
            onChange={(e) => handleAnswerChange(e.target.value)}
            minRows={10}
            rows={10}
            maxRows={10}
            disabled={feedbackWaiting}
          />
        </div>
        <div className="row-span-1 flex flex-col-reverse">
          <InterviewController
            disabled={feedbackWaiting}
            onSubmit={handleSubmit}
            onPass={handlePass}
          />
        </div>
      </div>

    </div>
  </>

}

export default InterviewForm;
