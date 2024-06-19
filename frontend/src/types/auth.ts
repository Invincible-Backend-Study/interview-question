
export interface AccessTokenResponse{
  /**
   * access token
   * */
  accessToken: string;
}

export interface SignupRequest{
  /**
   * social login id
   */
  providerId: string;
  avatarUrl: string;
  nickname: string;
}

export interface SigninRequest{
  providerId: string;
}

export interface OAuthProfileRequest{
  providerId: string;
  redirectUrl?: string;
}

export interface OAuthProfileResponse {
  providerId: string;
  avatarUrl: string;
  /**
   * 계정 등록 여부
   */
  isRegistered: boolean;
}
