import {Checkbox, Chip, cn} from "@nextui-org/react";
import {useState} from "react";


const ReportItem = () => {

  const [isSelected, setIsSelected] = useState(false);

  const user = {
    name: "Junior Garcia",
    avatar: "https://avatars.githubusercontent.com/u/30373425?v=4",
    username: "jrgarciadev",
    url: "https://twitter.com/jrgarciadev",
    role: "Software Developer",
    status: "Active",
  }


  return (
    <Checkbox
      aria-label={user.name}
      classNames={{
        base: cn(
          "inline-flex w-full max-w-md bg-content1",
          "hover:bg-content2 items-center justify-start",
          "cursor-pointer rounded-lg gap-2 p-4 border-2 border-transparent",
          "data-[selected=true]:border-primary",
        ),
        label: "w-full",
      }}
      isSelected={isSelected}
      onValueChange={setIsSelected}
    >
      <div className="w-full flex justify-between gap-2">
        <span className="text-sm">java에서 equals와 Hashcode에 대해 설명해주세요</span>
        <div className="flex flex-col items-end gap-1">
          <Chip color="success" size="sm" variant="flat">
            COMPLETE
          </Chip>
        </div>
      </div>
    </Checkbox>
  );
}

export default ReportItem;
