import {Chat} from "@/types/question";
import {useMediaQuery} from "@/hooks/useMediaQuery";


const ComputerChat = ({content}: Chat) => {
  const {isMobile} = useMediaQuery();
  return (
    <div className='flex items-start gap-2.5'>
      <div
        className={`flex flex-col w-full ${isMobile ? 'max-w-[70vw]' : 'max-w-[50vw]'} leading-1.5 p-4 border-gray-200 bg-gray-100 rounded-e-xl rounded-es-xl dark:bg-gray-700`}>
        <div className='flex items-center space-x-2 rtl:space-x-reverse'>
          <span className='text-sm font-semibold text-gray-900 dark:text-white'>꼬리 질문</span>
        </div>

        <p className='text-sm font-normal py-2.5 text-gray-900 dark:text-white'>
          {content}
        </p>
        <span className='text-sm font-normal text-gray-500 dark:text-gray-400'>AI가 생성한 질문입니다.(잘못된 답안을 만들 수 있기에 주의 부탁드립니다)</span>
      </div>
    </div>
  )
}

export default ComputerChat;
