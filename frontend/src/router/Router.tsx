import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Root from "@/router/Root";
import LoginPage from "@/pages/LoginPage";
import MainPage from "@/pages/MainPage";
import InterviewPage from "@/pages/InterviewPage";
import SignupPage from "@/pages/SignupPage";
import ReportPage from "@/pages/ReportPage";
import InterviewHistoryPage from "@/pages/InterviewHistoryPage";

function Router() {

  const router = createBrowserRouter([
    {
      path:'/',
      element: <Root.Layout/>,
      children: [
        {index: true, element: <MainPage/>},
        {path: '/report', element: <ReportPage/>},
        {path: '/history', element: <InterviewHistoryPage/>}
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
      path: '/interview/:interviewId',
      element: <Root.InterviewLayout/>,
      children: [
        {index: true, element: <InterviewPage/>}
      ]
    }
  ])

  return <RouterProvider router={router}/>
}

export default Router;
