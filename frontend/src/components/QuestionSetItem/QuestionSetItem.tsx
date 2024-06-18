import {Button, Card, CardBody, CardFooter, CardHeader, Divider, Image} from "@nextui-org/react";

const QuestionSetItem = () => {
  return (
    <Card className="max-w-[300px] ">
      <CardHeader className="flex flex-col items-start ">
        <span className="text-blue-500">NEW</span>
        <span>자바 면접 시리즈</span>
        <span>대충 난이도가 어떻고 이거 내가 제대로 알고 있는지 궁금하면 한번 풀어보세요</span>
      </CardHeader>
      <CardBody>
        <Image
          isBlurred
          width={300}
          height={200}
          src="https://velog.velcdn.com/images/pak4184/post/98ba8b4f-7b89-4d28-8376-0dc8d1be805a/image.png"/>
      </CardBody>
      <Divider/>
      <CardFooter className="flex flex-row-reverse">
        <Button color="primary" variant="light">시작하기</Button>
      </CardFooter>
    </Card>
  )
}


export default QuestionSetItem;
