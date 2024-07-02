/**
 *
 * interviewId: number;
 *   title: string;
 *   interviewState: "INIT" | "STOP" | "COMPLETE";
 *   questionCount: number;
 *   createdAt: Date;
 *   updatedAt: Date;
 */
import {MyInterview} from "@/types/interview";


export const columns = [
  {name: "인터뷰 ID", uid: "interviewId", sortable: true},
  {name: "인터뷰 제목", uid: "title"},
  {name: "인터뷰 상태", uid: "interviewState"},
  {name: "문항 수", uid: "questionCount"},
  {name: "면접 생성 일", uid: "createdAt"},
  {name: "면접 종료 일", uid: "updatedAt"},
  {name: "액션", uid:"action" }
];

const item = new Array(13).fill( 0)
  .map((_, i)=> ({
    interviewId: i + 3, interviewState: "PROGRESS", questionCount: 10,
    createdAt: new Date().toString(),
    updatedAt: new Date().toString(),
    title: "자바 면접 시리즈3"
  }) as MyInterview)
export const rows: MyInterview[] = [
  ...item
]


