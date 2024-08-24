import Navbar from "./components/common/navbar";
import ListEmployees from "./components/employees/list";
import AddEmployee from "./components/employees/add";

import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <div className="App container">
      <BrowserRouter>
        <Navbar/>
        <Routes>
          <Route path="/" element={<ListEmployees/>} />
          <Route path="/add-employee" element={<AddEmployee/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
