type Type = 'TailQuestion' | 'Answer' | 'MainQuestion'


export interface Question {
  type: Type
}

export interface TailQuestion extends Question {
  type: 'TailQuestion'
  score: number;
  feedback: string;
  tailQuestion: string;
}

export interface Answer extends Question {
  type: 'Answer'
  content: string;
}

export interface MainQuestion extends Question {
  type: 'MainQuestion'
  content: string;
}

export interface Interview {
  history: Question[]
}


export interface Chat {
  type: Type,
  content: string;
}


