import { ApiError, RequestConfig } from '../hooks/useHttp';

export enum AccountType {
  USER = 'USER',
  ADMIN = 'ADMIN',
}

export interface LoginRequestDto {
  username: string;
  password: string;
}

export interface LoginResponseDto {
  id: string;
  username: string;
  name: string;
  accountType: string;
  accessToken: string;
  profile: string;
}

export interface Account {
  id: string;
  name: string;
  username: string;
  email: string;
  profileUrl: string;
  description: string;
}

const getBackendUri = () => {
  if (import.meta.env.DEV && import.meta.env.VITE_BACKEND_SERVICE_URI_DEV) {
    return import.meta.env.VITE_BACKEND_SERVICE_URI_DEV;
  }
  return '';
};

const BACKEND_URI = getBackendUri();

const login = async (requestConfig: RequestConfig) => {
  const responseObj: LoginResponseDto = await fetch(
    `${BACKEND_URI}/api/v1/accounts/login`,
    {
      method: requestConfig.method || 'POST',
      body:
        requestConfig.body != null ? JSON.stringify(requestConfig.body) : null,
      headers: requestConfig.headers != null ? requestConfig.headers : {},
    }
  ).then((response) => {
    if (response.ok) {
      return response.json();
    }
    throw new ApiError('Invalid username/password');
  });
  return responseObj;
};

export default {
  login,
};
