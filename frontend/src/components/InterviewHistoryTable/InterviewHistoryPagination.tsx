import {Pagination} from "@nextui-org/react";


interface InterviewHistoryPaginationProps {
  totalPages: number;
  page: number;
  changePage?: (page: number) => void;
}

const InterviewHistoryPagination = ({totalPages, page, changePage}: InterviewHistoryPaginationProps) => {

  return (
    <div className="py-2 px-2 flex justify-between items-center">
      <Pagination
        showControls
        classNames={{
          cursor: "bg-foreground text-background",
        }}
        color="default"
        page={page}
        total={totalPages}
        variant="light"

        onChange={changePage}
      />
    </div>
  );
}

export default InterviewHistoryPagination;
