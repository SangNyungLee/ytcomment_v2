import React from "react";
import { StrictMode } from "react";
import ReactDOM from "react-dom/client";
// import "./index.css"; // CSS 경로 확인
import App from "./App";
import { Provider } from "react-redux"; // Redux 사용 시 필요
import store from "./store"; // Redux 사용 시 필요

ReactDOM.createRoot(document.getElementById("root")).render(
  <StrictMode>
    <Provider store={store}>
      <App />
    </Provider>
  </StrictMode>
);
