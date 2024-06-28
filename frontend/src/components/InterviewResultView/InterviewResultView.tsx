import {useInterviewResultQuery} from "@/hooks/api/interview/useInterviewResultQuery";
import {Chip, Spacer} from "@nextui-org/react";
import {InterviewQuestionDetail, MyInterviewResult, TailQuestionDetail} from "@/types/interview";



interface InterviewItemBlockProps {
  id: number;
  answerState: "INIT" | "PASS" | "COMPLETE",
  question: string;
  answer: string;
  referenceLinks?: string;
  feedback: string;
  score: number;
}

const InterviewItemBlock = ({id, answer, question, answerState, feedback, score}: InterviewItemBlockProps) => {
  return <section id={question} key={id} className='mb-8'>
    <h2 className='text-3xl font-semibold mb-3 flex items-center gap-1'>
      <span>{question}</span>
    </h2>
    <Chip color='warning' variant='dot'>{score}점</Chip>
    <Spacer y={3}/>
    {answerState === 'COMPLETE' && <p className='mb-4'>
        <span className="text-2xl">내 답변</span>
        <br/>{answer}</p>
    }
    {answerState === 'COMPLETE' && <p className='mb-4'>
        <span className="text-2xl">AI 피드백</span>
        <br/>{feedback}</p>
    }
  </section>
}

interface InterviewResultViewProps {
  interviewId: number;
}

const InterviewResultView = ({interviewId}: InterviewResultViewProps) => {
  const {interviewResult} = useInterviewResultQuery(interviewId);
  return (
    <div className='container mx-auto py-8'>
      <h1 className='text-4xl font-bold mb-4'>{interviewResult.title}</h1>

      <div className='p-4 rounded shadow mb-8'>
        <h2 className='text-2xl font-semibold mb-2'>질문목록</h2>
        <ul className='list-disc list-inside'>
        {interviewResult.interviewQuestions.map(({interviewQuestionId, question, tailQuestions}) => (
            <li key={interviewQuestionId}>
              <a href={`#${question}`} className='text-blue-500 hover:underline'>{question}</a>
              <ul className='list-disc list-inside ml-4'>
                {tailQuestions.map(({tailQuestionId, question}) => (
                  <li key={tailQuestionId}>
                    <a href={`#${question}`} className='text-blue-500 hover:underline'>
                      {question}
                    </a>
                  </li>
                ))}
              </ul>
            </li>
          ))}
        </ul>
      </div>

      <div className='p-8 rounded shadow'>
        {interviewResult.interviewQuestions.map(interviewQuestion => (
          <>
          <InterviewItemBlock id={interviewQuestion.interviewQuestionId} {...interviewQuestion} />
            {interviewQuestion.tailQuestions.map(tailQuestion => (
              <InterviewItemBlock id={tailQuestion.tailQuestionId} {...tailQuestion} />
            ))}
          </>
        ))}
      </div>
    </div>
  )
}

export default InterviewResultView;
