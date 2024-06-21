import InterviewQuestionHistory from "@/components/InterviewQuestionHistory/InterviewQuestionHistory";
import InterviewQuestionBoard from "@/components/InterviewQuestionBoard/InterviewQuestionBoard";
import {Textarea} from "@nextui-org/react";
import InterviewController from "@/components/InterviewController/InterviewController";
import {useInterviewForm} from "@/components/InterviewForm/useInterviewForm";


interface InterviewFormProps{
  interviewId: number;
}

const InterviewForm = ({interviewId}: InterviewFormProps) => {

  const {interview, handleSubmit, handlePass, chatList, remainTailQuestionCount} = useInterviewForm(interviewId);

  return <>
    <div className="grid grid-rows-3 grid-flow-col gap-3 min-h-screen">
      <div className="row-span-3 border-r-1 p-3">
        <InterviewQuestionHistory size={interview.size} index={interview.index}/>
      </div>
      <div className='row-span-2 col-span-2  border-l-1 border-b-1 grid grid-rows-4 grid-flow-col gap-1 h-full p-3'>
        <div className='row-span-4 col-auto'>
          <InterviewQuestionBoard question={interview.question}
                                  chatList={chatList}
                                  remainTailQuestionCount={remainTailQuestionCount}/>
        </div>
      </div>

      <div className="row-span-1 border-t-1 border-l-1 col-span-2 grid grid-rows-4 grid-flow-col gap-1 h-full p-3">
        <div className="row-span-3">
          <Textarea placeholder="여기에 답을 적어주세요" minRows={10} rows={10} maxRows={10}/>
        </div>
        <div className="row-span-1 flex flex-col-reverse">
          <InterviewController onSubmit={handleSubmit} onPass={handlePass}/>
        </div>
      </div>

    </div>
  </>

}

export default InterviewForm;
