import QuestionSetItem from "@/components/QuestionSetItem/QuestionSetItem";
import {Button, Modal, ScrollShadow, Spacer, useDisclosure,} from "@nextui-org/react";
import {useQuestionSetQuery} from "@/hooks/api/question/useQuestionSetQuery";
import {useCallback, useState} from "react";
import {InterviewSettings} from "@/types/interview";
import InterviewCreateForm from "@/components/InterviewCreatorForm/InterviewCreateForm";

export default function(){
  const {questionSetList } = useQuestionSetQuery();
  const {isOpen, onClose, onOpen} = useDisclosure();

  const [settings, setInterviewSettings] = useState<InterviewSettings>({
    questionSetId: 0,
    tailQuestionDepth: 0,
    count: 0
  })

  const handleOpenInterviewSettings = useCallback((interviewSettings: InterviewSettings) => {
    setInterviewSettings(interviewSettings);
    onOpen();
  }, [])


  return (
    <div className="p-3">
      <div>
        <span className="text-3xl">질문 목록</span>
      </div>
      <Spacer y={10}/>

      <ScrollShadow className="h-[85vh] p-5 flex flex-wrap gap-5">
        {questionSetList.map(questionSet => <QuestionSetItem
          key={questionSet.questionSetId}
          openInterviewSetting={handleOpenInterviewSettings}
          {...questionSet}
          />
        )}
      </ScrollShadow>
      <Modal backdrop="blur" isDismissable={false} isKeyboardDismissDisabled={true} isOpen={isOpen} placement={"top"} className={`text-foreground bg-background dark`} onClose={onClose}>
        <InterviewCreateForm interviewSettings={settings} />
      </Modal>
    </div>
  )
}
