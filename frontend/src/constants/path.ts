


export const PATH = {
  MAIN_PAGE: "/",
  AUTH: "/auth" ,
  INTERVIEW: (id: number) => `/interviews/${id}`,
  MY_INTERVIEW: '/history',
  INTERVIEW_RESULT:(id:number) => `/interviews/${id}/result`
} as const;
