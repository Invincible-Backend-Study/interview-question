import {dateToString} from "@/utils/Date";


interface QuestionSetDateColumnProps {
  date?: string | Date
}

const QuestionSetDateColumn = ({date} : QuestionSetDateColumnProps) => {
  return <div>{date === undefined ? "" : dateToString(new Date(date))}</div>
}

export default QuestionSetDateColumn;
