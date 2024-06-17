import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Root from "@/router/Root";
import LoginPage from "@/pages/LoginPage";

function Router() {

  const router = createBrowserRouter([
    {
      path:'/',
      element: <Root.Layout/>,
      children: [
        {index: true, element: <LoginPage/>}
      ]
    },
    {
      path: '/login',
      element: <Root.LoginLayout/>,
      children: [
        {index: true, element: <LoginPage/>}
      ]
    }
  ])

  return <RouterProvider router={router}/>
}

export default Router;
