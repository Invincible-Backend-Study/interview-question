import {
  Button,
  Chip,
  Dropdown,
  DropdownItem,
  DropdownMenu,
  DropdownTrigger,
  Table,
  TableBody,
  TableCell,
  TableColumn,
  TableHeader,
  TableRow
} from "@nextui-org/react";

import {columns} from "./InterviewHistoryTableConstant";
import React, {useCallback, useEffect, useMemo, useState} from "react";
import {InterviewState, MyInterview} from "@/types/interview";
import InterviewHistoryPagination from "@/components/InterviewHistoryTable/InterviewHistoryPagination";
import InterviewHistoryTableTopContent from "@/components/InterviewHistoryTable/InterviewHistoryTableTopContent";
import {dateToString} from "@/utils/Date";
import {HiDotsVertical} from "react-icons/hi";
import {useMyInterviewQuery} from "@/hooks/api/interview/useMyInterviewQuery";
import {useNavigate} from "react-router-dom";
import {PATH} from "@/constants/path";


const statusColorMap: Record<InterviewState, "success" | "danger" | "warning"> = {
  DONE: "success",
  PROGRESS: "danger",
};
const statusDisplay: Record<InterviewState, string> = {
  DONE: "완료",
  PROGRESS: "미완료",
}



const InterviewHistoryTable = () => {


  const [page, setPage] = useState(1);
  const {data, totalPages, refetch} = useMyInterviewQuery(page);
  const navigate = useNavigate();


  useEffect(() => {
    refetch()
  }, [page])

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

  const bottomContent = useMemo(() => {
    return <InterviewHistoryPagination page={page} totalPages={totalPages} changePage={(page) => setPage(page)}/>
  },[page, totalPages])

  /**
   * cell renderer
   */
  const renderCell = useCallback((item: MyInterview, cellType: React.Key) => {
    const cellValue = item[cellType as keyof MyInterview];
    console.log(cellType);
    switch(cellType){
      case "createdAt":
        return <time>{dateToString(new Date(item.createdAt))}</time>
      case "updatedAt":
        return <time>{item.interviewState === "PROGRESS" ? "" : dateToString(new Date(item.updatedAt))}</time>
      case "action":
        return (
          <Dropdown>
            <DropdownTrigger>
          <Button isIconOnly variant="light">
            <HiDotsVertical />
          </Button>
            </DropdownTrigger>
            {item.interviewState === "DONE" && <DropdownMenu><DropdownItem onClick={() => navigate(PATH.INTERVIEW_RESULT(item.interviewId))}>결과 보기</DropdownItem></DropdownMenu>}
            {item.interviewState === "PROGRESS" && <DropdownMenu><DropdownItem onClick={() => navigate(PATH.INTERVIEW(item.interviewId))}>이어서 풀기</DropdownItem></DropdownMenu>}
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
      <TableBody items={data === undefined ? [] : data} emptyContent={"참여한 면접 이력이 없습니다."}>
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
