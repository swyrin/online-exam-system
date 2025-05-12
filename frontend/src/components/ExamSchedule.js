
import React, { useState , useEffect } from "react";
import styles from "./ExamSchedule.module.css";
import "@fortawesome/fontawesome-free/css/all.min.css";

const StarRating = ({ rating, onRatingChange }) => {
  const handleClick = (value) => {
    if (onRatingChange) {
      onRatingChange(value);
    }
  };

  return (
    <div className={styles.starRating}>
      {[1, 2, 3, 4, 5].map((star) => (
        <span
          key={star}
          className={star <= rating ? styles.starActive : styles.starInactive}
          onClick={() => handleClick(star)}
        >
          ★
        </span>
      ))}
    </div>
  );
};

const ExamSchedule = () => {
  
   
  

  const [exams, setExams] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages , setTotalPages] = useState(1);
  const [searchInput, setSearchInput] = useState("");
  const [statusFilter, setStatusFilter] = useState("");
  const [modalOpen, setModalOpen] = useState(false);
  const [editingExam, setEditingExam] = useState(null);
  const [formData, setFormData] = useState({
    subject: "",
    date: "",
    time: "",
    difficulty: 1,
    status: "Upcoming",
  });
  const [assignmentModalOpen, setAssignmentModalOpen] = useState(false);
  const [selectedExamId, setSelectedExamId] = useState(null);
  const [assignmentData, setAssignmentData] = useState({
    selectedStudents: [],
    room: "",
    capacity: "",
  });
  const availableStudents = [
    "John Doe",
    "Jane Smith",
    "Michael Brown",
    "Emily Davis",
    "Chris Johnson",
    "Sophia Lee",
    "David Wilson",
    "Amelia Moore",
  ];
  const itemsPerPage = 5;

  
 
  const initialExams = async (page =0 , size = itemsPerPage) => {
      try {
         const response = await fetch(`https://localhost:8081/exams?page=${page}&size=${size}`);
         if(!response.ok){
            throw new Error ("Could not fetch the exams");
         }
         const data = await response.json();
         setExams(data.content);
         setTotalPages(data.totalPages);

      } catch (error) {
         console.log(error);
      }
  }

     
  useEffect(() =>{
     //Call the fetch function inside the useEffect()
     initialExams(currentPage);
    
  },[currentPage]);

  const filteredExams = exams.filter((exam) => {
    const matchesSearch =
      exam.subject.toLowerCase().includes(searchInput.toLowerCase()) ||
      exam.date.includes(searchInput);
    const matchesStatus = statusFilter ? exam.status === statusFilter : true;
    return matchesSearch && matchesStatus;
  });
    
   const paginatedExams = filteredExams.slice(
    (currentPage - 1) * itemsPerPage,
    currentPage * itemsPerPage
  );
  
 

  const prevPage = () => {
    if (currentPage > 1) setCurrentPage(currentPage - 1);
  };

  const nextPage = () => {
    if (currentPage < totalPages) setCurrentPage(currentPage + 1);
  };

  const openAddModal = () => {
    setEditingExam(null);
    setFormData({
      subject: "",
      date: "",
      time: "",
      difficulty: 1,
      status: "Upcoming",
    });
    setModalOpen(true);
  };

  const openEditModal = (id) => {
    const exam = exams.find((e) => e.id === id);
    if (exam) {
      setEditingExam(id);
      setFormData({
        subject: exam.subject,
        date: exam.date,
        time: exam.time,
        difficulty: exam.difficulty,
        status: exam.status,
      });
      setModalOpen(true);
    }
  };

  const closeModal = () => {
    setModalOpen(false);
  };

  const deleteExam = (id) => {
     
    fetch(`https://localhost:8081/exams/${id}`,{
          method : 'DELETE', 
    })
    .then(response=>{
       if(!response.ok){
         throw new Error("Could not delete the exam");
       }
       return response.json();
    })
    .then(()=>{
       
       setExams(exams.filter((exam) => exam.id !== id));
       console.log("success");
    })
    .catch(error =>{
        error = console.log(error);
    })
    // if (window.confirm("Are you sure you want to delete this exam?")) {
    //   setExams(exams.filter((exam) => exam.id !== id));
    // }
  };

  const handleFormSubmit = (e) => {
    e.preventDefault();
    const newExam = {
      id: editingExam || Date.now().toString(),
      subject: formData.subject,
      date: formData.date,
      time: formData.time,
      difficulty: formData.difficulty,
      status: formData.status,
      assignedStudents: editingExam
        ? exams.find((exam) => exam.id === editingExam).assignedStudents || []
        : [],
      room: editingExam
        ? exams.find((exam) => exam.id === editingExam).room || ""
        : "",
      capacity: editingExam
        ? exams.find((exam) => exam.id === editingExam).capacity || 0
        : 0,
    };
    if (editingExam) {
         //update the existing exam
         fetch (`https://localhost:8081/exams/${editingExam}`,{
            method : "PUT",
            headers :{
               "Content-Type" : "application/json",
            },
            body : JSON.stringify(newExam),

         })
         .then(response=>{
             if(!response.ok){
               throw new Error ("Could not update the following exam!");
             }
             return response.json();
         })
         .then(updatedExams=>{
             setExams(
               exams.map((exam) => exam.id === editingExam ? updatedExams : exam)
             );
         })
         .catch(error=>{
             console.log(error);
         })
        
    } else {
      
       //if exam does not exist => create 
       fetch(`https://localhost:8081/exams`,{
          method : "PUT",
          headers :{
              "Content-Type" : "application/json",
          },
          body : JSON.stringify(newExam),
       })
       .then(response =>{
            if(!response.ok){
                throw new Error ("Could not create the exam !");
            }
             return response.json();
       })
       .then(savedExam =>{
            const updatedExams = exams.some(exam => exam.id === savedExam.id)
            ? exams.map(exam => exam.id === savedExam.id ? savedExam : exam) // update
            : [...exams, savedExam]; // create
            setExams(updatedExams);
       })
       .catch(error=>{
           console.log(error);
       })
    }
    setModalOpen(false);
  };

  const renderStars = (rating , examId) => {
    return (
      <div className={styles.starRatingDisplay}>
        {[1, 2, 3, 4, 5].map((star) => (
          <span
            key={star}
            className={star <= rating ? styles.starActive : styles.starInactive}
            onClick={() => updateDifficulty(examId , star)}
          >
            ★
          </span>
        ))}
      </div>
    );
  };

  const openAssignmentModal = (examId) => {
    setSelectedExamId(examId);
    setAssignmentData({ selectedStudents: [], room: "", capacity: "" });
    setAssignmentModalOpen(true);
  };

  const closeAssignmentModal = () => {
    setAssignmentModalOpen(false);
  };

  const handleAssignmentSubmit = (e) => {
    e.preventDefault();
    const updatedExams = exams.map((exam) => {
      if (exam.id === selectedExamId) {
        return {
          ...exam,
          assignedStudents: assignmentData.selectedStudents,
          room: assignmentData.room,
          capacity: assignmentData.capacity,
        };
      }
      return exam;
    });
    setExams(updatedExams);
    setAssignmentModalOpen(false);
  };

  const updateDifficulty = (examId, newDifficulty) => {
    const updatedExam = exams.find((exam) => exam.id === examId);
    if (!updatedExam) return;

    const examToUpdate = {
      ...updatedExam,
       difficulty: newDifficulty
    };

    fetch(`https://localhost:8081/exams/${examId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(examToUpdate),
   })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to update difficulty");
      }
      return response.json();
    })
    .then((updatedExamFromServer) => {
      setExams(exams.map((exam) => exam.id === examId ? updatedExamFromServer : exam));
    })
    .catch((error) => {
      console.error("Update error:", error);
    });
  };


  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <h1>Exam Schedule</h1>
        <p>Manage your exam schedule with ease</p>
      </div>
      <div className={styles.controls}>
        <div className={styles.searchFilter}>
          <input
            type="text"
            placeholder="Search by subject or date..."
            value={searchInput}
            onChange={(e) => {
              setSearchInput(e.target.value);
              setCurrentPage(1);
            }}
          />
          <select
            value={statusFilter}
            onChange={(e) => {
              setStatusFilter(e.target.value);
              setCurrentPage(1);
            }}
          >
            <option value="">All Status</option>
            <option value="Upcoming">Upcoming</option>
            <option value="Completed">Completed</option>
            <option value="Cancelled">Cancelled</option>
          </select>
        </div>
        <button className={styles.addBtn} onClick={openAddModal}>
          Add New Exam
        </button>
      </div>
      <div className={styles.scheduleTable}>
        <table>
          <thead>
            <tr>
              <th>Exam ID</th>
              <th>Subject</th>
              <th>Date</th>
              <th>Time</th>
              <th>Difficulty</th>
              <th>Status</th>
              <th>Room</th>
              <th>Capacity</th>
              <th>Assigned Students</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {paginatedExams.map((exam) => (
              <tr key={exam.id}>
                <td>{exam.id}</td>
                <td>{exam.subject}</td>
                <td>{exam.date}</td>
                <td>{exam.time}</td>
                <td>{renderStars(exam.difficulty , exam.ExamID)}</td>
                <td>
                  <span
                    className={`${styles.status} ${
                      styles[exam.status.toLowerCase()]
                    }`}
                  >
                    {exam.status}
                  </span>
                </td>
                <td>{exam.room}</td>
                <td>{exam.capacity}</td>
                <td>
                  {exam.assignedStudents && exam.assignedStudents.join(", ")}
                </td>
                <td>
                  <button
                    className={`${styles.actionBtn} ${styles.edit}`}
                    onClick={() => openEditModal(exam.id)}
                  >
                    Edit
                  </button>
                  <button
                    className={`${styles.actionBtn} ${styles.delete}`}
                    onClick={() => deleteExam(exam.id)}
                  >
                    Delete
                  </button>
                  <button
                    className={`${styles.actionBtn} ${styles.assign}`}
                    onClick={() => openAssignmentModal(exam.id)}
                  >
                    Assign Students
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div className={styles.pagination}>
        <button onClick={prevPage} disabled={currentPage === 1}>
          Previous
        </button>
        <button onClick={nextPage} disabled={currentPage >= totalPages}>
          Next
        </button>
      </div>
      {modalOpen && (
        <div className={styles.modal}>
          <div className={styles.modalContent}>
            <h2>{editingExam ? "Edit Exam" : "Add New Exam"}</h2>
            <form onSubmit={handleFormSubmit}>
              <input
                type="text"
                placeholder="Subject"
                required
                value={formData.subject}
                onChange={(e) =>
                  setFormData({ ...formData, subject: e.target.value })
                }
              />
              <input
                type="date"
                required
                value={formData.date}
                onChange={(e) =>
                  setFormData({ ...formData, date: e.target.value })
                }
              />
              <input
                type="time"
                required
                value={formData.time}
                onChange={(e) =>
                  setFormData({ ...formData, time: e.target.value })
                }
              />
              <div className={styles.difficultyContainer}>
                <span className={styles.difficultyLabel}>Difficulty:</span>
                <StarRating
                  rating={formData.difficulty}
                  onRatingChange={(value) =>
                    setFormData({ ...formData, difficulty: value })
                  }
                />
              </div>
              <select
                required
                value={formData.status}
                onChange={(e) =>
                  setFormData({ ...formData, status: e.target.value })
                }
              >
                <option value="Upcoming">Upcoming</option>
                <option value="Completed">Completed</option>
                <option value="Cancelled">Cancelled</option>
              </select>
              <div className={styles.modalButtons}>
                <button type="submit">Save</button>
                <button type="button" onClick={closeModal}>
                  Cancel
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
      {assignmentModalOpen && (
        <div className={styles.modal}>
          <div className={styles.modalContent}>
            <h2>Assign Students, Room & Capacity</h2>
            <form onSubmit={handleAssignmentSubmit}>
              <div>
                <label>Select Students:</label>
                <div className={styles.studentsList}>
                  {availableStudents.map((student) => (
                    <div key={student}>
                      <input
                        type="checkbox"
                        id={student}
                        value={student}
                        checked={assignmentData.selectedStudents.includes(
                          student
                        )}
                        onChange={(e) => {
                          const value = e.target.value;
                          let updated;
                          if (e.target.checked) {
                            updated = [
                              ...assignmentData.selectedStudents,
                              value,
                            ];
                          } else {
                            updated = assignmentData.selectedStudents.filter(
                              (s) => s !== value
                            );
                          }
                          setAssignmentData({
                            ...assignmentData,
                            selectedStudents: updated,
                          });
                        }}
                      />
                      <label htmlFor={student}>{student}</label>
                    </div>
                  ))}
                </div>
              </div>
              <div>
                <label>Room:</label>
                <input
                  type="text"
                  placeholder="Room (e.g., Room A1 204)"
                  value={assignmentData.room}
                  onChange={(e) =>
                    setAssignmentData({
                      ...assignmentData,
                      room: e.target.value,
                    })
                  }
                  required
                />
              </div>
              <div>
                <label>Capacity:</label>
                <input
                  type="number"
                  placeholder="Capacity (e.g., 40)"
                  value={assignmentData.capacity}
                  onChange={(e) =>
                    setAssignmentData({
                      ...assignmentData,
                      capacity: e.target.value,
                    })
                  }
                  required
                />
              </div>
              <div className={styles.modalButtons}>
                <button type="submit">Save Assignment</button>
                <button type="button" onClick={closeAssignmentModal}>
                  Cancel
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default ExamSchedule;
