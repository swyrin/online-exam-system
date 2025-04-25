import React from "react";
import styles from "./FutureOfLearning.module.css";

const FutureOfLearning = () => {
  return (
    <div className={styles.container}>
      <section className={styles.hero}>
        <div className={styles.overlay}>
          <h1>Welcome to the Future of Learning</h1>
          <p>Explore innovative ways to educate and engage.</p>
          <button className={styles.cta}>Get Started</button>
        </div>
      </section>

      <section className={styles.features}>
        <div className={styles.feature}>
          <h2>Interactive Courses</h2>
          <p>Experience courses designed with you in mind.</p>
        </div>
        <div className={styles.feature}>
          <h2>Expert Mentorship</h2>
          <p>Learn from industry leaders with deep insights.</p>
        </div>
        <div className={styles.feature}>
          <h2>Flexible Learning</h2>
          <p>Study at your own pace with efficient learning techniques.</p>
        </div>
      </section>

      <section className={styles.callToAction}>
        <h2>Ready to change the way you learn?</h2>
        <button className={styles.ctaLarge}>Join Now</button>
      </section>

      <footer className={styles.footer}>
        <p>
          &copy; {new Date().getFullYear()} Future of Learning. All Rights
          Reserved.
        </p>
      </footer>
    </div>
  );
};

export default FutureOfLearning;
