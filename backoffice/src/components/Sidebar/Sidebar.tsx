import { Listbox, ListboxItem, ListboxSection} from "@nextui-org/react";


const Sidebar = () => {
  return (
    <div className="w-full max-w-[260px] border-small px-1 py-2 rounded-small border-default-200 dark:border-default-100">
      <Listbox>
        <ListboxSection title="Actions" showDivider>
          <ListboxItem key="HOME" description="Home & Dashboard"/>
          <ListboxItem key="Interview" description="면접 생성"/>
        </ListboxSection>
      </Listbox>
    </div>
  )
}

export default Sidebar;
