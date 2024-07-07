import {Button, ModalBody, ModalContent, ModalHeader, ScrollShadow} from "@nextui-org/react";
import {FileUploader} from "react-drag-drop-files";
import {useFileReader} from "@/hooks/useFileReader";


interface TextPreviewProps {
  saveAction: (textLines: string[]) => void;
  close: () => void;
}


const TextPreview = ({saveAction, close} : TextPreviewProps) => {
  const {lines, handleChange} = useFileReader();
  const handleClick = () => {

    saveAction(lines);
    close();
  }

  return (
    <ModalContent>
      {_ => (
        <>
          <ModalHeader>파일 읽기</ModalHeader>
          <ModalBody>
            <FileUploader label="텍스트 파일을 올려주세요" handleChange={handleChange} name="file" types={["TXT", "MD", ""]}/>
            <ScrollShadow className="h-[30vh]">
            <ul className="flex flex-col gap-3">
              {lines.map((t, key) => <li key={key}>{t}</li>)}
            </ul>
            </ScrollShadow>
            <Button onClick={handleClick}>저장</Button>
          </ModalBody>
        </>
      )}
    </ModalContent>
  )
}

export default TextPreview;
