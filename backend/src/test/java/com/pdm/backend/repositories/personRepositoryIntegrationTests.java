
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
import com.pdm.backend.models.Person;
import com.pdm.backend.repositoriess.CourseRepository;
import com.pdm.backend.repositoriess.PersonRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext (classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class personRepositoryIntegrationTests{
    private PersonRepository underTest;

    public personRepositoryIntegrationTests(PersonRepository underTest){
        this.underTest = underTest;
    }
     
    @Test
    public void TestThatPersonCanBeCreatedAndRecalled(){
        
        Person person = TestDataUtil.createTestPersonA();
       
        underTest.save(person);
        Optional<Person> result = underTest.findById(person.getPersonID());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(Person);
 
    }

    @Test
    public void TestThatMultiplePersonCanBeCalledAndCreated(){
        Person person1 = TestDataUtil.createTestPersonA();
        underTest.save(person1);
        Person person2 = TestDataUtil.createTestPersonB();
        underTest.save(person2);
        Person person3 = TestDataUtil.createTestPersonC();
        underTest.save(person3);

        Iterable<Person> result = underTest.findAll();
        assertThat(result)
        .hasSize(3)
        .containsExactly(person1 , person2, person3);



    }
    
    @Test
    public void TestThatPersonCanBeDeleted(){
        Person person = TestDataUtil.createTestPersonA();
        underTest.save(person);
        underTest.deleteById(person.getPersonID());
        Optional<Person> result = underTest.findById(person.getPersonID());
        assertThat(result).isEmpty();
        
    }

    @Test 
    public void TestThatWhatAgeIsGreaterThan(){
        Person personA = TestDataUtil.createTestPersonA();
        underTest.save(personA);
        Person personB = TestDataUtil.createTestPersonB();
        underTest.save(personB);
        Person personC = TestDataUtil.createTestPersonC();
        underTest.save(personC);

        Iterable<Person> result = underTest.FindAgeLess(21);
        assertThat(result).containsExactly(personB , personC );

    }

    @Test
    public void TestThatPersonIsTheOldestAge(){
        
        Person personA = TestDataUtil.createTestPersonA();
        underTest.save(personA);
        Person personB = TestDataUtil.createTestPersonB();
        underTest.save(personB);
        Person personC = TestDataUtil.createTestPersonC();
        underTest.save(personC);

        Iterable<Person> result = underTest.FindTheOldestAge(21);
        assertThat(result).containsExactly(personA);
    }

}