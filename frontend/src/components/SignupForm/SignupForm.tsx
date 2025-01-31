import {Button, Card, CardBody, CardFooter, CardHeader, Input, Spacer} from "@nextui-org/react";
import {useEffect} from "react";
import {useOAuthProfileQuery} from "@/hooks/api/auth/useOAuthProfileQuery";
import {useSignupForm} from "@/components/SignupForm/useSignupForm";
import {useSignInMutation} from "@/hooks/api/auth/useSignInMutation";


interface SignupFormProps {
  providerId: string;
}

const SignupForm = ({providerId}: SignupFormProps) => {
  const {profile} = useOAuthProfileQuery({providerId});
  const {memberInfo, updateInputValue, handleSignup, isValid} = useSignupForm(profile);
  const signInMutation = useSignInMutation();

  useEffect(() => {
    if(profile.isRegistered) {
      signInMutation.mutate({
        providerId:profile.providerId
      })
    }
  }, []);


  return (
    !profile.isRegistered && (
      <Card className="p-4 bg-default-100 w-[40vw] min-w-[310px]" >
        <CardHeader className="flex justify-between ">
          <div className="flex">
            <span className="text-2xl text-success">회원가입</span>
            <Spacer x={2} />
          </div>
        </CardHeader>
        <CardBody>
          <p>
            무적 백엔드 스터디에서 면접 연습을 위해 사용하는 도구입니다.<br/>
            편하게 이용 가능하지만, 비용 문제로 사라질 수 있습니다~
          </p>
        </CardBody>
        <CardFooter className="gap-3">
          <form className="flex flex-row gap-3 w-full" onSubmit={(event) => {
            event.preventDefault();
            handleSignup();
          }}>
          <Input
          errorMessage="닉네임은 3~20글자 사이로 구성해야 합니다."
          isInvalid={isValid}
            color="primary"
                 placeholder="닉네임"
                 value={memberInfo.nickname}
                 radius="none"
                 onChange={(e) => updateInputValue('nickname', e.target.value)}/>
          <Button type={"submit"}   radius="none">확인</Button>
          </form>
        </CardFooter>
      </Card>
    )
  )
}

export default SignupForm;
