package se.consid.applications.tidig.activity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.consid.applications.tidig.customer.Customer;
import se.consid.applications.tidig.customer.CustomerRepository;
import se.consid.applications.tidig.project.Project;
import se.consid.applications.tidig.project.ProjectKey;
import se.consid.applications.tidig.project.ProjectRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ActivityDBTest {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ActivityRepository activityRepository;

    Long customerid = 9999l;
    Long projectid = 9999l;
    Long activityid = 10l;

    @Test
    public void verifyRepositoryWorks() {

        // prereq MUST exist
        Customer customerObject = new Customer();
        customerObject.setId(customerid);
        customerObject.setName("ActivityCustomerName");
        customerRepository.save(customerObject);

        // prereq MUST exist
        ProjectKey projectKey = new ProjectKey();
        projectKey.setCustomerId(customerid);
        projectKey.setProjectId(projectid);
        Project object = new Project(projectKey);
        object.setName("ActivityProjectName");
        projectRepository.save(object);


        // verify
        ActivityKey activityKey = new ActivityKey(customerid, projectid, activityid);
        Activity activityObject = new Activity(activityKey);
        activityObject.setName("activityName");
        Activity ret = activityRepository.save(activityObject);
        assertNotNull(ret);


        // cleanup
        activityRepository.deleteById(activityKey);
        projectRepository.deleteById(projectKey);
        customerRepository.deleteById(customerid);
    }
}

