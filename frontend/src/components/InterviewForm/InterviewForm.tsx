import InterviewQuestionHistory from "@/components/InterviewQuestionHistory/InterviewQuestionHistory";
import InterviewQuestionBoard from "@/components/InterviewQuestionBoard/InterviewQuestionBoard";
import {Textarea, useDisclosure} from "@nextui-org/react";
import InterviewController from "@/components/InterviewController/InterviewController";
import {useInterviewForm} from "@/components/InterviewForm/useInterviewForm";
import {useShortCut} from "@/hooks/useShortCut";
import InterviewNotification from "@/components/InterviewNotification/InterviewNotification";
import {useNavigate} from "react-router-dom";
import {useCallback, useEffect} from "react";
import {PATH} from "@/constants/path";
import {toast} from "sonner";


interface InterviewFormProps{
  interviewId: number;
}

const border = "1px solid rgb(54, 54, 54)";


const InterviewForm = ({interviewId}: InterviewFormProps) => {
  const {
    interview,
    interviewLoading,
    handleSubmit,
    handlePass,
    chatList,
    feedbackWaiting,
    remainTailQuestionCount,
    answer,handleAnswerChange
  } = useInterviewForm(interviewId);

  const navigator = useNavigate();

  const quit = useCallback(() => {
    navigator(PATH.MAIN_PAGE)
    toast.info("면접을 일시중답합니다.(다시 풀 수 있습니다)");
  }, [])


  useShortCut({save: handleSubmit, pass: handlePass, isBlocking:feedbackWaiting, quit});

  const disclosure = useDisclosure();

  useEffect(() => {
    disclosure.onOpen();
  }, []);

  return <>
    <div className="min-h-screen max-h-screen w-full flex ">
      <div className="w-[20%] p-3" style={{borderRight: border}}>
        <InterviewQuestionHistory size={interview.size} index={interview.index}/>
      </div>
      <div className='min-h-full w-full flex flex-col justify-between' style={{
        borderBottom: border,
        borderLeft: border
      }}>
        <div className='p-3'>
          <InterviewQuestionBoard
            isLoading={interviewLoading}
            question={interview.question}
            chatList={chatList}
            remainTailQuestionCount={remainTailQuestionCount}/>
        </div>

        <div className="gap-1 h-full flex flex-col max-h-[310px] p-4"  style={{
          borderTop:border
        }}>
          <Textarea
            placeholder="여기에 답을 적어주세요"
            value={answer}
            onChange={(e) => handleAnswerChange(e.target.value)}
            minRows={10}
            rows={10}
            maxRows={10}
            disabled={feedbackWaiting}
          />
          <div className="row-span-1 flex flex-col-reverse" >
            <InterviewController
              onQuit={quit}
              info={disclosure.onOpen}
              disabled={feedbackWaiting}
              onSubmit={handleSubmit}
              onPass={handlePass}
            />
          </div>
        </div>
      </div>
      <InterviewNotification {...disclosure}/>
    </div>
  </>

}

export default InterviewForm;
