import Router from "@/router/Router";
import {ScrollShadow} from "@nextui-org/react";
import LogIn from "@/components/LogIn/LogIn";

function App() {
  return (
    <LogIn>
      <ScrollShadow className="h-screen">
        <Router/>
      </ScrollShadow>
    </LogIn>
  )
}

export default App
