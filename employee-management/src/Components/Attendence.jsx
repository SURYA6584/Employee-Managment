import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

const Attendance = () => {
  const { id } = useParams();
  const [attendance, setAttendance] = useState([]);
 console.log(attendance)
  useEffect(() => {
    axios
      .get(`http://localhost:8080/attendance/${id}`)
      .then((response) => setAttendance(response.data))
      .catch((error) => console.error("Error fetching attendance", error));
  }, [id]);

  return (
    <div>
      <h2>Last 10 Days Attendance</h2>
      <table border="1">
        <thead>
          <tr>
            <th>Date</th>
            <th>Login</th>
            <th>Logout</th>
          </tr>
        </thead>
        <tbody>
          {attendance.map((record, index) => (
            <tr key={index}>
              <td>{record.date}</td>
              <td>{record.loginTime}</td>
              <td>{record.logoutTime}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Attendance;
