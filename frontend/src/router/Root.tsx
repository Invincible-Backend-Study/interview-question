import {Outlet} from "react-router-dom";


function Layout(){

  return (
    <>
      <Outlet/>
    </>
  )
}


function LoginLayout() {
  return  (
    <>
      <div className="flex items-center justify-center min-h-screen">
        <Outlet/>
      </div>
    </>
  )
}

export default {
  LoginLayout,
  Layout
};
