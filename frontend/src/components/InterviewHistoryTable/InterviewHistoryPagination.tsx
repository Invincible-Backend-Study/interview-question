import {Pagination} from "@nextui-org/react";


const InterviewHistoryPagination = () => {

  return (
    <div className="py-2 px-2 flex justify-between items-center">
      <Pagination
        showControls
        classNames={{
          cursor: "bg-foreground text-background",
        }}
        color="default"
        page={0}
        total={10}
        variant="light"
      />
    </div>
  );
}

export default InterviewHistoryPagination;
