

interface QuestionSetIdColumnProps {
  id: number;
}

const QuestionSetIdColumn = ({id}: QuestionSetIdColumnProps) => {
  return <div>{id >= 0 ? id : "NEW"}</div>
}
export default QuestionSetIdColumn;
