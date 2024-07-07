import {Button, ButtonGroup} from "@nextui-org/react";
import {CiSearch, CiSquarePlus} from "react-icons/ci";


interface AdminEditorButtonProps {
  add: () => void;
}

const AdminEditorButton = ({add}: AdminEditorButtonProps) => {
  return <ButtonGroup className="pt-3 pb-3" variant="ghost" radius="none">
    <Button isIconOnly onClick={add}><CiSquarePlus/></Button>
    <Button isIconOnly><CiSearch/></Button>
    <Button isIconOnly><CiSearch/></Button>
    <Button isIconOnly><CiSearch/></Button>
  </ButtonGroup>
}
export default AdminEditorButton;
