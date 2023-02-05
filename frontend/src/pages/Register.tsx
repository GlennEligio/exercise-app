import { FormEventHandler, useEffect, useState } from 'react';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';
import Image from 'react-bootstrap/esm/Image';
import { useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';
import AccountService, { LoginResponseDto } from '../api/accountService';
import RequestStatusMessage from '../components/UI/RequestStatusMessage';
import useHttp, { RequestConfig } from '../hooks/useHttp';
import { authActions } from '../store/authSlice';

function Register() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const dispatch = useDispatch();

  const {
    sendRequest: register,
    data,
    error,
    status,
  } = useHttp<LoginResponseDto>(AccountService.register, false);

  // Checking useHttp states
  useEffect(() => {
    if (data && error === null && status === 'completed') {
      dispatch(authActions.saveAuth(data));
      console.log('Data received');
      console.log(data);
    }
  }, [data, error, status, dispatch]);

  const registerFormSubmitHandler: FormEventHandler = async (e) => {
    e.preventDefault();

    const registerFormData = {
      username,
      password,
      name,
      email,
    };

    const requestConfig: RequestConfig = {
      body: registerFormData,
      headers: {
        'Content-type': 'application/json',
      },
    };

    register(requestConfig);
  };

  return (
    <Container>
      <Row className="vh-100">
        <Col />
        <Col xs="7" className="h-100">
          <div className="h-100 d-flex flex-column">
            <div className="align-self-stretch flex-fill d-flex flex-column justify-content-center">
              <Image fluid roundedCircle src="e-xercise-logo.png" />
              <h2 className="text-center mb-3">Register</h2>
              <RequestStatusMessage
                data={data}
                error={error}
                loadingMessage="Registering..."
                status={status}
                successMessage="Registered Successfully!"
                key="RegisterForm"
              />
              <Form onSubmit={registerFormSubmitHandler}>
                <Form.Group className="mb-3" controlId="registerFormUsername">
                  <Form.Label>Username</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="Enter username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="registerFormPassword">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    placeholder="Enter password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="registerFormName">
                  <Form.Label>Name</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="John Doe"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="registerFormEmail">
                  <Form.Label>Email</Form.Label>
                  <Form.Control
                    type="email"
                    placeholder="sample@email.com"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </Form.Group>
                <div className="d-flex justify-content-end">
                  <Button variant="primary" type="submit">
                    Register
                  </Button>
                </div>
              </Form>
            </div>
            <div className="py-4">
              {'Already have an account? Sign in '}
              <Link to="/login">here</Link>
            </div>
          </div>
        </Col>
        <Col />
      </Row>
    </Container>
  );
}

export default Register;
