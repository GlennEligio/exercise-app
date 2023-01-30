/* eslint-disable import/no-extraneous-dependencies */
import { describe, it } from 'vitest';
import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import App from './App';

describe('App', () => {
  it('Render Hello World', () => {
    // ARRANGE
    render(<App />);
    // ACT
    // ASSERT
    expect(
      screen.getByRole('heading', {
        level: 1,
      })
    ).toHaveTextContent('Hello World');
  });
  it('Renders Not Found if given invalid path', () => {
    // ARRANGE
    render(
      <MemoryRouter initialEntries={['/this-path-does-nothing']}>
        <App />
      </MemoryRouter>
    );

    // ASSERT
    expect(
      screen.getByRole('heading', {
        level: 1,
      })
    ).toHaveTextContent('Not Found');
  });
});
