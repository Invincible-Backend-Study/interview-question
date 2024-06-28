import {Button, Dropdown, DropdownItem, DropdownMenu, DropdownTrigger, Input} from "@nextui-org/react";
import {BiCaretDown, BiSearch} from "react-icons/bi";


const statusOptions = ["COMPLETE", "INIT", "STOP"]

const InterviewHistoryTableTopContent = () => {
  return (
    <div className="flex flex-col gap-4">
      <div className="flex gap-3 items-end">
        <Input
          disabled
          isClearable
          classNames={{
            base: "w-full sm:max-w-[44%]",
            inputWrapper: "border-1",
          }}
          placeholder="Search by name..."
          size="sm"
          startContent={<BiSearch className="text-default-300" />}
          variant="bordered"
        />
        <div className="flex gap-3">
          <Dropdown>
            <DropdownTrigger disabled className="hidden sm:flex">
              <Button
                disabled
                endContent={<BiCaretDown className="text-small" />}
                size="sm"
                variant="flat"
              >
                Status
              </Button>
            </DropdownTrigger>
            <DropdownMenu
              disallowEmptySelection
              aria-label="Table Columns"
              closeOnSelect={false}
              selectionMode="multiple"
            >
              {statusOptions.map((status) => (
                <DropdownItem key={status} className="capitalize">
                  {status}
                </DropdownItem>
              ))}
            </DropdownMenu>
          </Dropdown>
        </div>
      </div>
    </div>
  );
}
export default InterviewHistoryTableTopContent;
