import { FormEventHandler, useState } from 'react';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';
import Image from 'react-bootstrap/esm/Image';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const loginFormSubmitHandler: FormEventHandler = async (e) => {
    e.preventDefault();
    const loginCredentials = {
      username,
      password,
    };

    const response = await fetch(
      'http://localhost:8080/api/v1/accounts/login',
      {
        method: 'POST',
        body: JSON.stringify(loginCredentials),
        headers: {
          'Content-type': 'application/json',
        },
      }
    );
    const data = await response.json();
    console.log(data);
  };

  return (
    <Container>
      <Row className="vh-100">
        <Col />
        <Col
          xs="7"
          className="d-flex align-items-center justify-content-center"
        >
          <div>
            <Image fluid roundedCircle src="e-xercise-logo.png" />
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
        </Col>
        <Col />
      </Row>
    </Container>
  );
}

export default Login;
