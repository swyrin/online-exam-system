import React, { useState, useEffect } from "react";
import styles from "./ExamSchedule.module.css";

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
  const initialExams = () => {
    const storedExams = localStorage.getItem("exams");
    return storedExams
      ? JSON.parse(storedExams)
      : [
          {
            id: "1",
            subject: "Mathematics",
            date: "2025-05-01",
            time: "09:00",
            status: "Upcoming",
            difficulty: 3,
          },
          {
            id: "2",
            subject: "Physics",
            date: "2025-05-03",
            time: "14:00",
            status: "Upcoming",
            difficulty: 4,
          },
          {
            id: "3",
            subject: "Chemistry",
            date: "2025-04-28",
            time: "11:00",
            status: "Completed",
            difficulty: 2,
          },
        ];
  };

  const [exams, setExams] = useState(initialExams());
  const [currentPage, setCurrentPage] = useState(1);
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

  const itemsPerPage = 5;

  useEffect(() => {
    localStorage.setItem("exams", JSON.stringify(exams));
  }, [exams]);

  const filteredExams = exams.filter((exam) => {
    const matchesSearch =
      exam.subject.toLowerCase().includes(searchInput.toLowerCase()) ||
      exam.date.includes(searchInput);
    const matchesStatus = statusFilter ? exam.status === statusFilter : true;
    return matchesSearch && matchesStatus;
  });

  const totalPages = Math.ceil(filteredExams.length / itemsPerPage);
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
    if (window.confirm("Are you sure you want to delete this exam?")) {
      setExams(exams.filter((exam) => exam.id !== id));
    }
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
    };
    if (editingExam) {
      setExams(exams.map((exam) => (exam.id === editingExam ? newExam : exam)));
    } else {
      setExams([...exams, newExam]);
    }
    setModalOpen(false);
  };

  const renderStars = (rating) => {
    return (
      <div className={styles.starRatingDisplay}>
        {[1, 2, 3, 4, 5].map((star) => (
          <span
            key={star}
            className={star <= rating ? styles.starActive : styles.starInactive}
          >
            ★
          </span>
        ))}
      </div>
    );
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
                <td>{renderStars(exam.difficulty)}</td>
                <td>
                  <span
                    className={`${styles.status} ${
                      styles[exam.status.toLowerCase()]
                    }`}
                  >
                    {exam.status}
                  </span>
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
    </div>
  );
};

export default ExamSchedule;
