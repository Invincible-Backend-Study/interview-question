import {
  ScrollShadow,
  Spinner,
  Table,
  TableBody,
  TableCell,
  TableColumn,
  TableHeader,
  TableRow
} from "@nextui-org/react";
import {columns} from "@/components/QuestionSetTable/QuestionSetTableConstant";
import {QuestionSetRow} from "@/types/admin/questionSet";
import useQuestionSetTable from "@/components/QuestionSetTable/useQuestionSetTable";
import React, {useCallback, useMemo} from "react";
import QuestionSetIdColumn from "@/components/QuestionSetTable/QuestionSetIdColumn";
import QuestionSetActionColumn from "@/components/QuestionSetTable/QuestionSetActionColumn";
import QuestionSetTextColumn from "@/components/QuestionSetTable/QuestionSetTextColumn";
import InterviewHistoryPagination from "@/components/InterviewHistoryTable/InterviewHistoryPagination";


interface QuestionSetTableProps {
  rows: QuestionSetRow[],
  page: number;
  totalPages?: number;
  changePage?: (e: number) => void;
  isLoading: boolean,
  selectionChange: (e: number) => void;
}


const QuestionSetTable = ({rows, page, totalPages, isLoading, changePage, selectionChange}: QuestionSetTableProps) => {
  const {tableClassNames} = useQuestionSetTable();

  const bottomContent = useMemo(() => {
    return <InterviewHistoryPagination page={page} totalPages={totalPages} changePage={changePage}/>
  },[page, totalPages])

  const renderCell = useCallback((item: QuestionSetRow, cellType: React.Key) => {
    const cellValue = item[cellType as keyof QuestionSetRow];

    if(cellType === "questionSetId") {
      return <QuestionSetIdColumn id={item.questionSetId}/>
    }

    if(cellType === "action") {
      return <QuestionSetActionColumn/>
    }

    return <QuestionSetTextColumn text={cellValue}/>
  }, [])

  return(
    <ScrollShadow className="max-h-full">
      <Table
        aria-label="simple table"
        isCompact
        removeWrapper
        onSelectionChange={(selection) => {
          if([...selection].length === 0) {
            selectionChange(-1);
          }
          for(const k of selection) {
            selectionChange(Number(k));
          }
        }}
        color="success"
        selectionMode="single"
        classNames={tableClassNames}

        bottomContent={bottomContent}
        bottomContentPlacement="outside"
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
        <TableBody
          loadingContent={<Spinner label="Loading..." />}
          isLoading={isLoading}
          items={rows} emptyContent={"질문 이력이 없습니다."}>
          {(row) => (
            <TableRow key={row.questionSetId}>
              {(columnKey) => <TableCell>{renderCell(row, columnKey)}</TableCell>}
            </TableRow>
          )}
        </TableBody>
      </Table>
    </ScrollShadow>
  )
}

export default QuestionSetTable;
