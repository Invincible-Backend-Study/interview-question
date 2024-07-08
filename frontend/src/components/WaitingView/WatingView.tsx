

interface WaitingViewProps {
  message?: string;
}
const WaitingView = ({message="페이지를 가져오는 중이에요"}: WaitingViewProps) => {
  return (
    <div className='flex items-center justify-center min-h-screen '>
      <div className='text-center'>
        <div className='flex justify-center mb-4'>
          <div className='w-16 h-16 border-t-4 border-blue-500 border-solid rounded-full animate-spin'></div>
        </div>
        <h2 className='text-xl font-semibold text-gray-700'>Loading...</h2>
        <p className='text-gray-500'>{message}</p>
      </div>
    </div>
  )
}
export default WaitingView;
