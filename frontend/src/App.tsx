import Router from "@/router/Router";
import LogIn from "@/components/LogIn/LogIn";
import {Toaster} from "sonner";
import {ErrorBoundary} from "react-error-boundary";

function App() {
  return (
    <ErrorBoundary fallback={<div>1234</div>}>
      <LogIn>
        <Toaster richColors closeButton />
        <Router/>
      </LogIn>
    </ErrorBoundary>
  )
}

export default App
