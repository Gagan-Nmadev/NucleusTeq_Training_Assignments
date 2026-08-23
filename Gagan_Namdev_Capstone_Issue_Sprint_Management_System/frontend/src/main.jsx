import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import { Toaster } from "react-hot-toast";

import App from "./App";
import "./styles/global.css";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <BrowserRouter>

      <Toaster
        position="top-center"
        reverseOrder={false}
        toastOptions={{
          duration: 3000,

          success: {
            style: {
              background: "#16a34a",
              color: "#ffffff",
              fontWeight: "600",
              borderRadius: "8px",
              padding: "14px 18px",
            },
            iconTheme: {
              primary: "#ffffff",
              secondary: "#16a34a",
            },
          },

          error: {
            style: {
              background: "#dc2626",
              color: "#ffffff",
              fontWeight: "600",
              borderRadius: "8px",
              padding: "14px 18px",
            },
            iconTheme: {
              primary: "#ffffff",
              secondary: "#dc2626",
            },
          },
        }}
      />

      <App />

    </BrowserRouter>
  </React.StrictMode>
);