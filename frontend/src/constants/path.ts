


export const PATH = {
  MAIN_PAGE: "/",
  AUTH: "/auth" ,
  INTERVIEW: (id: number) => `/interviews/${id}`,
  MY_INTERVIEW: '/history'
} as const;
