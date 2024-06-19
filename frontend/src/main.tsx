import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import {NextUIProvider} from "@nextui-org/react";
import {QueryClientProvider} from "@tanstack/react-query";
import {queryClient} from "@/hooks/api/queryClient";
import {RecoilRoot} from "recoil";

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <NextUIProvider>
      <QueryClientProvider client={queryClient}>
        <RecoilRoot>
          <main className="dark text-foreground bg-background">
            <App/>
          </main>
        </RecoilRoot>
      </QueryClientProvider>
    </NextUIProvider>
  </React.StrictMode>,
)
