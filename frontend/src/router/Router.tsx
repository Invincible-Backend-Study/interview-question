import {createBrowserRouter, RouterProvider} from "react-router-dom";
import LoginPage from "@/pages/LoginPage";
import MainPage from "@/pages/MainPage";
import InterviewPage from "@/pages/InterviewPage";
import SignupPage from "@/pages/SignupPage";
import ReportPage from "@/pages/ReportPage";
import InterviewHistoryPage from "@/pages/InterviewHistoryPage";
import {lazy, Suspense} from "react";
import InterviewResultPage from "@/pages/InterviewResultPage";
import NotFoundPage from "@/pages/NotFoundPage";
import QuestionSetManagePage from "@/pages/QuestionSetManagePage";
import WaitingView from "@/components/WaitingView/WatingView";


const AdminLayout = lazy(() => import("@/router/AdminLayout"));
const DefaultLayout = lazy(() => import("@/router/DefaultLayout"));
const LoginLayout = lazy(() => import("@/router/LoginLayout"));
const InterviewLayout = lazy( () => import("@/router/InterviewLayout"));




function Router() {

  const router = createBrowserRouter([
    {
      path: '/',
      element: (<Suspense fallback={<WaitingView/>}><DefaultLayout/></Suspense>),
      errorElement: <NotFoundPage/>,
      children: [
        {index: true, element: <MainPage/>},
        {path: '/report', element: <ReportPage/>},
        {path: '/history', element: <InterviewHistoryPage/>},
        {path: '/interviews/:interviewId/result', element: <InterviewResultPage/>}
      ]
    },
    {
      path: '/auth',
      errorElement: <NotFoundPage/>,
      element: <Suspense fallback={<WaitingView/>}><LoginLayout/></Suspense>,
      children:[
        {index: true, element: <LoginPage/>},
        {path: '/auth/signup', element:  <SignupPage/>}
      ]
    },
    {
      path: '/interviews/:interviewId',
      errorElement: <NotFoundPage/>,
      element: <Suspense fallback={<WaitingView/>}><InterviewLayout></InterviewLayout></Suspense>,
      children: [
        {index: true, element: <InterviewPage/>}
      ]
    },
    {
      path: '/admin',
      element: <Suspense fallback={<WaitingView/>}><AdminLayout/></Suspense>,
      children: [
        {path: '/admin/manage/question-set', element: <QuestionSetManagePage/>},
      ]
    }
  ])

  return <RouterProvider router={router}  />
}

export default Router;
