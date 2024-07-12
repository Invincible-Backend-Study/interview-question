import {useCallback, useState} from "react";
import {useSignupMutation} from "@/hooks/api/auth/useSignupMutation";

interface MemberInfo {
  nickname: string;
}

interface DefaultProfile {
  providerId: string;
  avatarUrl: string;
}

export const useSignupForm = (profile: DefaultProfile) => {
  const [memberInfo, setMemberInfo] = useState<MemberInfo>({
    nickname: "",
  });
  const [isValid, setValid] = useState(false);
  const mutation = useSignupMutation();


  const updateInputValue = useCallback( <K extends keyof MemberInfo>(key: K, value: MemberInfo[K]) => {
    setMemberInfo((prev) => ({
      ...prev,
      [key]: value
    }))
  }, []);

  const handleSignup = useCallback(() => {
    if(memberInfo.nickname.length < 3 || memberInfo.nickname.length > 20){
      setValid(true);
      return;
    }
    setValid(false);
    mutation.mutate({
      ...memberInfo,
      ...profile
    })
  }, [memberInfo])

  return {
    memberInfo, updateInputValue, handleSignup, isValid
  }
}
