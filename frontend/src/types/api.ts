export interface Pageable {
  page: number;
  size: number;
}

export interface PageResponse<T>{
  totalPages: number,
  totalElements: number,
  size: number,
  content: T;
  first: boolean;
  last: boolean;
  empty: boolean;
  number: number;
}
