import {CSSProperties, PropsWithChildren} from "react";
import AdminEditorButton from "@/components/AdminEditorButton/AdminEditorButton";
import QuestionSetTable from "@/components/QuestionSetTable/QuestionSetTable";
import useQuestionSetManage from "@/pages/QuestionSetManage/useQuestionSetMaange";
import QuestionSetPreview from "@/components/QuestionSetPreview/QuestionSetPreview";
import QuestionSetEditForm from "@/components/QuestionSetTable/QuestionSetEditForm";
import {Modal, useDisclosure} from "@nextui-org/react";
import useQuestionSetEditForm from "@/components/QuestionSetTable/useQuestionSetEditForm";
import {useQuestionSetLoader} from "@/pages/QuestionSetManage/useQuestionSetLoader";
import {useAdminQuestionsQuery} from "@/hooks/api/question/useAdminQuestionsQuery";
import QuestionsTable from "@/components/QuestionsTable/QuestionsTable";

const border = "1px solid rgb(54, 54, 54)";

const QuestionSetManagePage = () => {
  const {isOpen, onOpen, onClose} = useDisclosure();
  const {questionSetList, pageInfo,selected} = useQuestionSetLoader();
  const {rows} = useQuestionSetManage(questionSetList ?? []);
  const {form,updateInputValue: updateFormValue } = useQuestionSetEditForm();
  const {questions,  isLoading} = useAdminQuestionsQuery(selected.selectedQuestionSetId)

  return <>
    <div className="flex flex-row justify-between gap-3 ">
      <Box style={{borderRight:border}}>
        <AdminEditorButton add={onOpen}/>
        <QuestionSetTable  rows={rows} {...pageInfo} selectionChange={selected.changeSelectedQuestionSet}/>
      </Box>

      <Box>
        <AdminEditorButton add={onOpen}/>
        <QuestionsTable rows={questions} isLoading={isLoading}/>
        <QuestionSetPreview questionSet={selected.selectedQuestionSet}/>
      </Box>
    </div>

    <Modal isOpen={isOpen} onClose={onClose}>
      <QuestionSetEditForm form={form} change={updateFormValue}/>
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


