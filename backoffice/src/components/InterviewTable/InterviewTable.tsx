import {Table, TableBody, TableCell, TableColumn, TableHeader, TableRow} from "@nextui-org/react";
import {INTERVIEW} from "@/components/InterviewTable/Interview.constant";
import {Key, useCallback} from "react";
import {Interview} from "@/types/Interview";



const InterviewTable = () => {
  const renderCell = useCallback((interview: Interview, columnKey: Key) => {
    const cellValue = interview[columnKey as keyof Interview];

    switch (columnKey) {
      default:
        return cellValue;
    }
  }, []);

  return (<Table
    aria-label="Example table with custom cells, pagination and sorting"
    isHeaderSticky
    bottomContentPlacement="outside"
    topContentPlacement="outside"
  >
    <TableHeader columns={INTERVIEW.HEADERS}>
      {(column) => (
        <TableColumn
          key={column.uid}
          align={column.uid === "actions" ? "center" : "start"}
          allowsSorting={column.sortable}
        >
          {column.name}
        </TableColumn>
      )}
    </TableHeader>
    <TableBody emptyContent={"No users found"} items={[]}>
      {(item: Interview)=> (
        <TableRow key={item.id}>
          {(columnKey) => <TableCell>{renderCell(item, columnKey)}</TableCell>}
        </TableRow>
      )}
    </TableBody>
  </Table>
)
}

export default InterviewTable;
