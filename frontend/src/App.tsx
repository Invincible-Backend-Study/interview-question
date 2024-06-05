import {
  Button,
  Image,
  Link,
  Navbar,
  NavbarBrand,
  NavbarContent,
  NavbarItem,
  ScrollShadow,
  Textarea
} from "@nextui-org/react";
import {useAnswerQuestionMutation} from "@/hooks/api/question/useAnswerQuestionMutation";
import {useState} from "react";
import {useInterview} from "@/hooks/useInterview";
import {Answer, MainQuestion, TailQuestion} from "@/types/question";

const Chat = ({score, feedback, tailQuestion}: TailQuestion) => {
  return (
    <div className='flex items-start gap-2.5'>
      {/*<img className='w-8 h-8 rounded-full' src='/docs/images/people/profile-picture-3.jpg' alt='Jese image'/>*/}
      <div
        className='flex flex-col w-full max-w-[50vw] leading-1.5 p-4 border-gray-200 bg-gray-100 rounded-e-xl rounded-es-xl dark:bg-gray-700'>
        <div className='flex items-center space-x-2 rtl:space-x-reverse'>
          <span className='text-sm font-semibold text-gray-900 dark:text-white'>꼬리 질문</span>
          <span className='text-sm font-normal text-gray-500 dark:text-gray-400'>점수: {score}</span>
        </div>

        <p className='text-sm font-normal py-2.5 text-gray-900 dark:text-white'>
          피드백<br/>
          {feedback}
        </p>
        <p className='text-sm font-normal py-2.5 text-gray-900 dark:text-white'>
          {tailQuestion}
        </p>
        <span className='text-sm font-normal text-gray-500 dark:text-gray-400'>Delivered</span>
      </div>
    </div>
  )
}

const UserChat = ({content}: Answer) => {
  return (
    <div className='flex flex-row-reverse gap-2.5'>
      {/*<img className='w-8 h-8 rounded-full' src='/docs/images/people/profile-picture-3.jpg' alt='Jese image'/>*/}
      <div
        className='flex flex-col w-full max-w-[50vw] leading-1.5 p-4 border-gray-200 bg-gray-100 rounded-e-xl rounded-es-xl dark:bg-gray-700'>
        <p className='text-sm font-normal py-2.5 text-gray-900 dark:text-white'>
          {content}
        </p>
      </div>
    </div>
  )
}

const Sidebar = () => {
  return (
    <div className='h-screen w-64'>
      <ul className='p-4'>
        <li className='py-2'><a href='#'>질문 1.</a></li>
        <li className='py-2'><a href='#'>질문 2.</a></li>
        <li className='py-2'><a href='#'>질문 3.</a></li>
        <li className='py-2'><a href='#'>Contact</a></li>
      </ul>
    </div>
  );
}

const Header = () => {
  return (
    <Navbar maxWidth='full'>
      <NavbarContent justify='center' className='w-full'>
        <NavbarBrand>
          <Image
            src='https://user-images.githubusercontent.com/66772624/227790401-efdd3e2f-d9a5-49f0-9465-a774a1d901ac.png'
            width={40} height={40}/>
        </NavbarBrand>

        <NavbarContent justify='end'>
          <NavbarItem className='hidden lg:flex'>
            <Link href='#'>Login</Link>
          </NavbarItem>
          <NavbarItem>
            <Button as={Link} color='primary' href='#' variant='flat'>
              Sign Up
            </Button>
          </NavbarItem>
        </NavbarContent>
      </NavbarContent>
    </Navbar>
  )
}

function App() {
  const [answer, setAnswer] = useState("");

  const {
    questions,
    lastQuestion,
    appendTailQuestion,
    appendAnswer
  } = useInterview("Java의 equals() hashcode() 에 대해서 설명해주세요");


  const mutation = useAnswerQuestionMutation(appendTailQuestion);


  const handleAnswerQuestion = () => {
    appendAnswer(answer)
    mutation.mutate({
      question: lastQuestion,
      response: answer
    })
  }

  return (
    <>
      <div className='flex h-full'>
        <Sidebar/>
        <div className='flex-grow '>
          <Header/>

          <div className='h-[83vh]'>
            <h1 className='text-3xl font-bold '>Main Content</h1>
            <ScrollShadow className='p-5 h-full flex flex-col gap-4'>
              {questions.map((question, index) => {
                if (question.type === "MainQuestion") {
                  const mainQuestion = question as MainQuestion;
                  return <p key={index}>{mainQuestion.content}</p>
                }
                if (question.type === "TailQuestion") {
                  const tailQuestion = question as TailQuestion;
                  return <Chat key={index} {...tailQuestion}/>
                }

                if (question.type === "Answer") {
                  const answer = question as Answer;
                  return <UserChat key={index}{...answer}/>
                }

              })}
            </ScrollShadow>
          </div>
          <div className='flex justify-center'>
            <Textarea placeholder='입력해주세용' className='w-[80vw]' minRows={1} maxRows={4}
                      onValueChange={setAnswer}
                      endContent={<Button onClick={handleAnswerQuestion}>확인</Button>}/>
          </div>
        </div>
      </div>

    </>
  )
}

export default App
