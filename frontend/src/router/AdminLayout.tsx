import {ErrorBoundary} from "react-error-boundary";
import ErrorFallback from "@/components/ErrorFallback/ErrorFallback";
import {ScrollShadow} from "@nextui-org/react";
import {Outlet} from "react-router-dom";

const AdminLayout = () => {
  return (
    <ErrorBoundary fallbackRender={ErrorFallback}>
      <ScrollShadow className="h-full">
        <Outlet/>
      </ScrollShadow>
    </ErrorBoundary>
  )
}

export default AdminLayout;
