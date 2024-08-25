import React, { useEffect } from 'react'
import axios from 'axios';
import { Link, useParams } from 'react-router-dom'
import { useNavigate } from 'react-router-dom'

export default function EditEmployee() {
  let navigation = useNavigate();

  const apiUrl = process.env.REACT_APP_API_URL + '/employees';

  const {id} = useParams();

  const [employee, setEmployee] = React.useState({
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
    job: '',
    salary: '',
  });

  const{firstName, lastName, email, phone, job, salary} = employee;

  useEffect(() => {
    loadEmployee();
  }, [id]);

  const loadEmployee = async () => {
    const result = await axios.get(apiUrl + '/' + id);
    setEmployee(result.data);
  }

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.post(apiUrl, employee);
    navigation('/');
  }

  return (
    <div className="container">
      {/* <Header /> */}
      <div className="container text-center" style={{ margin: "50px" }}>
        <h1>Edit Employee</h1>
      </div>

      {/* Form */}
      <form onSubmit={(e) => onSubmit(e)}>
        <div className="mb-3">
          <label htmlFor="firstName" className="form-label">First name</label>
          <input type="text" className="form-control" id="firstName" name="firstName" value={firstName} onChange={(e) => setEmployee({...employee, firstName: e.target.value})} required={true}/>
        </div>
        <div className="mb-3">
          <label htmlFor="lastName" className="form-label">Last name</label>
          <input type="text" className="form-control" id="lastName" name="lastName" value={lastName} onChange={(e) => setEmployee({...employee, lastName: e.target.value})} required={true}/>
        </div>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">Email</label>
          <input type="email" className="form-control" id="email" aria-describedby="emailHelp" name="email" value={email} onChange={(e) => setEmployee({...employee, email: e.target.value})} required={true}/>
        </div>
        <div className="mb-3">
          <label htmlFor="phone" className="form-label">Phone</label>
          <input type="number" className="form-control" id="phone" name="phone" value={phone} onChange={(e) => setEmployee({...employee, phone: e.target.value})} required={true}/>
        </div>
        <div className="mb-3">
          <label htmlFor="job" className="form-label">Job</label>
          <input type="text" className="form-control" id="job" name="job" value={job} onChange={(e) => setEmployee({...employee, job: e.target.value})} required={true}/>
        </div>
        <div className="mb-3">
          <label htmlFor="salary" className="form-label">Salary</label>
          <input type="number" className="form-control" id="salary" name="salary" value={salary} onChange={(e) => setEmployee({...employee, salary: e.target.value})} required={true}/>
        </div>
        {/* Buttons */}
        <div className="text-center">
          <button type="submit" className="btn btn-primary me-3">Submit</button>
          <button type="reset" className="btn btn-warning me-3">Reset</button>
          <Link type="button" className="btn btn-danger" to="/">Go back</Link>
        </div>
      </form>
    </div>
  )
}
