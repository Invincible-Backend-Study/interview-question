import {Button, Card, CardBody, CardFooter, CardHeader, Spacer} from "@nextui-org/react";
import {useCallback} from "react";
import {AUTH_TEXT} from "@/constants/auth";

const LoginForm = () => {

  const handleOAuthLoginPage = useCallback(() => {
    location.assign(`https://github.com/login/oauth/authorize?client_id=${import.meta.env.VITE_CLIENT_ID}`);
  }, [])


  return (
    <Card className="p-4 bg-default-100 w-[40vw] min-w-[300px]">
      <CardHeader className="flex justify-between">
        <div className="flex items-center">
          <span className="text-2xl text-success">{AUTH_TEXT.LOGIN}</span>
          <Spacer x={2} />
        </div>
      </CardHeader>
      <CardBody className="w-full">
        <p className="whitespace-pre-wrap">{AUTH_TEXT.DESCRIPTION}</p>
      </CardBody>
      <CardFooter className="w-full flex flex-row-reverse">
        <Button color="primary" onClick={handleOAuthLoginPage}>GITHUB 로그인</Button>
      </CardFooter>
    </Card>
  )
}


export default LoginForm;
