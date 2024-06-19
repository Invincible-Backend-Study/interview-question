import {useParams} from "react-router-dom";
import InterviewController from "@/components/InterviewController/InterviewController";
import {Accordion, AccordionItem,  ScrollShadow, Textarea} from "@nextui-org/react";
import ComputerChat from "@/components/Chat/ComputerChat";
import UserChat from "@/components/Chat/UserChat";

const InterviewPage = () => {
  const {interviewId} = useParams();

  if(interviewId === undefined) {
    window.location.assign("/");
    return;
  }


  return (
    <div className="grid grid-rows-3 grid-flow-col gap-3 min-h-screen">
      <div className="row-span-3 border-r-1 p-3">
          <span>총 50문제</span>
        <ScrollShadow className="h-[90vh]">
        <Accordion variant="splitted">
          <AccordionItem key="1" aria-label="check Accordion 1" title="✅ Accordion 1" />
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>

          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
          <AccordionItem key="2" aria-label="Accordion 2" title="📌 Accordion 2"/>
        </Accordion>
        </ScrollShadow>
      </div>
      <div className='row-span-2 col-span-2  border-l-1 border-b-1 grid grid-rows-4 grid-flow-col gap-1 h-full p-3'>
        <div className='row-span-4 col-auto'>
          <p className="text-3xl">자바에서 equals()와 hashCode에 대해 설명해주세요111111111111.</p>
          <ScrollShadow className="h-[60vh] p-5 flex flex-col gap-4">
            <ComputerChat type={"TailQuestion"} score={100} feedback={""} tailQuestion={"HashMap에 대해서 설명해주세요"}></ComputerChat>
            <UserChat type={"Answer"} content={"hashCode가 해시맵에서 사용됩니다"}/>
            <ComputerChat type={"TailQuestion"} score={100} feedback={""} tailQuestion={"HashMap에 대해서 설명해주세요"}></ComputerChat>
            <UserChat type={"Answer"} content={"hashCode가 해시맵에서 사용됩니다"}/>
            <ComputerChat type={"TailQuestion"} score={100} feedback={""} tailQuestion={"HashMap에 대해서 설명해주세요"}></ComputerChat>
            <UserChat type={"Answer"} content={"hashCode가 해시맵에서 사용됩니다"}/>
          </ScrollShadow>
        </div>

      </div>
      <div className="row-span-1 border-t-1 border-l-1 col-span-2 grid grid-rows-4 grid-flow-col gap-1 h-full p-3">
        <div className="row-span-3">
          <Textarea placeholder="여기에 답을 적어주세요" minRows={10} rows={10} maxRows={10}/>
        </div>
        <div className="row-span-1 flex flex-col-reverse">
          <InterviewController/>
        </div>
      </div>
    </div>
  )
}

export default InterviewPage;
