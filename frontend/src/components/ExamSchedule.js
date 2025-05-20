import React, {useEffect, useState} from "react";
import { v4 as uuidv4 } from 'uuid';
import styles from "./ExamSchedule.module.css";
import "@fortawesome/fontawesome-free/css/all.min.css";

const StarRating = ({rating, onRatingChange}) => {
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
    const [testTypes, setTestTypes] = useState([]);
    const [courses, setCourses] = useState([]);
    const [students, setStudents] = useState([]); // New state for students
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages , setTotalPages] = useState(1);
    const [editBtn , setEditBtn] = useState(false);
    const [deleteBtn , setDeleteBtn] = useState(false);
    const [assignBtn , setAssignBtn] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");
    const [isSubmitting, setIsSubmitting] = useState(false); // New state to track form submission
    const [searchInput, setSearchInput] = useState("");
    const [statusFilter, setStatusFilter] = useState("");
    const [modalOpen, setModalOpen] = useState(false);
    const [editingExam, setEditingExam] = useState(null);
     const [newExamId, setNewExamId] = useState(null); // New state for pre-generated examID
    const [formData, setFormData] = useState({
        courseID: "",
        courseName:"",
        bagCode: "",
        date: "",
        time: "",
        difficulty: 1,
        status: "Upcoming",
        typeID:"",
    });
    const [assignmentModalOpen, setAssignmentModalOpen] = useState(false);
    const [selectedExamId, setSelectedExamId] = useState(null);
    const [assignmentData, setAssignmentData] = useState({
        selectedStudents: [],
        room: "",
        capacity: "",
    });

    

    const itemsPerPage = 10;
    // const initialExam = async (page =0 , size = itemsPerPage) =>{
    //     try {
            
    //          const response = await fetch(`http://localhost:8081/exams?page=${page}&size=${size}`);
    //           if(!response.ok){
    //              throw new Error("Could not fetch the exams!");
    //           }
    //          const data = await response.json();
    //          setExams(data.content);
    //          setTotalPages(data.totalPages);
    //     } catch (error) {
    //         console.log(error);
    //     }
       
        
    // }
    // Fetch students on component mount
  useEffect(() => {
    
    fetch("http://localhost:8081/persons")
      .then((res) => {
        if (!res.ok) throw new Error("Could not fetch students");
        return res.json();
      })
      .then((data) => {
        const studentsData = Array.isArray(data) ? data : (data.content || []);
        setStudents(studentsData);
        console.log("Fetched students:", studentsData);
        
      })
      .catch((error) => {
        console.error("Fetch students error:", error);
        setStudents([]);
        setErrorMessage("Failed to load students. Please try again.");
      });
  }, []);

    useEffect(() => {
    fetch(`http://localhost:8081/courses`)
      .then((res) => {
        if (!res.ok) throw new Error("Could not fetch courses");
        return res.json();
      })
      .then((data) =>{
      const coursesData = Array.isArray(data) ? data : (data.content || []);
       setCourses(coursesData);
    })
      .catch((error) =>{ 
        console.log("Fetch courses error:", error);
        setCourses([]);

    });
  }, []);

     useEffect(() => {
    fetch(`http://localhost:8081/types`)
      .then((res) => {
        if (!res.ok) throw new Error("Could not fetch test types");
        return res.json();
      })
      .then((data) => setTestTypes(data))
      .catch((error) => console.log("Fetch test types error:", error));
  }, []);

    useEffect(() => {
        fetch(`http://localhost:8081/exams?page=${currentPage}&size=${itemsPerPage}`)
            .then(res => res.json())
            .then(res => {
                let inner = res.content;
                console.log(res) 
                console.log(res.content)
                inner = inner.filter((exam) => {
                    // console.log(exam)
                    // console.log(statusFilter)
                    // console.log(searchInput)
                    return (statusFilter === "" || exam.status === statusFilter) && 
                        ((searchInput === "" || exam.examID === parseInt(searchInput)) ||
                        (searchInput === "" || exam.course.name.toLowerCase().includes(searchInput.toLowerCase())) ||
                        (searchInput === "" || exam.course.abbreviation.toLowerCase().includes(searchInput.toLowerCase())) ||
                        (searchInput === "" || exam.course.courseID.toLowerCase().includes(searchInput.toLowerCase())))
                });
                // console.log(inner)
                setExams(inner || []);

                setTotalPages(res.page.totalPages);
            })
            .catch((error) =>{
                console.log(error);
            })
    }, [currentPage, statusFilter, searchInput]);

    useEffect(()=>{
        if(deleteBtn){
            fetch(`http://localhost:8081/exams?page=${currentPage}&size=${itemsPerPage}`)
            .then(res => res.json())
            .then(res => {
                setExams(res.content);
                totalPages = res.totalPages;
                setDeleteBtn(false);
                console.log("Fetched after delete:", res.content);
                setErrorMessage(""); 
            })
            .catch((error) =>{
                console.log(error);
            })
        }
    },[deleteBtn,currentPage]);

    useEffect(()=>{
        if(editBtn){
        fetch(`http://localhost:8081/exams?page=${currentPage}&size=${itemsPerPage}`)
            .then(res => res.json())
            .then(res => {
                setExams(res.content );
                totalPages = res.totalPages;
                setEditBtn(false);
                console.log("Fetched after edit:", res.content);
            })
            .catch((error) =>{
                console.log(error);
            });
        }
    },[editBtn , currentPage]);

    useEffect(()=>{
        if(assignBtn){
            fetch(`http://localhost:8081/exams?page=${currentPage}&size=${itemsPerPage}`)
            .then(res => res.json())
            .then((res) => {
                setExams(res.content);
                totalPages = res.totalPages;
                setAssignBtn(false);
                console.log("Fetched after assign:", res.content);
            })
            .catch((error) =>{
                console.log(error);
            })
        }
    },[assignBtn , currentPage]);

   const filteredExams = exams.filter((exam) => {
    const matchesSearch = exam.course?.Name?.toLowerCase().includes(searchInput.toLowerCase());
    const matchesStatus = statusFilter ? exam.status === statusFilter : true;
    return matchesSearch && matchesStatus;
  });

    // let totalPages = Math.ceil(filteredExams.length / itemsPerPage);

    const prevPage = () => {
        setCurrentPage(currentPage - 1);
    };

    const nextPage = () => {
        setCurrentPage(currentPage + 1);
        // if (currentPage <= totalPages -1 ) setCurrentPage(currentPage + 1);
    };

    const openAddModal = () => {
        setEditingExam(null);
         const generatedExamId = uuidv4(); // Generate examID once when modal opens
         setNewExamId(generatedExamId); 
        setFormData({
            courseID: "",
            courseName:"",
            bagCode: "",
            date: "",
            time: "",
            difficulty: 1,
            status: "Upcoming",
            typeID:"",
        });
        setModalOpen(true);
    };

    const openEditModal = (id) => {
        const exam = exams.find((e) => e.examID === id);
        if (exam) {
            const selectedCourse = courses.find((course) => course.courseID === exam.course?.courseID);
            setEditingExam(id);
            setFormData({
                courseID: exam.course.CourseID,
                courseName : selectedCourse.Name,   
                bagCode :exam.BagCode,
                date: exam.Date,
                time: exam.Time,
                difficulty: exam.Difficulty,
                status: exam.Status,
                typeID: exam.examType.TypeID,
            });
            setModalOpen(true);
        }
    };

  const handleCourseChange = (courseID) => {
    const selectedCourse = courses.find((course) => course.courseID === courseID);
    setFormData({
      ...formData,
      courseID: courseID,
      courseName: selectedCourse ? selectedCourse.name : "",
    });
  };


    const closeModal = () => {
        setModalOpen(false);
        setErrorMessage(""); // Clear error message when closing modal
        setNewExamId(null); // Reset the newExamId when modal closes
        setIsSubmitting(false); // Reset submitting state
    };

    const deleteExam = (id) => {
        fetch(`http://localhost:8081/exams/${id}`, {
            method: 'DELETE',
        })
            .then((response) => {
                if (!response.ok) {
                    if (response.status === 400 || response.status === 500) {
                        return response.json().then((err) => {
                            throw new Error(err.message || "Failed to delete exam due to constraints");
                        });
                    }
                    throw new Error("Could not delete the exam");
                }
                console.log("Delete successful for exam ID:", id);
                setDeleteBtn(true);
            })
            // .then(() => {
            //     setExams(exams.filter((exam) => exam.examID !== id));
            //     console.log("success");
            // })
            .catch((error) => {
                console.log(error);
                setErrorMessage(
                    "Cannot delete this exam because it is associated with rooms. Please remove the room assignments first."
                );
            });
    };


  

    

    const handleFormSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true); // Disable the Save button

    const selectedCourse = {
      courseID: formData.courseID,
      name: formData.courseName,
      abbreviation: "",
    };
    const selectedExamType = {
        typeID :formData.typeID,
        name: "",
        description:"",

    };


    const formattedTime = formData.time ? `${formData.time}:00` : formData.time;

    const newExam = {
      examID: editingExam || newExamId, 
      course: selectedCourse,
      date: formData.date,
      time: formattedTime,
      difficulty: formData.difficulty,
      status: formData.status,
      bagCode: formData.bagCode,
      examType: selectedExamType,
       assignedStudents: editingExam
                ? exams.find((exam) => exam.examID === editingExam).assignedStudents || []
                : [],
            room: editingExam
                ? exams.find((exam) => exam.examID === editingExam).room || ""
                : "",
            capacity: editingExam
                ? exams.find((exam) => exam.examID === editingExam).capacity || 0
                : 0,
    };

    console.log("Payload being sent:", JSON.stringify(newExam));

    const method = editingExam ? "PUT" : "POST";
    const url = editingExam
      ? `http://localhost:8081/exams/${editingExam}`
      : `http://localhost:8081/exams`;

    try {
      const response = await fetch(url, {
        method,
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newExam),
      });

      if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message || `Could not ${editingExam ? "update" : "create"} the exam!`);
      }

      alert(`${editingExam ? "Update" : "Create"} successful for exam ID:`, newExam.examID);
      setEditBtn(true);
    } catch (error) {
      alert(`${editingExam ? "Update" : "Create"} error:`, error.message);
      setErrorMessage(error.message || "Failed to save exam. Please check the course ID.");
    } finally {
      setModalOpen(false);
      setIsSubmitting(false); 
    }
  };

   

    const renderStars = (rating, examId) => {
        return (
            <div className={styles.starRatingDisplay}>
                {[1, 2, 3, 4, 5].map((star) => (
                    <span
                        key={star}
                        className={star <= rating ? styles.starActive : styles.starInactive}
                        onClick={() => updateDifficulty(examId, star)}
                    >
            ★
          </span>
                ))}
            </div>
        );
    };

    const openAssignmentModal = (examId) => {
        setSelectedExamId(examId);
        // setAssignmentData({selectedStudents: [], room: "", capacity: ""});
    const exam = exams.find((e) => e.examID === examId);
      setAssignmentData({
        selectedStudents: exam?.assignedStudents || [],
        room: exam?.room || "",
        capacity: exam?.capacity || "",
      });
        setAssignmentModalOpen(true);
    };
    // useEffect(()=>{
    //     if(assignBtn && selectedExamId !== null){
    //         fetch(`http://localhost:8081/exams/`)
    //     }
    // })
//      const handleAssignStudents = (exam) => {
    
//     const roomId = exam.room?.id;
//     const personId = exam.supervisor?.id; 
//     if (!roomId || !personId) {
//       alert("Missing room or person ID to assign.");
//       return;
//     }

//     fetch(`/api/rooms/${roomId}/persons/${personId}`, {
//       method: 'PUT',
//       headers: { 'Content-Type': 'application/json' },
//       body: JSON.stringify(exam.room), 
//     })
//     .then(res => res.json())
//     .then(updatedRoom => {

//       console.log("Assigned student to room:", updatedRoom);
//     });
//   };


    const closeAssignmentModal = () => {
        setAssignmentModalOpen(false);
         setErrorMessage(""); 
    };

    const handleAssignmentSubmit = async(e) => {
        e.preventDefault();

        try {
            //assign selectedStudent to each exam 
            const assignPromise = assignmentData.selectedStudents.map((personId)=>
            fetch(`http://localhost:8081/persons/${personId}/exams/${selectedExamId}`,{
                method : "PUT",
                headers :{
                    "Content-Type" :"application/json",
                },
                body: JSON.stringify({personID :personId}),
            })
            .then((response)=>{
                if(!response.ok){
                    throw new Error(`failed to assign exam to persons ${personId}`);
                }
                return response.json();
            })
         );

           //wait for all assignment to complete 
           await Promise.all(assignPromise);
           console.log("all students assigned successfully for examID :" , selectedExamId);

           //update exams' room
           const updatedExam={
              ...exams.find((exam) => exam.examID === selectedExamId),
              rooms: [{roomName : assignmentData.room , capacity : assignmentData.capacity}],
           };
           const examUpdatedResponse = await fetch (`http://localhost:8081/exams/${selectedExamId}`,{
              method : "PUT",
              headers :{
                 "Content-Type" : "application/json",
              },
              body:JSON.stringify(updatedExam),
           });

           if(!examUpdatedResponse.ok){
                throw new Error("failed to assign student to exam");
           }
           console.log("Exam rooms update successfully for examid :" , selectedExamId);
           setAssignBtn(true);
            
        } catch (error) {
            console.log(error);
        }
        // const updatedExams = exams.map((exam) => {
        //     if (exam.examID === selectedExamId) {
        //         return {
        //             ...exam,
        //             assignedStudents: assignmentData.selectedStudents,
        //             room: assignmentData.room,
        //             capacity: assignmentData.capacity,
        //         };
        //     }
        //     return exam;
        // });
        // setExams(updatedExams);
        // setAssignmentModalOpen(false);
        setAssignmentModalOpen(false);
    };

    const updateDifficulty = (examId, newDifficulty) => {
        const updatedExam = exams.find((exam) => exam.examID === examId);
        if (!updatedExam) return;

        const examToUpdate = {
            ...updatedExam,
            difficulty: newDifficulty,
        };

        fetch(`http://localhost:8081/exams/${examId}`, {
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
                console.log("Difficulty update successful for exam ID:", examId);
                setEditBtn(true);
            })
            // .then((updatedExamFromServer) => {
            //     setExams(exams.map((exam) => exam.examID === examId ? updatedExamFromServer : exam));
            // })
            .catch((error) => {
                console.error("Update error:", error);
            });
    };

   

    console.log(filteredExams);

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
                            setCurrentPage(0);
                        }}
                    />
                    <select
                        value={statusFilter}
                        onChange={(e) => {
                            setStatusFilter(e.target.value);
                            setCurrentPage(0);
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
                        {/* <th>Exam ID</th> */}
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
                    {exams && exams.map((exam) => {
                        return (
                            <tr key={exam.examID}>
                                {/* <td>{exam.examID}</td> */}
                               <td>{`${exam.course.courseID} - ${exam.course.name}`}</td>
                                <td>{exam.date}</td>
                                <td>{exam.time}</td>
                                <td>{renderStars(exam.difficulty, exam.examID)}</td>
                                <td>{exam.status}</td>
                               <td>{exam.rooms[0]?.roomID !== undefined ? exam.rooms[0].roomID : "N/A"}</td>
                               <td>{exam.rooms[0]?.headcount !== undefined ? exam.rooms[0].headcount : "N/A"}</td>
                               <td>{exam.attendees.length}</td>
                                <td>
                                    <button
                                           className={`${styles.actionBtn} ${styles.edit}`}
                                          onClick={() => openEditModal(exam.examID)}
                                    >
                                        Edit
                                    </button>
                                    <button
                                          className={`${styles.actionBtn} ${styles.delete}`}
                                        onClick={() => deleteExam(exam.examID)} 
                                    >
                                        Delete
                                    </button>
                                    <button
                                       className={`${styles.actionBtn} ${styles.assign}`}
                                        onClick={() => openAssignmentModal(exam.examID)}
                                    >
                                        Assign Students
                                    </button>
                                </td>
                            </tr>
                        );
                    })}
                    </tbody>
                </table>
            </div>
            <div className={styles.pagination}>
                <button onClick={prevPage} disabled={currentPage === 0}>
                    Previous
                </button>
                <button onClick={nextPage} disabled={currentPage === totalPages - 1}>
                    Next
                </button>
            </div>
            {modalOpen && (
                <div className={styles.modal}>
                    <div className={styles.modalContent}>
                        <h2>{editingExam ? "Edit Exam" : "Add New Exam"}</h2>
                        <form onSubmit={handleFormSubmit}>
                            
                            {/* <input
                                type="text"
                                placeholder="Subject"
                                required
                                value={formData.courseID}
                                onChange={(e) =>
                                    setFormData({...formData,courseID: e.target.value})
                                }
                            /> */}
                            {/* <select
                                required
                               value={formData.courseID}
                               onChange={(e) => 
                                   handleCourseChange(e.target.value)
                               }
                            >
                               <option value="">Select a Course</option>
                               {courses.map((course) => (
                                  <option key={course.courseID} value={course.courseID}>
                                  {course.name} (ID: {course.courseID})
                                  </option>
                               ))}
                            </select>
                            <div>
                                <label>Course Name:</label>
                                <input
                                        type="text"
                                        value={formData.courseName}
                                        disabled
                                />
                            </div> */}
                            {editingExam ? (
                <>
                  <select
                    required
                    value={formData.courseID}
                    onChange={(e) => handleCourseChange(e.target.value)}
                  >
                    <option value="">Select a Course</option>
                    {courses.map((course) => (
                      <option key={course.courseID} value={course.courseID}>
                        {course.name} (ID: {course.courseID})
                      </option>
                    ))}
                  </select>
                  <div>
                    <label>Course Name:</label>
                    <input
                      type="text"
                      value={formData.courseName}
                      disabled
                    />
                  </div>
                </>
              ) : (
                <>
                  <div>
                    <label>Course ID:</label>
                    <input
                      type="text"
                      value={formData.courseID}
                      onChange={(e) => setFormData({ ...formData, courseID: e.target.value })}
                      required
                    />
                  </div>
                  <div>
                    <label>Course Name:</label>
                    <input
                      type="text"
                      value={formData.courseName}
                      onChange={(e) => setFormData({ ...formData, courseName: e.target.value })}
                      required
                    />
                  </div>
                </>
              )}
                            <input
                                type="date"
                                required
                                value={formData.date}
                                onChange={(e) =>
                                    setFormData({...formData, date: e.target.value})
                                }
                            />
                            <input
                                type="time"
                                required
                                value={formData.time}
                                onChange={(e) =>
                                    setFormData({...formData, time: e.target.value})
                                }
                            />
                            <div className={styles.difficultyContainer}>
                                <span className={styles.difficultyLabel}>Difficulty:</span>
                                <StarRating
                                    rating={formData.difficulty}
                                    onRatingChange={(value) =>
                                        setFormData({...formData, difficulty: value})
                                    }
                                />
                            </div>
                            <select
                                required
                                value={formData.status}
                                onChange={(e) =>
                                    setFormData({...formData, status: e.target.value})
                                }
                            >
                                <option value="Upcoming">Upcoming</option>
                                <option value="Completed">Completed</option>
                                <option value="Cancelled">Cancelled</option>
                            </select>
                            <input
                                type="text"
                                placeholder="Bag Code"
                               value={formData.bagCode}
                               onChange={(e) => 
                                      setFormData({ ...formData, bagCode: e.target.value })
                                }
                            />
                            <select
                               required
                               value={formData.typeID}
                               onChange={(e) => 
                                     setFormData({ ...formData, typeID: e.target.value })
                               }
                            >
                                <option value="">Select Exam Type</option>
                                {testTypes.map((type) => (
                                    <option key={type.typeID} value={type.typeID}>
                                     {type.name} (ID: {type.typeID})
                                      </option>
                                ))}
                            </select>
                            <div className={styles.modalButtons}>
                                 <button type="submit" disabled={isSubmitting}>
                                        {isSubmitting ? "Saving..." : "Save"}
                                 </button>
                                 <button type="button" onClick={closeModal} disabled={isSubmitting}>
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
                                    {students.length > 0 ? (
                                        students.map((student)=>(
                                            <div key = {student.personID}>
                                             <input
                                             type = "checkbox"
                                             id ={student.personID}
                                             value ={student.personID}
                                             checked ={assignmentData.selectedStudents.includes(student.personID)}
                                             onChange={(e) =>{
                                                 const value = e.target.value;
                                                 let updated;
                                                 if(e.target.checked){
                                                    updated = [...assignmentData.selectedStudents,value];
                                                 } else{
                                                    updated = assignmentData.selectedStudents.filter((s)=> s!== value);
                                                 }
                                              setAssignmentData({
                                                ...assignmentData,
                                                selectedStudents :updated
                                              });
                                             }}
                                        />
                                            <label htmlFor={student.personID}>
                                                {student.personID} - {student.firstName || "N/A"} {student.lastName || "N/A"}
                                            </label>
                                          </div>
                                        ))
                                      ) : (
                                        <div> No Students Available </div>
                                    )}
                                    {/* {availableStudents.map((student) => (
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
                                    ))} */}
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