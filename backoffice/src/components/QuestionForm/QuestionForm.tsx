import QuestionTable from "@/components/QuestionTable/QuestionTable";
import Editor from "@/components/Editor/Editor";
import {DEFAULT_EDITOR} from "@/components/Editor/Editor.constants";
import {Spacer} from "@nextui-org/react";



const QuestionForm = () => {
  return <>
    <Editor units={DEFAULT_EDITOR.units}/>
    <Spacer y={5}/>
    <QuestionTable/>
  </>
}

export default QuestionForm;
