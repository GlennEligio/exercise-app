import React from 'react';
import styles from './RequestStatusMessage.module.css';

interface RequestStatusMessageProps {
  data: any;
  error: string | null;
  status: 'pending' | 'completed' | null;
  loadingMessage: string;
  successMessage: string;
}

function RequestStatusMessage({
  data,
  error,
  status,
  loadingMessage,
  successMessage,
}: RequestStatusMessageProps): JSX.Element {
  let MessageDisplay;
  let requestClassName = '';
  if (status === 'pending') {
    requestClassName = styles.pending;
    MessageDisplay = (
      <>
        <i className="bi bi-arrow-clockwise" />
        <span>{loadingMessage}</span>
      </>
    );
  } else if (status === 'completed') {
    if (error !== null || data === null) {
      requestClassName = styles.error;
      MessageDisplay = (
        <>
          <i className="bi bi-exclamation-circle-fill" />
          <span>{error}</span>
        </>
      );
    } else if (error === null || data !== null) {
      requestClassName = styles.success;
      MessageDisplay = (
        <>
          <i className="bi bi-check-circle-fill" />
          <span>{successMessage}</span>
        </>
      );
    }
  }
  return (
    <div className={`${styles.message} ${requestClassName}`}>
      {MessageDisplay}
    </div>
  );
}

export default RequestStatusMessage;
