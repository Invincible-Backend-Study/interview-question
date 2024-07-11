import Router from "@/router/Router";
import LogIn from "@/components/LogIn/LogIn";
import {Toaster} from "sonner";
import {useMediaQuery} from "@/hooks/useMediaQuery";
// import Agent from "@/components/Agent/Agent";

function App() {
  const {isMobile} = useMediaQuery();
  return (
      // <Agent>
      //   <LogIn>
      //     <Toaster richColors closeButton />
      //     <Router/>
      //   </LogIn>
      // </Agent>

      <LogIn>
        <Toaster richColors closeButton position={isMobile ? "top-right" : "bottom-right"}/>
        <Router/>
      </LogIn>
  )
}

export default App
