import Router from "@/router/Router";
import LogIn from "@/components/LogIn/LogIn";
import {Toaster} from "sonner";
import {ErrorBoundary} from "react-error-boundary";
import Agent from "@/components/Agent/Agent";

function App() {
  return (
    <ErrorBoundary fallback={<div>1234</div>}>
      <Agent>
        <LogIn>
          <Toaster richColors closeButton />
          <Router/>
        </LogIn>
      </Agent>
    </ErrorBoundary>
  )
}

export default App
