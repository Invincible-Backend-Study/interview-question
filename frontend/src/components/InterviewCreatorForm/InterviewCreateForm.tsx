import {
  Button,
  ModalBody,
  ModalContent,
  ModalHeader,
  Slider
} from "@nextui-org/react";
import {problemCount, tailQuestionCount} from "@/components/InterviewCreatorForm/InterviewCreateForm.constant";
import {InterviewSettings} from "@/types/interview";
import {useInterviewCreateMutation} from "@/hooks/api/interview/useInterviewCreateMutation";
import {useCallback} from "react";


interface InterviewSettingsSliderProps {
  label: string;
  disabled?: boolean
}

const InterviewSettingsSlider = ({label, disabled=false}: InterviewSettingsSliderProps) => {
  return (
    <Slider
      isDisabled={disabled}
      label={label}
      showTooltip={true}
      step={0.1}
      formatOptions={{style: "percent"}}
      maxValue={1}
      minValue={0}
      marks={[
        {
          value: 0.2,
          label: "20%",
        },
        {
          value: 0.5,
          label: "50%",
        },
        {
          value: 0.8,
          label: "80%",
        },
      ]}
      defaultValue={0.2}
      className="max-w-md"
    />
  )
}

interface InterviewCreateFormProps {
  interviewSettings: InterviewSettings
}

const InterviewCreateForm = ({ interviewSettings: {questionSetId, count, tailQuestionDepth}}: InterviewCreateFormProps) => {

  const {mutate} = useInterviewCreateMutation();


  const handleInterviewCreate = useCallback(() => {
    mutate({questionSetId, tailQuestionDepth, totalProblemCount: count});
  }, []);

  return (
    <ModalContent>
      {(onClose) => (
        <>
        <ModalHeader>
          면접 시작하기
        </ModalHeader>
        <ModalBody>
          <Slider maxValue={count} {...problemCount}/>
          <Slider defaultValue={tailQuestionDepth}  {...tailQuestionCount}  color="danger" radius="none" formatOptions={{signDisplay: 'always'}}/>

          <p>*아직 지원하지 않습니다.</p>
          <InterviewSettingsSlider label={"문항 별 대기 시간"} disabled={true}/>
          <InterviewSettingsSlider label={"문항 별 제한 시간"} disabled={true}/>
          <Button onClick={handleInterviewCreate}>인터뷰 생성하기</Button>
        </ModalBody>
        </>
      )}
    </ModalContent>
  )
}

export default InterviewCreateForm;
