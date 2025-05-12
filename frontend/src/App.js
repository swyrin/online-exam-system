import React from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Header from "./components/Header";
import FutureOfLearning from "./components/FutureOfLearning";
import ExamSchedule from "./components/ExamSchedule";
import StudentList from "./components/StudentList";

function App() {
    return (
        <Router>
            <Header/>
            <Routes>
                <Route path="/" element={<FutureOfLearning/>}/>
                <Route path="/exam-schedule" element={<ExamSchedule/>}/>
                <Route path="/student-list" element={<StudentList></StudentList>}/>
            </Routes>
        </Router>
    );
}

export default App;
