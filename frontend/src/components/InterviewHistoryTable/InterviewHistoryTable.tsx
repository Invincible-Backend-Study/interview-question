import {
  Button,
  Chip,
  Dropdown, DropdownItem, DropdownMenu, DropdownTrigger,
  Table,
  TableBody,
  TableCell,
  TableColumn,
  TableHeader,
  TableRow
} from "@nextui-org/react";

import {columns, rows} from "./InterviewHistoryTableConstant";
import React, {Key, useCallback} from "react";
import {InterviewState, MyInterview} from "@/types/interview";
import InterviewHistoryPagination from "@/components/InterviewHistoryTable/InterviewHistoryPagination";
import InterviewHistoryTableTopContent from "@/components/InterviewHistoryTable/InterviewHistoryTableTopContent";
import {dateToString} from "@/utils/Date";
import { HiDotsVertical } from "react-icons/hi";


const statusColorMap: Record<InterviewState, "success" | "danger" | "warning"> = {
  COMPLETE: "success",
  STOP: "danger",
  INIT: "warning",
};
const statusDisplay: Record<InterviewState, string> = {
  COMPLETE: "완료",
  STOP: "포기",
  INIT: "미완료",
}



const InterviewHistoryTable = () => {

  const tableClassNames = React.useMemo(
    () => ({
      th: ["bg-transparent", "text-default-500", "border-b", "border-divider"],
      td: [
        // changing the rows border radius
        // first
        "group-data-[first=true]:first:before:rounded-none",
        "group-data-[first=true]:last:before:rounded-none",
        // middle
        "group-data-[middle=true]:before:rounded-none",
        // last
        "group-data-[last=true]:first:before:rounded-none",
        "group-data-[last=true]:last:before:rounded-none",
      ],
    }),
    [],
  );

  const topContent = React.useMemo(() => {
    return <InterviewHistoryTableTopContent/>
  }, [])

  const bottomContent = React.useMemo(() => {
    return <InterviewHistoryPagination/>
  },[])

  /**
   * cell renderer
   */
  const renderCell = useCallback((item: MyInterview, cellType: React.Key) => {
    const cellValue = item[cellType as keyof MyInterview];
    switch(cellType){
      case "createdAt":
        return <time>{dateToString(item.createdAt)}</time>
      case "updatedAt":
        return <time>{item.interviewState === "INIT" ? "" : dateToString(item.updatedAt)}</time>
      case "action":
        return (
          <Dropdown>
            <DropdownTrigger>
          <Button isIconOnly variant="right">
            <HiDotsVertical />
          </Button>
            </DropdownTrigger>
            <DropdownMenu>
              <DropdownItem>결과 보기</DropdownItem>
              <DropdownItem>재시도 하기</DropdownItem>
              <DropdownItem>이어서 풀기</DropdownItem>
            </DropdownMenu>
          </Dropdown>
        )
      case "interviewState":
        return <Chip className="capitalize border-none gap-1 text-default-600"
                     color={statusColorMap[item.interviewState]}
                     size="sm"
                     variant="dot"
        >
          {statusDisplay[item.interviewState]}
        </Chip>
      case "title":
        return <span className="truncate">{item.title}</span>
      default:
        // eslint-disable-next-line @typescript-eslint/ban-ts-comment
        // @ts-expect-error
        return <div>{cellValue}</div>;
    }

  }, [])


  return <>
    <Table
      isCompact
      removeWrapper
      classNames={tableClassNames}
      aria-label="Example table with custom cells, pagination and sorting"
      bottomContent={bottomContent}
      bottomContentPlacement="outside"
      topContent={topContent}
      topContentPlacement="outside"
      >
      <TableHeader columns={columns}>
        {(column) => (
          <TableColumn
            key={column.uid}
            align={column.uid === "actions" ? "center" : "start"}
            width={"10%"}
            allowsSorting={column.sortable}
          >
            {column.name}
          </TableColumn>
        )}
      </TableHeader>
      <TableBody items={rows} emptyContent={"참여한 면접 이력이 없습니다."}>
        {(row) => (
          <TableRow key={row.interviewId}>
            {(columnKey) => <TableCell>{renderCell(row, columnKey)}</TableCell>}
          </TableRow>
        )}
      </TableBody>
    </Table>
  </>
}

export default InterviewHistoryTable;
