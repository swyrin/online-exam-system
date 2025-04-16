package com.pdm.backend.repositories;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;
import com.pdm.backend.TestDataUtil;
import com.pdm.backend.models.Course;
import com.pdm.backend.repositoriess.CourseRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext (classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)


public class courseRepositoryIntegrationTest {
   

    private CourseRepository underTest;

    @Autowired
    public courseRepositoryIntegrationTest(CourseRepository underTest){
        this.underTest = underTest;
    }

     @Test
    public void testThatCourseCanBeCreatedAndRecalled() {
        Course course  = TestDataUtil.createTestCourseA();
        underTest.save(course);
       Optional<Course> result = underTest.findById(course.getCourseID());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(course);
    }

    @Test
    public void testThatMultipleCourseCanBeCreatedAndRecalled() {
        Course courseEntityA = TestDataUtil.createTestCourseA();
        underTest.save(courseEntityA);
        Course CourseEntityB = TestDataUtil.createTestCourseB();
        underTest.save(CourseEntityB);
        Course courseEntityC = TestDataUtil.createTestCourseC();
        underTest.save(courseEntityC);

        Iterable<Course> result = underTest.findAll();
        assertThat(result)
                .hasSize(3).
                containsExactly(courseEntityA, CourseEntityB, courseEntityC);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Course courseEntityA = TestDataUtil.createTestCourseA();
        underTest.save(courseEntityA);
        courseEntityA.setName("UPDATED");
        underTest.update(courseEntityA);
        Optional<Course> result = underTest.findById(courseEntityA.getCourseID());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(courseEntityA);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
      Course  courseEntityA = TestDataUtil.createTestCourseA();
        underTest.save(courseEntityA);
        underTest.deleteById(courseEntityA.getCourseID());
        Optional<Course> result = underTest.findById(courseEntityA.getCourseID());
        assertThat(result).isEmpty();
    }


 }
