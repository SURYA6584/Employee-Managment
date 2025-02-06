import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Components/Login";
import EmployeeList from "./Components/Employeelist";
import Attendance from "./Components/Attendence";
import EmpProfile from "./Components/employeeProfile";

function App() {
  return (
    <Router>
      <Routes>
      <Route path="/" element={<Login />} />

        <Route path="/employees" element={<EmployeeList />} />
        <Route path="/Profile" element={<EmpProfile />} />

        <Route path="/attendance/:id" element={<Attendance />} />
      </Routes>
    </Router>
  );
}

export default App;
