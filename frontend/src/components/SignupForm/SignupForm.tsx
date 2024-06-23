
import {Button, Card, CardBody, CardFooter, CardHeader, Chip, Input, Spacer} from "@nextui-org/react";
import {useCallback, useEffect} from "react";
import {useOAuthProfileQuery} from "@/hooks/api/auth/useOAuthProfileQuery";
import {useSignupForm} from "@/components/SignupForm/useSignupForm";
import {useSignInMutation} from "@/hooks/api/auth/useSignInMutation";


interface SignupFormProps {
  providerId: string;
}

const SignupForm = ({providerId}: SignupFormProps) => {
  const {profile} = useOAuthProfileQuery({providerId});
  const {memberInfo, updateInputValue, handleSignup} = useSignupForm(profile);
  const signInMutation = useSignInMutation();

  const handleRedirectGithubRepository = useCallback(() => {
    location.assign("https://github.com/invincible-Backend-Study");
  },[])

  useEffect(() => {
    if(profile.isRegistered) {
      signInMutation.mutate({
        providerId:profile.providerId
      })
    }
  }, []);


  return (<Card className="p-4 bg-default-100 w-[40vw]">
      <CardHeader className="flex justify-between ">
        <div className="flex">
          <span className="text-2xl">회원가입</span>
          <Spacer x={2} />
          <Chip>MEMBER</Chip>
        </div>
        <Button color="primary" onClick={handleRedirectGithubRepository}>무적 백엔드 스터디 리포지터리 보러가기</Button>
      </CardHeader>
      <CardBody>
        <p>
          무적 백엔드 스터디에서 면접 연습을 위해 사용하는 도구입니다.<br/>
          편하게 이용 가능하지만, 비용 문제로 사라질 수 있습니다~
        </p>
      </CardBody>
      <CardFooter>
        <Input color="primary" placeholder="닉네임" value={memberInfo.nickname} onChange={(e) => updateInputValue('nickname', e.target.value)}/>
        <div>
          <Button onClick={handleSignup}>확인</Button>
        </div>
      </CardFooter>
    </Card>
  )
}

export default SignupForm;
