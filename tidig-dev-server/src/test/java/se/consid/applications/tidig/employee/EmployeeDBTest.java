package se.consid.applications.tidig.employee;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeDBTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void verifyRepositoryWorks() {

        Employee object = new Employee();
        object.setEmpId("TFO");
        object.setFirstName("Thomas");
        object.setSurName("Johansson");
        employeeRepository.save(object);
        Optional<Employee> fetched = employeeRepository.findById("TFO");
        Employee fetchedEmployee = fetched.get();
        assertNotNull(fetchedEmployee);
        Boolean exists = employeeRepository.existsById(object.getEmpId());
        assertTrue(exists);
        employeeRepository.delete(object);
        exists = employeeRepository.existsById(object.getEmpId());
        assertFalse(exists);
    }


}
