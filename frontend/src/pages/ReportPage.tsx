import {Checkbox, Divider, ScrollShadow, Snippet } from "@nextui-org/react";
import ReportItem from "@/components/ReportItem/ReportItem";

const ReportPage = () => {
  return (
    <div className="p-3 w-full grid grid-flow-col gap-1">
      <div className="border-1 col-span-1 p-2">
        <span className="text-2xl">문제 목록</span>
        <br/>
        <Snippet size="sm">내가 푼 답안 공유하기</Snippet>
        <div className="flex gap-4">
          <Checkbox defaultSelected color="default">푼 문제</Checkbox>
          <Checkbox defaultSelected color="primary">통과 시킨 문제</Checkbox>
        </div>
        <ScrollShadow className="h-[80vh] p-3 flex flex-col gap-5">
          <ReportItem/>
          <ReportItem/>
          <ReportItem/>
          <ReportItem/>
          <ReportItem/>
          <ReportItem/>
          <ReportItem/>
          <ReportItem/>
          <ReportItem/>
          <ReportItem/>
          <ReportItem/>
          <ReportItem/>
        </ScrollShadow>
      </div>
      <div className="border-1 col-span-3 p-3 flex flex-col">
        <span>질문: 자바의 equals와 hashcode에 대해 설명해주세요</span>
        <span>내답변: ~</span>
        <span>피드백: ~</span>

        <span>꼬리질문1</span>
        <span>내 답변</span>
        <span>피드백: ~</span>

        <span>꼬리질문2</span>
        <span>내 답변</span>
        <span>피드백: ~</span>


      </div>
      <div className="border-1 col-span-3 flex flex-col gap-5 p-3">
        <div className="flex flex-col gap-2">
          <span>학습용 참고 링크</span>
          <Snippet color="primary" size="sm">https://github.com/</Snippet>
        </div>
        <Divider/>
        <div>
          <span>다른 사람이 제출한 답변</span>
        </div>
      </div>
    </div>
  )
}

export default ReportPage;
