import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Root from "@/router/Root";
import LoginPage from "@/pages/LoginPage";
import MainPage from "@/pages/MainPage";
import InterviewPage from "@/pages/InterviewPage";
import SignupPage from "@/pages/SignupPage";
import ReportPage from "@/pages/ReportPage";
import InterviewHistoryPage from "@/pages/InterviewHistoryPage";
import {lazy, Suspense} from "react";
import InterviewResultPage from "@/pages/InterviewResultPage";


const DefaultLayout = lazy(() => import("@/router/DefaultLayout"))

function Router() {

  const router = createBrowserRouter([
    {
      path:'/',
      element: (<Suspense fallback={"wait"}><DefaultLayout/></Suspense>),
      children: [
        {index: true, element: <MainPage/>},
        {path: '/report', element: <ReportPage/>},
        {path: '/history', element: <InterviewHistoryPage/>},
        {path: '/interview/result/:interviewId', element: <InterviewResultPage/>}
      ]
    },
    {
      path: '/auth',
      element: <Root.LoginLayout/>,
      children: [
        {index: true, element: <LoginPage/>},
        {path: '/auth/signup', element:  <SignupPage/>}
      ]
    },
    {
      path: '/interviews/:interviewId',
      element: <Root.InterviewLayout/>,
      children: [
        {index: true, element: <InterviewPage/>}
      ]
    }
  ])

  return <RouterProvider router={router}/>
}

export default Router;
