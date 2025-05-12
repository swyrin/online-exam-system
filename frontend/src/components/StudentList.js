import React, {useEffect, useState} from "react";
import "./Studentliststyle.css";
import "@fortawesome/fontawesome-free/css/all.min.css";

export default function StudentList() {
    const [search, setSearch] = useState("");
    let [students, setStudents] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8081/persons?page=0&size=10",
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
            .then(res => res.json())
            .then(res => {
                setStudents(res["content"]);
            });
    }, []);

    const filteredStudents = students;

    return (
        <div className="student-list-container">
            <div className="search-container">
                <div className="search-wrapper">
                    <i className="fa fa-search search-icon"></i>
                    <input
                        type="text"
                        placeholder="Search students..."
                        className="search-input"
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                    />
                </div>
            </div>

            {filteredStudents.length === 0 && (
                <p
                    style={{
                        padding: "1.5rem",
                        textAlign: "center",
                        fontSize: "1.2rem",
                        color: "#777",
                    }}
                >
                    No students found.
                </p>
            )}

            <section id="student-grid">
                {filteredStudents.map((student, index) => (
                    <div className="student-card" key={index}>
                        <div className="student-image">
                            {/*<img src={student.image} alt={student.name} />*/}
                        </div>
                        <h2>{`${student.FirstName} ${student.MiddleName} ${student.LastName}`} </h2>
                        <p>Joined: {student.JoinDate}</p>
                        <p>ID: {student.PersonID}</p>
                    </div>
                ))}
            </section>
        </div>
    );
}
