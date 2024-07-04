import {Button, Card, CardBody, CardFooter, CardHeader, Divider, Skeleton, Spacer} from "@nextui-org/react";

const QuestionSetItemListSkeleton = () => {
  return new Array(5).fill(0).map((_,index) => <Card key={index} className="max-w-[270px] ">
    <CardHeader className="flex flex-col items-start ">
      <span className="text-blue-500">NEW</span>
      <Skeleton className='w-3/5 rounded-lg'>
        <div className='h-4 w-3/5 rounded-lg bg-default-200'></div>
      </Skeleton>
      <Spacer y={2}/>
      <Skeleton className='w-full rounded-lg'>
        <div className='h-4 w-4/5 rounded-lg bg-default-200'></div>
      </Skeleton>
    </CardHeader>
    <CardBody className='p-3'>
      <Skeleton className="w-[240px] h-[200px] rounded-lg"></Skeleton>
    </CardBody>
    <Divider/>
    <CardFooter className="flex flex-row-reverse">
      <Skeleton className="rounded-lg">
        <Button />
      </Skeleton>
    </CardFooter>
  </Card>)
}

export default QuestionSetItemListSkeleton;
