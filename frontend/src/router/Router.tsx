import {createBrowserRouter, RouterProvider} from "react-router-dom";
import LoginPage from "@/pages/LoginPage";
import MainPage from "@/pages/MainPage";
import InterviewPage from "@/pages/InterviewPage";
import SignupPage from "@/pages/SignupPage";
import ReportPage from "@/pages/ReportPage";
import InterviewHistoryPage from "@/pages/InterviewHistoryPage";
import {lazy, Suspense} from "react";
import InterviewResultPage from "@/pages/InterviewResultPage";


const DefaultLayout = lazy(() => import("@/router/DefaultLayout"));
const LoginLayout = lazy(() => import("@/router/LoginLayout"));
const InterviewLayout = lazy( () => import("@/router/InterviewLayout"));

const WaitingView = () => {
  return (
    <div className='flex items-center justify-center min-h-screen bg-gray-100'>
      <div className='text-center'>
        <div className='flex justify-center mb-4'>
          <div className='w-16 h-16 border-t-4 border-blue-500 border-solid rounded-full animate-spin'></div>
        </div>
        <h2 className='text-xl font-semibold text-gray-700'>Loading...</h2>
        <p className='text-gray-500'>Please wait while we fetch your data.</p>
      </div>
    </div>
  )
}

function Router() {

  const router = createBrowserRouter([
    {
      path: '/',
      element: (<Suspense fallback={<WaitingView/>}><DefaultLayout/></Suspense>),
      children: [
        {index: true, element: <MainPage/>},
        {path: '/report', element: <ReportPage/>},
        {path: '/history', element: <InterviewHistoryPage/>},
        {path: '/interviews/:interviewId/result', element: <InterviewResultPage/>}
      ]
    },
    {
      path: '/auth',
      element: <Suspense fallback={<WaitingView/>}><LoginLayout/></Suspense>,
      children:[
        {index: true, element: <LoginPage/>},
        {path: '/auth/signup', element:  <SignupPage/>}
      ]
    },
    {
      path: '/interviews/:interviewId',
      element: <Suspense fallback={<WaitingView/>}><InterviewLayout></InterviewLayout></Suspense>,
      children: [
        {index: true, element: <InterviewPage/>}
      ]
    }
  ])

  return <RouterProvider router={router}/>
}

export default Router;
