import { FormEventHandler, useEffect, useState } from 'react';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';
import Image from 'react-bootstrap/esm/Image';
import { useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';
import AccountService, { LoginResponseDto } from '../api/accountService';
import RequestStatusMessage from '../components/UI/RequestStatusMessage';
import useHttp, { RequestConfig } from '../hooks/useHttp';
import { authActions } from '../store/authSlice';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const dispatch = useDispatch();

  const {
    sendRequest: login,
    data,
    error,
    status,
  } = useHttp<LoginResponseDto>(AccountService.login, false);

  // Checking useHttp states
  useEffect(() => {
    if (data && error === null && status === 'completed') {
      dispatch(authActions.saveAuth(data));
      console.log('Data received');
      console.log(data);
    }
  }, [data, error, status, dispatch]);

  const loginFormSubmitHandler: FormEventHandler = async (e) => {
    e.preventDefault();

    const loginCredentials = {
      username,
      password,
    };

    const requestConfig: RequestConfig = {
      body: loginCredentials,
      headers: {
        'Content-type': 'application/json',
      },
    };

    login(requestConfig);
  };

  return (
    <Container>
      <Row className="vh-100">
        <Col />
        <Col xs="7" className="h-100">
          <div className="h-100 d-flex flex-column">
            <div className="align-self-stretch flex-fill d-flex flex-column justify-content-center">
              <Image fluid roundedCircle src="e-xercise-logo.png" />
              <h2 className="text-center mb-3">Login</h2>
              <RequestStatusMessage
                data={data}
                error={error}
                loadingMessage="Logging in..."
                status={status}
                successMessage="Logged in Successfully!"
                key="LoginForm"
              />
              <Form onSubmit={loginFormSubmitHandler}>
                <Form.Group className="mb-3" controlId="loginFormUsername">
                  <Form.Label>Username</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="Enter username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="loginFormPassword">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </Form.Group>
                <div className="d-flex justify-content-end">
                  <Button variant="primary" type="submit">
                    Login
                  </Button>
                </div>
              </Form>
            </div>
            <div className="py-4">
              {"Don't have an account? Sign up "}
              <Link to="/register">here</Link>
            </div>
          </div>
        </Col>
        <Col />
      </Row>
    </Container>
  );
}

export default Login;
