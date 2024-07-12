import {Modal, Spacer, useDisclosure,} from "@nextui-org/react";
import {useQuestionSetQuery} from "@/hooks/api/question/useQuestionSetQuery";
import {useCallback, useEffect, useRef, useState} from "react";
import {InterviewSettings} from "@/types/interview";
import InterviewCreateForm from "@/components/InterviewCreatorForm/InterviewCreateForm";
import QuestionSetItemList from "@/components/QuestionSetItem/QuestionSetItemList";
import {useIntersectionObserver} from "@/hooks/useIntersectionObserver";
import QuestionSetItemListSkeleton from "@/components/QuestionSetItem/QuestionSetItemListSkeleton";
import {useMediaQuery} from "@/hooks/useMediaQuery";

export default function(){
  const {data, fetchNextPage, isLoading} = useQuestionSetQuery();
  const {isOpen, onClose, onOpen} = useDisclosure();
  const { modalPlacement, isMobile} = useMediaQuery();

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
    <div className="p-3 ">
      <span className="text-2xl text-warning">질문 목록</span>
      <Spacer y={10}/>

      <div className="flex justify-center">
        <div className={`flex flex-wrap gap-6 ${isMobile ? "justify-center" : "justify-start"} min-w-full max-w-screen-lg`}>
          {data?.pages.map((data, index)=> <QuestionSetItemList key={index} questionSetItems={data.content} openInterviewSetting={handleOpenInterviewSettings}/>)}
          {isLoading && <QuestionSetItemListSkeleton/>}
          <div ref={ref}/>
        </div>
      </div>
      <Modal backdrop="opaque"
             isDismissable={false}
             isKeyboardDismissDisabled={true}
             isOpen={isOpen}
             placement={modalPlacement}
             onClose={onClose}>
        <InterviewCreateForm interviewSettings={settings}/>
      </Modal>
    </div>
  )
}
