import QuestionSetItem from "@/components/QuestionSetItem/QuestionSetItem";
import {Button, Modal, ScrollShadow, Spacer, useDisclosure,} from "@nextui-org/react";
import InterviewCreateForm from "@/components/InterviewCreatorForm/InterviewCreateForm";

export default function(){
  const {isOpen, onClose, onOpen} = useDisclosure();

  return (
    <div className="p-3">
      <div>
        <span className="text-3xl">질문 목록</span>
        <Button onClick={onOpen}>임시</Button>
      </div>
      <Spacer y={10}/>

      <ScrollShadow className="h-[85vh] p-5 flex flex-wrap gap-5">
        {new Array(100).fill( 0).map(_=> <QuestionSetItem/>)}
        <Modal backdrop="blur" isDismissable={false} isKeyboardDismissDisabled={true} isOpen={isOpen} placement={"top"} className={`text-foreground bg-background dark`} onClose={onClose}>
          <InterviewCreateForm/>
        </Modal>
      </ScrollShadow>
    </div>
  )
}
