import React from 'react';
import axios from 'axios';

export default function ListEmployees() {
  const apiUrl = process.env.REACT_APP_API_URL + '/employees';
  const [employees, setEmployees] = React.useState([]);

  React.useEffect(() => {
    loadEmployees();
  }, []);

  const loadEmployees = async () => {
    const result = await axios.get(apiUrl);
    console.log(result.data); // dbg
    setEmployees(result.data);
  };

  return (
    <div className="container">
      {/* <Header /> */}
      <div className="container text-center" style={{ margin: "50px" }}>
        <h1>Human Resources Management System</h1>
      </div>

      {/* Table */}
      <table className="table table-striped table-hover align-middle">
        <thead className="table-dark">
          <tr>
            <th scope="col">#</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Email</th>
            <th scope="col">Phone</th>
            <th scope="col">Job</th>
            <th scope="col">Department</th>
            <th scope="col">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th scope="row">1</th>
            <td>Mark</td>
            <td>Otto</td>
            <td>@mdo</td>
          </tr>
          <tr>
            <th scope="row">2</th>
            <td>Jacob</td>
            <td>Thornton</td>
            <td>@fat</td>
          </tr>
          <tr>
            <th scope="row">3</th>
            <td colspan="2">Larry the Bird</td>
            <td>@twitter</td>
          </tr>
        </tbody>
      </table>
    </div>

  );
}
