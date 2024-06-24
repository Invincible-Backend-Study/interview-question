import {Modal,Spacer, useDisclosure,} from "@nextui-org/react";
import {useQuestionSetQuery} from "@/hooks/api/question/useQuestionSetQuery";
import {useCallback, useEffect, useRef, useState} from "react";
import {InterviewSettings} from "@/types/interview";
import InterviewCreateForm from "@/components/InterviewCreatorForm/InterviewCreateForm";
import QuestionSetItemList from "@/components/QuestionSetItem/QuestionSetItemList";
import {useIntersectionObserver} from "@/hooks/useIntersectionObserver";

export default function(){
  const {data, fetchNextPage} = useQuestionSetQuery();
  const {isOpen, onClose, onOpen} = useDisclosure();

  const ref = useRef<HTMLDivElement>(null)
  const entry = useIntersectionObserver(ref, {});

  const [settings, setInterviewSettings] = useState<InterviewSettings>({
    questionSetId: 0,
    tailQuestionDepth: 0,
    count: 0
  })

  const handleOpenInterviewSettings = useCallback((interviewSettings: InterviewSettings) => {
    setInterviewSettings(interviewSettings);
    onOpen();
  }, [settings])

  useEffect(() => {
    if (entry) {
      fetchNextPage();
    }
  }, [entry]);


  return (
    <div className="p-3">
      <span className="text-2xl">질문 목록</span>
      <Spacer y={10}/>

      <div className="p-5 flex flex-wrap gap-4">
        {data?.pages.map(data=> <QuestionSetItemList questionSetItems={data.content} openInterviewSetting={handleOpenInterviewSettings}/>)}
        <div ref={ref}/>
      </div>
      <Modal backdrop="blur" isDismissable={false} isKeyboardDismissDisabled={true} isOpen={isOpen} placement={"top"} className={`text-foreground bg-background dark`} onClose={onClose}>
        <InterviewCreateForm interviewSettings={settings} />
      </Modal>
    </div>
  )
}
