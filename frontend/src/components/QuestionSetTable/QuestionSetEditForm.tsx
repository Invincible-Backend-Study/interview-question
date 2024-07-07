import {Button, Image, Input, ModalBody, ModalContent, ModalHeader, Slider} from "@nextui-org/react";
import {QuestionSetRow} from "@/types/admin/questionSet";
import {ChangeQuestionForm} from "@/components/QuestionSetTable/useQuestionSetEditForm";
import axios from "axios";
import {FileUploader} from "react-drag-drop-files";
import {useState} from "react";


interface QuestionSetEditFormProps {
  form: QuestionSetRow,
  change: ChangeQuestionForm,
}

const QuestionSetEditForm = ({change, form: {description, title, thumbnailUrl, defaultTailQuestionCount}}: QuestionSetEditFormProps) => {

  const [file, setFile] = useState<File | undefined>();
  const handleChange = (file: File) => {
    setFile(file);
  };

  return (
    <ModalContent>
      {_ => (
        <>
          <ModalHeader>질문 집합 등록/편집 폼</ModalHeader>
          <ModalBody>
            <span className="text-sm">바디용</span>

            <Input placeholder={"제목"} value={title} onValueChange={(t) => change("title", t)}/>

            <Input placeholder={"설명"} value={description} onValueChange={t => change("description",t)}/>


            <Slider label="기본 꼬리질문 개수" minValue={0} maxValue={20} defaultValue={defaultTailQuestionCount}  onChange={(n) => typeof n === 'number' && change("defaultTailQuestionCount", n)}/>

            <FileUploader label="이미지를 올려주세요" handleChange={handleChange} name="file" types={["PNG", "JPEG", "WEBP"]}/>
            {!thumbnailUrl && <Image src={file && URL.createObjectURL(file)}/>}
            {thumbnailUrl && <Image src={thumbnailUrl}/>}

            <Button>등록</Button>

          </ModalBody>
        </>
      )}
    </ModalContent>
  )
}
export default QuestionSetEditForm;
