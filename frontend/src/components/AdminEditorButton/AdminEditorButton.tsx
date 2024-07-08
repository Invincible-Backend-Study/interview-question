import {Button, ButtonGroup} from "@nextui-org/react";
import {CiFloppyDisk, CiFolderOn, CiSquarePlus} from "react-icons/ci";


interface AdminEditorButtonProps {
  add?: () => void;
  file?:() => void;
  save?: () => void;
}

const AdminEditorButton = ({add, file, save}: AdminEditorButtonProps) => {
  return <ButtonGroup className="pt-3 pb-3" variant="ghost" radius="none">
    {add  && <Button isIconOnly onClick={add}><CiSquarePlus size={26}/></Button>}
    {file && <Button isIconOnly onClick={file}><CiFolderOn size={26}/></Button>}
    {save && <Button isIconOnly onClick={save}><CiFloppyDisk size={26}/></Button> }
  </ButtonGroup>
}
export default AdminEditorButton;
