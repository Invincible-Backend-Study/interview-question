import {Accordion, AccordionItem, ScrollShadow} from "@nextui-org/react";

interface InterviewQuestionHistoryProps {
  /**
   * 문제 수
   */
  size: number;

  index: number;
}


const InterviewQuestionHistory = ({size, index}: InterviewQuestionHistoryProps) => {
  return <>
    <span>{size}/{index+1}</span>
    <ScrollShadow className="h-[90vh]">
      <Accordion variant="splitted">
        {new Array(size).fill(0).map((_, itemQuestionIndex) =>
          <AccordionItem key={itemQuestionIndex+ 2} title={` ${ itemQuestionIndex < index ?  '✅': '📌'}  문제 ${itemQuestionIndex + 1}`}/>
        )}
      </Accordion>
    </ScrollShadow>
  </>
}

export default InterviewQuestionHistory;
