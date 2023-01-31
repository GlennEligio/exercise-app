import { useReducer, useCallback } from 'react';

enum RequestActionKind {
  SEND = 'SEND',
  SUCCESS = 'SUCCESS',
  ERROR = 'ERROR',
  RESET = 'RESET',
}

export class ApiError extends Error {
  message: string;

  constructor(m: string) {
    super();
    this.message = m;
  }
}

interface RequestState<T> {
  data: T | null;
  error: string | null;
  status: 'pending' | 'completed' | null;
}

interface RequestAction {
  type: string;
  responseData?: any;
  errorMessage?: any;
}

export interface RequestConfig {
  body?: { [props: string]: any };
  headers?: {
    [header: string]: string;
  };
  method?: string;
  relativeUrl?: string;
}

const createDataFetchReducer =
  <T>() =>
  (state: RequestState<T>, action: RequestAction): RequestState<T> => {
    switch (action.type) {
      case RequestActionKind.RESET:
        return {
          status: null,
          data: null,
          error: null,
        };
      case RequestActionKind.SEND:
        return {
          ...state,
          status: 'pending',
          error: null,
          data: null,
        } as RequestState<T>;
      case RequestActionKind.SUCCESS: {
        return {
          ...state,
          error: null,
          status: 'completed',
          data: action.responseData,
        } as RequestState<T>;
      }
      case RequestActionKind.ERROR:
        return {
          ...state,
          error: action.errorMessage,
          status: 'completed',
          data: null,
        } as RequestState<T>;
      default:
        throw new Error('Action not supported');
    }
  };

// eslint-disable-next-line @typescript-eslint/ban-types
function useHttp<T>(requestFunction: Function, startWithPending = false) {
  const [httpState, dispatch] = useReducer(createDataFetchReducer<T>(), {
    status: startWithPending ? 'pending' : null,
    data: null,
    error: null,
  });

  const determineErrorMessage = (error: any) => {
    if (error instanceof ApiError) {
      return error.message;
    }
    return 'Something went wrong';
  };

  const resetHttpState = () => {
    dispatch({ type: RequestActionKind.RESET });
  };

  const sendRequest = useCallback(
    async function (requestConfig?: RequestConfig) {
      dispatch({ type: RequestActionKind.SEND });
      try {
        const responseData = await requestFunction(requestConfig);
        dispatch({ type: RequestActionKind.SUCCESS, responseData });
      } catch (error) {
        dispatch({
          type: RequestActionKind.ERROR,
          errorMessage: determineErrorMessage(error),
        });
      }
    },
    [requestFunction]
  );

  return {
    sendRequest,
    resetHttpState,
    ...httpState,
  };
}

export default useHttp;
