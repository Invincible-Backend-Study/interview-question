import {Button} from "@nextui-org/react";
import {EditorProps, UnitType} from "@/components/Editor/Editor.type";
import {IoMdAdd} from "react-icons/io";
import {FiSave} from "react-icons/fi";
import {MdDelete, MdOutlineCancel} from "react-icons/md";
import {TbDatabaseSearch} from "react-icons/tb";



const defaultEditor:EditorProps = {
  units: {
    SEARCH: {
      action: () => {
      },
      visible: true,
      disabled: false
    },
    ADD: {
      action: () => {
      },
      visible: true,
      disabled: false
    },
    DELETE: {
      action: () => {
      },
      visible: true,
      disabled: false
    },
    CANCEL: {
      action: () => {
      },
      visible: true,
      disabled: false
    },
    SAVE: {
      action: () => {
      },
      visible: true,
      disabled: false
    },
  }
}

const EditorIcon = ({item, size}: {item: string, size: number}) => {
  const cast = item as UnitType;
  switch(cast) {
    case "ADD": return <IoMdAdd size={size}/>
    case "SEARCH": return <TbDatabaseSearch size={size}/>
    case "SAVE": return <FiSave size={size}/>
    case "DELETE": return <MdDelete size={size}/>
    case "CANCEL": return <MdOutlineCancel size={size}/>
  }
}

const Editor = ({units} = defaultEditor) => {
  return <div className ="flex gap-3">
    {Object.entries(units).map(([key, {action, visible, disabled}]) =>
      visible && <Button size="lg" color="primary" isIconOnly key={key} onClick={action} disabled={disabled}>
      <EditorIcon item={key} size={25}/>
    </Button>)}
  </div>

}

export default Editor;
