import {Answer} from "@/types/question";

const UserChat = ({content, isError}: Answer) => {
  return (
    <div className={`flex flex-row-reverse gap-2.5 ${isError ? "text-warning" : ""} `}>
      <div
        className='flex flex-col w-full max-w-[50vw] leading-1.5 p-4 rounded-l-xl rounded-b-xl rounded-es-xl dark:bg-gray-800'>
        <p className={`text-sm font-normal py-2.5 ${isError ? "text-warning" : "text-white"}`}>
          {content}
        </p>
      </div>
    </div>
  )
}


export default UserChat;
