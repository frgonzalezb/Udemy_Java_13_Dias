import Navbar from "./components/common/navbar";
import ListEmployees from "./components/employees/list";

import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <div className="App container text-center">
      <BrowserRouter>
        <Navbar/>
        <Routes>
          <Route path="/" element={<ListEmployees/>} />
          <Route path="/add-employee" element={<ListEmployees/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
