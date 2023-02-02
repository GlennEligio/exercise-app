import React from 'react';
import { Button, Col, Container, Row } from 'react-bootstrap';
import Image from 'react-bootstrap/esm/Image';
import { useNavigate } from 'react-router-dom';

function Home() {
  const navigate = useNavigate();

  return (
    <Container>
      <Row>
        <Col />
        <Col
          xs="7"
          className="vh-100 d-flex flex-column justify-content-center"
        >
          <Image
            fluid
            roundedCircle
            src="e-xercise-logo.png"
            className="mb-5"
          />
          <Button
            variant="primary"
            size="lg"
            className="mb-3"
            onClick={() => navigate('/login')}
          >
            Login
          </Button>
          <Button
            variant="primary"
            size="lg"
            onClick={() => navigate('/register')}
          >
            Register
          </Button>
        </Col>
        <Col />
      </Row>
    </Container>
  );
}

export default Home;
