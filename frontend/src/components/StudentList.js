import React, { useState } from "react";
import "./Studentliststyle.css";
import "@fortawesome/fontawesome-free/css/all.min.css";

const initialStudents = [
  {
    name: "John Doe",
    joined: "Jan 2023",
    major: "Computer Science",
    image: "/Asuna_Sword_Art_Online.jpg",
  },
  {
    name: "Jane Smith",
    joined: "Sep 2022",
    major: "Biology",
    image: "/studentlist/Doraemon-12-1024x576.jpg",
  },
  {
    name: "Michael Brown",
    joined: "Aug 2021",
    major: "Engineering",
    image: "/studentlist/chaien.webp",
  },
  {
    name: "Emily Davis",
    joined: "Feb 2022",
    major: "Literature",
    image: "/studentlist/conan.jpg",
  },
  {
    name: "Chris Johnson",
    joined: "Nov 2021",
    major: "Music",
    image: "/studentlist/klee.webp",
  },
  {
    name: "Sophia Lee",
    joined: "Apr 2023",
    major: "Information Technology",
    image: "/studentlist/nobita.webp",
  },
  {
    name: "David Wilson",
    joined: "Jul 2023",
    major: "Mathematics",
    image: "/studentlist/shizuka.jpg",
  },
  {
    name: "Amelia Moore",
    joined: "Dec 2022",
    major: "Psychology",
    image: "/studentlist/suneo.jpg",
  },
];

export default function StudentList() {
  const [search, setSearch] = useState("");

  const filteredStudents = initialStudents.filter(
    (student) =>
      student.name.toLowerCase().includes(search.toLowerCase()) ||
      student.major.toLowerCase().includes(search.toLowerCase())
  );

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
              <img src={student.image} alt={student.name} />
            </div>
            <h2>{student.name}</h2>
            <p>Joined: {student.joined}</p>
            <p>Major: {student.major}</p>
          </div>
        ))}
      </section>
    </div>
  );
}
