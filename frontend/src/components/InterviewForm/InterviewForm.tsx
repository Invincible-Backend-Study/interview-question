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
import WaitingView from "@/components/WaitingView/WatingView";
import {useMediaQuery} from "@/hooks/useMediaQuery";
import InterviewQuestionCompactHistory from "@/components/InterviewQuestionHistory/InterviewQuestionCompactHistory";


interface InterviewFormProps{
  interviewId: number;
}

const border = "1px solid rgb(54, 54, 54)";


const InterviewForm = ({interviewId}: InterviewFormProps) => {
  const {
    interview,
    focusRef,
    interviewLoading,
    handleSubmit,
    handlePass,
    chatList,
    feedbackWaiting,
    remainTailQuestionCount,
    answer,handleAnswerChange
  } = useInterviewForm(interviewId); const navigator = useNavigate(); const disclosure = useDisclosure();
  const {isMobile } = useMediaQuery();
  const quit = useCallback(() => {
    navigator(PATH.MAIN_PAGE)
    toast.info("면접을 일시중답합니다.(다시 풀 수 있습니다)");
  }, [])

  useShortCut({save: handleSubmit, pass: handlePass, isBlocking:feedbackWaiting, quit});


  useEffect(() => {
    disclosure.onOpen();
  }, []);

  return <>
    <div className="min-h-screen max-h-screen w-full flex ">
      {!isMobile && <div className="w-[20%] min-w-[200px] p-3" style={{borderRight: border}}>
          <InterviewQuestionHistory size={interview.size} index={interview.index}/>
      </div>
      }

      <div className='min-h-full w-full flex flex-col justify-between' style={{
        borderBottom: border,
        borderLeft: border
      }}>
        {isMobile &&
            <div>
                <InterviewQuestionCompactHistory size={interview.size} index={interview.index}/>
            </div>
        }
        {interviewLoading ? <div className="h-full"><WaitingView message={"다음 문제로 넘어갑니다"}/></div>: (
          <>
            <div className='p-3'>
              <InterviewQuestionBoard
                chatList={chatList}
                remainTailQuestionCount={remainTailQuestionCount}/>
            </div>

            <div className="gap-1 h-full flex flex-col max-h-[310px] p-4"  style={{
              borderTop:border
            }}>
              <Textarea
                placeholder="여기에 답을 적어주세요"
                value={answer}
                ref={focusRef}
                onChange={(e) => handleAnswerChange(e.target.value)}
                minRows={10}
                rows={10}
                maxRows={10}
                disabled={feedbackWaiting}
              />
              <div className="row-span-1 flex flex-col-reverse" >
                <InterviewController
                  onQuit={quit}
                  disabled={feedbackWaiting}
                  onSubmit={handleSubmit}
                  onPass={handlePass}
                />
              </div>
            </div>
          </>
        )}
      </div>
      <InterviewNotification {...disclosure}/>
    </div>
  </>

}

export default InterviewForm;
