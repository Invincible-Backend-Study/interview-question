import {Button} from "@nextui-org/react";
import {CiPen,  CiTrash} from "react-icons/ci";

const QuestionSetActionColumn = () => {
  return <>
    <Button isIconOnly variant="light"><CiPen size={20}/></Button>
    <Button isIconOnly variant="light"><CiTrash size={20}/></Button>
  </>
}

export default QuestionSetActionColumn;
