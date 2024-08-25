import Navbar from "./components/common/navbar";
import ListEmployees from "./components/employees/list";
import AddEmployee from "./components/employees/add";
import EditEmployee from "./components/employees/edit";

import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <div className="App container">
      <BrowserRouter>
        <Navbar/>
        <Routes>
          <Route exact path="/" element={<ListEmployees/>} />
          <Route exact path="/add-employee" element={<AddEmployee/>} />
          <Route exact path="/edit-employee/:id" element={<EditEmployee/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
