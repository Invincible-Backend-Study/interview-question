import InterviewHistoryTable from "@/components/InterviewHistoryTable/InterviewHistoryTable";
import {Spacer} from "@nextui-org/react";

const InterviewHistoryPage= () => {
  return <div className="p-3 w-full">
    <span className="text-2xl">
      면접 이력
    </span>

    <Spacer y={5}/>

    <div className="">
      <InterviewHistoryTable />
    </div>
  </div>
}

export default InterviewHistoryPage;
