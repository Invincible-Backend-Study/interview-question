import {Answer} from "@/types/question";

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


export default UserChat;
