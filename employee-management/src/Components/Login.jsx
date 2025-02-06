import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [isAdmin, setIsAdmin] = useState(false);
  const navigate = useNavigate();

  const handleLogin = async () => {
    const endpoint = isAdmin 
      ? "http://localhost:8080/admin/login" 
      : "http://localhost:8080/employees/login";
      console.log(endpoint)

    try {
      const response = await axios.post(endpoint, {
        emailId: email,
        password,
      });

      if ( response.data) {
        console.log(response.data)
        localStorage.setItem("user", JSON.stringify(response.data));
        navigate(isAdmin ? "/employees" : "/Profile");
      } else {
        alert("Invalid credentials");
      }
    } catch (error) {
      alert("Login failed");
    }
  };

  return (
    <div style={styles.pageContainer}>
      <div style={styles.container}>
        <h2 style={styles.heading}>
          {isAdmin ? "Admin Login" : "Employee Login"}
        </h2>
        <input 
          type="email" 
          placeholder="Email" 
          onChange={(e) => setEmail(e.target.value)} 
          style={styles.input}
        />
        <input 
          type="password" 
          placeholder="Password" 
          onChange={(e) => setPassword(e.target.value)} 
          style={styles.input}
        />
        <button onClick={handleLogin} style={styles.button}>
          Login
        </button>
        <button 
          onClick={() => setIsAdmin(!isAdmin)} 
          style={styles.toggleButton}
        >
          {isAdmin ? "Switch to Employee Login" : "Switch to Admin Login"}
        </button>
      </div>
    </div>
  );
};

const styles = {
  pageContainer: {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    height: "100vh",
    backgroundColor: "#f4f4f4",
  },
  container: {
    width: "350px",
    padding: "25px",
    textAlign: "center",
    borderRadius: "8px",
    backgroundColor: "white",
    boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.1)",
  },
  heading: {
    marginBottom: "20px",
    color: "#333",
  },
  input: {
    width: "100%",
    padding: "10px",
    marginBottom: "15px",
    fontSize: "16px",
    borderRadius: "5px",
    border: "1px solid #ccc",
  },
  button: {
    width: "100%",
    padding: "10px",
    fontSize: "16px",
    backgroundColor: "#007bff",
    color: "white",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
    marginBottom: "10px",
  },
  toggleButton: {
    width: "100%",
    padding: "8px",
    fontSize: "14px",
    backgroundColor: "#6c757d",
    color: "white",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
  },
};

export default Login;
