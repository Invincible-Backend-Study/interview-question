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
import {QuestionRow} from "@/types/admin/question";
import useQuestionSetTable from "@/components/QuestionSetTable/useQuestionSetTable";
import React, {useCallback} from "react";
import QuestionSetIdColumn from "@/components/QuestionSetTable/QuestionSetIdColumn";
import QuestionSetActionColumn from "@/components/QuestionSetTable/QuestionSetActionColumn";
import QuestionSetTextColumn from "@/components/QuestionSetTable/QuestionSetTextColumn";
import {columns} from "@/components/QuestionsTable/QuestionsTableConstant";


interface QuestionTableProps {
  rows: QuestionRow[],
  isLoading: boolean
}

const QuestionsTable = ({rows,  isLoading}: QuestionTableProps) => {
  const {tableClassNames} = useQuestionSetTable();

  const renderCell = useCallback((item: QuestionRow, cellType: React.Key) => {
    const cellValue = item[cellType as keyof QuestionRow];

    if(cellType === "questionSetId") {
      return <QuestionSetIdColumn id={item.questionSetId}/>
    }

    if(cellType === "action") {
      return <QuestionSetActionColumn/>
    }

    return <QuestionSetTextColumn text={cellValue}/>
  }, [])

  return (
    <ScrollShadow className="max-h-[55vh]">
      <Table
        aria-label="simple table"
        isCompact
        removeWrapper
        onSelectionChange={(key) => console.log(key)}
        color="success"
        selectionMode="single"
        classNames={tableClassNames}
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
          items={rows} emptyContent={"질문이 없습니다."}>
          {(row) => (
            <TableRow key={row.questionId}>
              {(columnKey) => <TableCell>{renderCell(row, columnKey)}</TableCell>}
            </TableRow>
          )}
        </TableBody>
      </Table>
    </ScrollShadow>
  )

}

export default QuestionsTable;
