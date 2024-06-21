import {Chat } from "@/types/question";


const ComputerChat = ({content}: Chat) => {
  return (
    <div className='flex items-start gap-2.5'>
      {/*<img className='w-8 h-8 rounded-full' src='/docs/images/people/profile-picture-3.jpg' alt='Jese image'/>*/}
      <div
        className='flex flex-col w-full max-w-[50vw] leading-1.5 p-4 border-gray-200 bg-gray-100 rounded-e-xl rounded-es-xl dark:bg-gray-700'>
        <div className='flex items-center space-x-2 rtl:space-x-reverse'>
          <span className='text-sm font-semibold text-gray-900 dark:text-white'>꼬리 질문</span>
        </div>

        <p className='text-sm font-normal py-2.5 text-gray-900 dark:text-white'>
          {content}
        </p>
        <span className='text-sm font-normal text-gray-500 dark:text-gray-400'>Delivered</span>
      </div>
    </div>
  )
}

export default ComputerChat;
