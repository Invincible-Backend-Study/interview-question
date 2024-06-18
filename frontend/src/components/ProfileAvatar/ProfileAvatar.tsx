import {Avatar, Dropdown, DropdownItem, DropdownMenu, DropdownTrigger} from "@nextui-org/react";

interface ProfileAvatarProps{
  profile: {
    avatar: string;
    nickname:string;
    email:string;
  }
}

const ProfileAvatar = ({profile}: ProfileAvatarProps) => {
  return (
    <div className="flex items-center gap-4">
      <Dropdown placement="bottom-end">
        <DropdownTrigger>
          <Avatar
            isBordered
            size="sm"
            as="button"
            className="transition-transform"
            src={profile.avatar}
          />
        </DropdownTrigger>
        <DropdownMenu aria-label="Profile Actions" variant="flat">
          <DropdownItem key="profile" className="h-14 gap-2">
            <p className="font-semibold">닉네임: {profile.nickname}</p>
            <p className="font-semibold">{profile.email}</p>
          </DropdownItem>
          <DropdownItem key="settings">
            프로필
          </DropdownItem>

          <DropdownItem key="logout" color="danger">
            로그 아웃
          </DropdownItem>
        </DropdownMenu>
      </Dropdown>
    </div>
  )
}

export default ProfileAvatar;
