
interface QuestionSetTextColumnProps {
  text?: string | number;
}

const QuestionSetTextColumn = ({text}: QuestionSetTextColumnProps) => {
  return <div>{text}</div>
}

export default QuestionSetTextColumn;
