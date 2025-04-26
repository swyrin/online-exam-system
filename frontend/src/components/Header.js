import React from "react";
import { Link } from "react-router-dom";
import styles from "./Header.module.css";

const Header = () => {
  return (
    <header className={styles.header}>
      <div className={styles.logo}>Future of Learning</div>
      <nav className={styles.nav}>
        <Link to="/">Home</Link>
        <Link to="/exam-schedule">Exam Schedule</Link>
        <Link to="/student-list">Student List</Link>
      </nav>
    </header>
  );
};

export default Header;
