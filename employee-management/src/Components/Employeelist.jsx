import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

// EmployeeList component
const EmployeeList = () => {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);  // Loading state
  const [error, setError] = useState(null);  // Error state
  const navigate = useNavigate();

  // Fetch employee data on component mount
  useEffect(() => {
    axios
      .get("http://localhost:8080/employees")
      .then((response) => {
        setEmployees(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching employees", error);
        setError("Failed to fetch employee data.");
        setLoading(false);
      });
  }, []);

  // Loading state
  if (loading) {
    return <div>Loading...</div>;
  }

  // Error handling
  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <h2>Employee List</h2>
      <table border="1">
        <thead>
          <tr>
            <th>ID</th>
            <th>Profile</th>
            <th>Name</th>
            <th>Address</th>
            <th>CTC</th>
            <th>Project</th>
            <th>Attendance</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((emp) => (
            <tr key={emp.id}>
              <td>{emp.id}</td>
              <td>
                <img 
                  src={emp.profilePic ?? "default-image.jpg"} 
                  alt="Profile" 
                  width="50" 
                />
              </td>
              <td>{emp.name}</td>
              <td>{emp.address}</td>
              <td>{emp.ctc}</td>
              <td>{emp.project}</td>
              <td>
                <button onClick={() => navigate(`/attendance/${emp.id}`)}>
                  View Attendance
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default EmployeeList;
