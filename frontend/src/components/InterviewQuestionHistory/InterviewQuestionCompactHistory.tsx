import {Badge, Divider, ScrollShadow} from "@nextui-org/react";
import {BsCheck } from "react-icons/bs";

interface InterviewQuestionCompactHistoryProps {
  size: number;
  index: number;
}

const InterviewQuestionCompactHistory = ({size, index}: InterviewQuestionCompactHistoryProps) => {
  return (
    <>
      <span className="p-2">{size}/{index + 1}</span>
      <ScrollShadow
        orientation='horizontal'
        className='h-[50px] p-3 flex flex-row items-center gap-2 justify-items-center'>
        {new Array(size).fill(0).map((_, itemQuestionIndex) =>
          itemQuestionIndex < index ? (
            <Badge
              isOneChar
              content={<BsCheck/>}
              color='primary'
              placement='top-right'
            >
              <div key={itemQuestionIndex + 2} className='min-w-[30px] h-[30px] border-1 text-center'>
                {itemQuestionIndex + 1}
              </div>
            </Badge>
          ) : (
            <div key={itemQuestionIndex + 2} className='min-w-[30px] h-[30px] border-1 text-center'>
              {itemQuestionIndex + 1}
            </div>
          )
        )}
      </ScrollShadow>
      <Divider/>
    </>
  )

}

export default InterviewQuestionCompactHistory;
