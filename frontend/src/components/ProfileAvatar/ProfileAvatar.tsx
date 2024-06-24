import {Avatar} from "@nextui-org/react";

interface ProfileAvatarProps{
  profile: {
    avatarUrl: string;
    nickname:string;
  }
}

const ProfileAvatar = ({profile}: ProfileAvatarProps) => {
  return (
    <Avatar
      isBordered
      size="sm"
      as="button"
      className="transition-transform"
      src={profile.avatarUrl}
    />
  )
}

export default ProfileAvatar;
