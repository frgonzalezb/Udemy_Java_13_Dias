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

  const deleteEmployee = async (id) => {
    await axios.put(apiUrl + '/' + id + '/deactivate');
    loadEmployees();
  }

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
            <th scope="col">Salary</th>
            <th scope="col">Actions</th>
          </tr>
        </thead>
        <tbody>
          {
            employees.map((employee, index) => (
              <tr key={employee.id}>
                <th scope="row">{index + 1}</th>
                <td>{employee.firstName}</td>
                <td>{employee.lastName}</td>
                <td>{employee.email}</td>
                <td>{employee.phone}</td>
                <td>{employee.job}</td>
                <td>{employee.salary}</td>
                <td>
                  <a href={`/edit-employee/${employee.id}`} className="btn btn-primary me-3">Edit</a>
                  <button onClick={() => deleteEmployee(employee.id)} type="button" className="btn btn-danger">Delete</button>
                </td>
              </tr>
            ))
          }
        </tbody>
      </table>
    </div>

  );
}
