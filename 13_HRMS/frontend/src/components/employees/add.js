import React from 'react'
import { Link } from 'react-router-dom'

export default function AddEmployee() {
  return (
    <div className="container">
      {/* <Header /> */}
      <div className="container text-center" style={{ margin: "50px" }}>
        <h1>Human Resources Management System</h1>
      </div>

      {/* Form */}
      <form>
        <div className="mb-3">
          <label htmlFor="firstName" className="form-label">First Name</label>
          <input type="text" className="form-control" id="firstName" name="firstName" required={true}/>
        </div>
        <div className="mb-3">
          <label htmlFor="lastName" className="form-label">Last Name</label>
          <input type="text" className="form-control" id="lastName" name="lastName" required={true}/>
        </div>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">Email address</label>
          <input type="email" className="form-control" id="email" aria-describedby="emailHelp" name="email" required={true}/>
        </div>
        <div className="mb-3">
          <label htmlFor="phone" className="form-label">Phone</label>
          <input type="number" className="form-control" id="phone" name="phone" required={true}/>
        </div>
        <div className="mb-3">
          <label htmlFor="job" className="form-label">Job</label>
          <input type="text" className="form-control" id="job"/>
        </div>
        <div className="mb-3">
          <label htmlFor="department" className="form-label">Department</label>
          <input type="text" className="form-control" id="department"/>
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
