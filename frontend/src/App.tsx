import Router from "@/router/Router";
import LogIn from "@/components/LogIn/LogIn";
import {Toaster} from "sonner";

function App() {
  return (
    <LogIn>
      <Toaster/>
      <Router/>
    </LogIn>
  )
}

export default App
