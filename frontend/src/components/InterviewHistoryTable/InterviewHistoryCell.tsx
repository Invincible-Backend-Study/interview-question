import {MyInterview} from "@/types/interview";
import {Key} from "react";
import {TableCell} from "@nextui-org/react";


interface InterviewHistoryCellProps {
  item: MyInterview,
  cellType: keyof MyInterview;

}


const InterviewHistoryCell = ({item, cellType}: InterviewHistoryCellProps) => {

  const cellValue = item[cellType as keyof MyInterview];


  switch(cellType) {
    case "createdAt":
    case "updatedAt":
      return <div>{1234}</div>
    default:
      return cellValue;
  }
}
export default (props:InterviewHistoryCellProps) => <TableCell><InterviewHistoryCell {...props}/></TableCell>;

