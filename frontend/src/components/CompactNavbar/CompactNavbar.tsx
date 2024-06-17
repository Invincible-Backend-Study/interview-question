import {Button} from "@nextui-org/react";

const CompactNavbar = () => {
  return (
    <nav
      className="z-1 flex-col items-center w-16 py-4  min-h-screen " style={{
        borderRight: "1px solid rgb(54, 54, 54)"
    }}>
      <div className="flex flex-col items-center flex-1 p-2 space-y-4">
        <Button isIconOnly>
          <span className="sr-only">Toggle sidebar</span>
          <svg
            aria-hidden="true"
            className="w-6 h-6"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h7"/>
          </svg>
        </Button>


        <Button isIconOnly>
          <span className="sr-only">Toggle sidebar</span>
          <svg
            aria-hidden="true"
            className="w-6 h-6"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h7"/>
          </svg>
        </Button>

        <Button isIconOnly>
          <span className="sr-only">Toggle sidebar</span>
          <svg
            aria-hidden="true"
            className="w-6 h-6"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h7"/>
          </svg>
        </Button>
      </div>
    </nav>
  )
}

export default CompactNavbar;
