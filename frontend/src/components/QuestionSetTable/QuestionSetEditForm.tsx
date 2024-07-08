import {Button, Image, Input, ModalBody, ModalContent, ModalHeader, Slider} from "@nextui-org/react";
import {QuestionSetRow} from "@/types/admin/questionSet";
import {ChangeQuestionForm} from "@/components/QuestionSetTable/useQuestionSetEditForm";
import {FileUploader} from "react-drag-drop-files";
import {useCallback} from "react";
import {useImageUploadMutation} from "@/hooks/api/image/useImageUploadMutation";
import {toast} from "sonner";
import imageCompression from "browser-image-compression";


interface QuestionSetEditFormProps {
  form: QuestionSetRow;
  change: ChangeQuestionForm;
  confirm:() => void;
}

const QuestionSetEditForm = ({confirm, change, form: {description, title, thumbnailUrl, defaultTailQuestionDepth}}: QuestionSetEditFormProps) => {
  const imageUploadMutation = useImageUploadMutation();


  const compressImage = useCallback(async (originalImageFile: File) => {
    let imageFile: File;
    try {
      const compressedImageFile = await imageCompression(
        originalImageFile,
        {
          maxSizeMB: 1.5
        }
      );

      const fileName = originalImageFile.name;

      const fileType = compressedImageFile.type;

      imageFile = new File([compressedImageFile], fileName, { type: fileType });
    } catch (e) {
      imageFile = originalImageFile;
    }

    return imageFile;
  }, []);


  const handleChange = useCallback(async(file: File) => {

    const compressedFile = await compressImage(file);
    const formData = new FormData();
    formData.append("thumbnail", compressedFile);

    imageUploadMutation.mutate({thumbnail: formData}, {
      onSuccess: (thumbnailUrl) => {
        change("thumbnailUrl", thumbnailUrl)
      },
      onError: () => {
        toast.error("이미지 업로드에 실패했습니다.")
      }
    });
  }, []);





  return (
    <ModalContent>
      {onClose => (
        <>
          <ModalHeader>질문 집합 등록/편집 폼</ModalHeader>
          <ModalBody>
            <span className="text-sm">바디용</span>

            <Input placeholder={"제목"} value={title} onValueChange={(t) => change("title", t)}/>

            <Input placeholder={"설명"} value={description} onValueChange={t => change("description",t)}/>

            <Slider label="기본 꼬리질문 개수" minValue={0} maxValue={20} defaultValue={defaultTailQuestionDepth}  onChange={(n) => typeof n === 'number' && change("defaultTailQuestionDepth", n)}/>

            <FileUploader label="이미지를 올려주세요" handleChange={handleChange} name="file" types={["PNG", "JPEG", "WEBP"]}/>

            {thumbnailUrl && <Image src={thumbnailUrl}/>}

            <Button onClick={() => {
              confirm();
              onClose();
            }}>등록</Button>

          </ModalBody>
        </>
      )}
    </ModalContent>
  )
}
export default QuestionSetEditForm;
