import {Pagination} from "@nextui-org/react";


interface TablePaginationProps{
  totalPages?: number;
  page: number;
  changePage?: (page: number) => void;
}

const TablePagination = ({totalPages, page, changePage}: TablePaginationProps) => {

  return (
    <div className="py-2 px-2 flex justify-between items-center">
      <Pagination
        showControls
        classNames={{
          cursor: "bg-foreground text-background",
        }}
        color="default"
        page={page}
        total={totalPages ??  1 }
        variant="light"

        onChange={changePage}
      />
    </div>
  );
}

export default TablePagination;
