import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const EmpProfile = () => {
  const navigate = useNavigate();
  const [employee, setEmployee] = useState(null);
  const [showAttendance, setShowAttendance] = useState(false);

  useEffect(() => {
    const storedEmployee = JSON.parse(localStorage.getItem("user"));
    if (!storedEmployee) {
      navigate("/"); // Redirect to login if not authenticated
    } else {
      setEmployee(storedEmployee);
    }
  }, [navigate]);

  const handleLogout = async () => {
    if (!employee) return;

    try {
      const response = await fetch(`http://localhost:8080/attendance/logout/${employee.id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
      });

      if (response.ok) {
        localStorage.removeItem("user");
        navigate("/");
      } else {
        console.error("Failed to update logout time");
      }
    } catch (error) {
      console.error("Error during logout:", error);
    }
  };

  return (
    <div style={styles.container}>
      <button
        onClick={() => setShowAttendance(!showAttendance)}
        style={styles.attendanceButton}
      >
        {showAttendance ? "Hide Attendance" : "Attendance"}
      </button>

      <div style={styles.profileSection}>
        <img
          src={employee?.profilePic}
          alt="Profile"
          style={styles.profilePic}
        />
        <h2>{employee?.name}</h2>
        <p><strong>Project:</strong> {employee?.project}</p>
        <p><strong>Location:</strong> {employee?.address}</p>
        <p><strong>CTC:</strong> ₹{employee?.ctc.toLocaleString()}</p>
      </div>

      {showAttendance && (
        <div style={styles.attendanceSection}>
          <h3>Attendance Records</h3>
          <table style={styles.table}>
            <thead>
              <tr>
                <th>Date</th>
                <th>Login Time</th>
                <th>Logout Time</th>
              </tr>
            </thead>
            <tbody>
              {employee?.attendanceRecords?.map((record) => (
                <tr key={record.id}>
                  <td>{record.date}</td>
                  <td>{record.loginTime}</td>
                  <td>{record.logoutTime}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      <button
        onClick={handleLogout}
        style={styles.logoutButton}
      >
        Logout
      </button>
    </div>
  );
};

const styles = {
  container: {
    width: "400px",
    margin: "100px auto",
    padding: "20px",
    textAlign: "center",
    border: "1px solid #ccc",
    borderRadius: "10px",
    boxShadow: "0px 4px 8px rgba(0, 0, 0, 0.2)",
    position: "relative",
  },
  profileSection: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
  },
  profilePic: {
    width: "120px",
    height: "120px",
    borderRadius: "50%",
    marginBottom: "10px",
  },
  attendanceButton: {
    position: "absolute",
    top: "10px",
    right: "10px",
    padding: "8px 12px",
    backgroundColor: "#007bff",
    color: "white",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
  },
  attendanceSection: {
    marginTop: "20px",
    textAlign: "left",
  },
  table: {
    width: "100%",
    borderCollapse: "collapse",
    marginTop: "10px",
  },
  logoutButton: {
    padding: "10px",
    fontSize: "16px",
    backgroundColor: "#ff4c4c",
    color: "white",
    border: "none",
    cursor: "pointer",
    marginTop: "20px",
  },
};

export default EmpProfile;
