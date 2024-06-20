


export const PATH = {
  MAIN_PAGE: "/",
  AUTH: "/auth" ,
  INTERVIEW: (id: number) => `/interviews/${id}`
} as const;
