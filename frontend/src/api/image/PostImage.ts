import {axiosInstance} from "@/api/AxiosInstance";
import {END_POINT} from "@/constants/api";

interface PostImageBody {
  thumbnail: FormData;
}

interface ImageUrl {
  imageUrl: string;
}

export const postImage = async (body: PostImageBody) => {
  const { data } = await axiosInstance.post<ImageUrl>(END_POINT.admin.IMAGE, body.thumbnail);
  return data.imageUrl;
}
