import {useMutation} from "@tanstack/react-query";
import {postImage} from "@/api/image/PostImage";

export const useImageUploadMutation = () => {
  return useMutation({
    mutationKey: ['imageUpload'],
    mutationFn: postImage
  })
}
