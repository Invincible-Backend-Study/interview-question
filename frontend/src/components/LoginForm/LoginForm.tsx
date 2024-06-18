
import {Button, Card, CardBody, CardFooter, CardHeader, Chip, Spacer} from "@nextui-org/react";
import {useCallback} from "react";
import {AUTH_TEXT} from "@/constants/auth";

const LoginForm = () => {

  const handleRedirectGithubPage = useCallback(() => {
    location.href = "https://github.com/invincible-Backend-Study"
  }, []);


  return (
    <Card className="p-4 bg-default-100 w-[40vw]">
      <CardHeader className="flex justify-between">
        <div className="flex items-center">
          <span className="text-2xl">{AUTH_TEXT.LOGIN}</span>
          <Spacer x={2} />
          <Chip>{AUTH_TEXT.AUTH_TYPE}</Chip>
        </div>
        <Button color="primary" onClick={handleRedirectGithubPage}>{AUTH_TEXT.REDIRECT_GITHUB}</Button>
      </CardHeader>
      <CardBody>
        <p className="whitespace-pre-wrap">{AUTH_TEXT.DESCRIPTION}</p>
      </CardBody>
      <CardFooter>
        <Button color="primary">GITHUB 로그인</Button>
      </CardFooter>
    </Card>
  )
}


export default LoginForm;
