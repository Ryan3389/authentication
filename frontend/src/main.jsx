import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import HomePage from "./pages/HomePage.jsx";
import LoginPage from "./pages/LoginPage.jsx";
import SignupPage from "./pages/SignupPage.jsx";
import Verify from "./pages/Verify.jsx";

const router = createBrowserRouter([
    {
        path: "/",
        element: <App />,
        errorElement: <h1>Error, something went wrong</h1>,
        children: [
            {
                index: true,
                element: <HomePage />
            },
            {
                path: "/login",
                element: <LoginPage />
            },
            {
                path: "/signup",
                element: <SignupPage />
            },
            {
                path: "/verify",
                element: <Verify />
            }

        ]
    }

])
createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)
