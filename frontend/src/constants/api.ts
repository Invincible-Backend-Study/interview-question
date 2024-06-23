export const NETWORK = {
  RETRY_COUNT: 2,
  TIMEOUT: 10000
}as const;



const wrap = (url: string) => {
  return (params: object) => url + '?' + Object.entries(params)
    .map(arr => `${arr[0]}=${arr[1]}`)
    .join("&")
}

export const END_POINT = {
  ME: '/members/me',

  TAIL_QUESTION_SUBMIT: "/tail-questions/submit",

  INTERVIEWS: "/interviews",
  INTERVIEWS_GET_RESULT: (interviewId: number) => `/interviews/${interviewId}`,
  INTERVIEWS_SUBMIT: "/interviews/submit",
  INTERVIEWS_GET_PROBLEM: (interviewId: number) => `/interviews/${interviewId}/current/problem`,
  INTERVIEWS_ME: wrap( "/interviews/me"),

  SIGNUP: "/auth/signup",
  SIGNIN: "/auth/signin",
  PROFILE: wrap( "/auth/profile"),
  LOGOUT: "/auth/logout",
  TOKEN_REISSUE: "/auth/token/reissue",

  QUESTION_SETS: wrap( "/question-set")

} as const;


export const TOKEN = {
  ACCESS: "accessToken",
  bearer: (accessToken: string) => `Bearer ${accessToken}`,
}
