package se.consid.applications.tidig.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.consid.applications.tidig.customer.Customer;
import se.consid.applications.tidig.customer.CustomerRepository;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProjectDBTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    Long customerid = 9998l;
    Long projectid = 9998l;

    @Test
    public void verifyRepositoryWorks() {

        // prereq MUST exist
        Customer customerObject = new Customer();
        customerObject.setId(customerid);
        customerObject.setName("projcustomerName");
        customerRepository.save(customerObject);

        // the test
        ProjectKey key = new ProjectKey();
        key.setCustomerId(customerid);
        key.setProjectId(projectid);
        Project object = new Project(key);
        object.setName("ProjectName");
        projectRepository.save(object);
        Boolean exists = projectRepository.existsById(key);
        assertTrue(exists);
        projectRepository.deleteById(key);
        exists = projectRepository.existsById(key);
        assertFalse(exists);

        customerRepository.deleteById(customerid);


    }


}
