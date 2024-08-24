import Navbar from "./components/common/navbar";
import ListEmployees from "./components/employees/list";

function App() {
  return (
    <div className="App container text-center">
      <Navbar/>
      <ListEmployees/>
    </div>
  );
}

export default App;
