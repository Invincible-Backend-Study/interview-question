import {CSSProperties, PropsWithChildren} from "react";
import AdminEditorButton from "@/components/AdminEditorButton/AdminEditorButton";
import QuestionSetTable from "@/components/QuestionSetTable/QuestionSetTable";
import useQuestionSetManage from "@/pages/QuestionSetManage/useQuestionSetManage";
import QuestionSetPreview from "@/components/QuestionSetPreview/QuestionSetPreview";
import QuestionSetEditForm from "@/components/QuestionSetTable/QuestionSetEditForm";
import {Modal, useDisclosure} from "@nextui-org/react";
import {useQuestionSetLoader} from "@/pages/QuestionSetManage/useQuestionSetLoader";
import QuestionsTable from "@/components/QuestionsTable/QuestionsTable";
import {useQuestionsManage} from "@/pages/QuestionSetManage/useQuestionsManage";
import TextPreview from "@/components/TextPreview/TextPreview";

const border = "1px solid rgb(54, 54, 54)";

const QuestionSetManagePage = () => {
  const {isOpen, onOpen, onClose} = useDisclosure();
  const {isOpen: textPreviewIsOpen, onOpen: textPreviewOpen, onClose: textPreviewClose} = useDisclosure();
  const {questionSetList, pageInfo,selected} = useQuestionSetLoader();
  const {rows: questionSetRows, form, updateInputValue, handleRegisterNewQuestionSet} = useQuestionSetManage(questionSetList ?? []);

  const {questions, isLoading, handlePrependRows, handleRemove, handleSave} = useQuestionsManage(selected.selectedQuestionSetId);

  return <>
    <div className="flex flex-row justify-between gap-3 ">
      <Box style={{borderRight:border}}>
        <AdminEditorButton add={onOpen}/>
        <QuestionSetTable  rows={questionSetRows} {...pageInfo} selectionChange={selected.changeSelectedQuestionSet}/>
      </Box>

      <Box>
        <AdminEditorButton  save={handleSave} file={textPreviewOpen}/>
        <QuestionsTable rows={questions} isLoading={isLoading}/>
        <QuestionSetPreview questionSet={selected.selectedQuestionSet}/>
      </Box>
    </div>

    <Modal isOpen={isOpen} onClose={onClose}>
      <QuestionSetEditForm form={form} change={updateInputValue} confirm={handleRegisterNewQuestionSet}/>
    </Modal>

    <Modal isOpen={textPreviewIsOpen} onClose={textPreviewClose}>
      <TextPreview close={textPreviewClose} saveAction={handlePrependRows}/>
    </Modal>
  </>
}


const Box = ({children, style}: PropsWithChildren &  {style?: CSSProperties} ) => {
  return(
    <div className={"w-[50%] max-h-full p-3"} style={style}>
      {children}
    </div>
  )
}

export default QuestionSetManagePage;


