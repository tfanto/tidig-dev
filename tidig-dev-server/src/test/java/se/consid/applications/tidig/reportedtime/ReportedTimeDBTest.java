package se.consid.applications.tidig.reportedtime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.consid.applications.tidig.activity.Activity;
import se.consid.applications.tidig.activity.ActivityKey;
import se.consid.applications.tidig.activity.ActivityRepository;
import se.consid.applications.tidig.customer.Customer;
import se.consid.applications.tidig.customer.CustomerRepository;
import se.consid.applications.tidig.employee.Employee;
import se.consid.applications.tidig.employee.EmployeeRepository;
import se.consid.applications.tidig.project.Project;
import se.consid.applications.tidig.project.ProjectKey;
import se.consid.applications.tidig.project.ProjectRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@SpringBootTest
public class ReportedTimeDBTest {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ReportedTimeRepository reportedTimeRepository;

    Long customerid = 9995l;
    Long projectid = 9994l;
    Long activityid =  3l;
    String employeeid = "reportedTimeEmpid";

    @Test
    public void verifyRepositoryWorks() {

        // prereq MUST exist
        Employee employeeObject = new Employee();
        employeeObject.setEmpId(employeeid);
        employeeObject.setFirstName("Karl");
        employeeObject.setSurName("Phantaux");
        employeeRepository.save(employeeObject);

        // prereq MUST exist
        Customer customerObject = new Customer();
        customerObject.setId(customerid);
        customerObject.setName("ActivityCustomerName");
        customerRepository.save(customerObject);

        // prereq must exist
        ProjectKey projectKey = new ProjectKey(customerid,projectid);
        Project object = new Project(projectKey);
        object.setName("ActivityProjectName");
        projectRepository.save(object);


        // prereq must exist
        ActivityKey activityKey = new ActivityKey(customerid,projectid,activityid);
        Activity activityObject = new Activity(activityKey);
        activityObject.setName("activityName");
        activityRepository.save(activityObject);

        // the "test"
        LocalDate reportedDate = LocalDate.of(2021,Month.MAY, 24);
        ReportedTimeKey reportedTimeKey = new ReportedTimeKey(customerid,projectid,activityid,employeeid,reportedDate );
        ReportedTime reportedTime = new ReportedTime(reportedTimeKey);
        reportedTime.setReportedTime(8f);
        reportedTime.setTime_row_id(23000l);
        reportedTime.setIs_submitted(true);
        reportedTime.setDescription("No comment");

        ReportedTime entity = reportedTimeRepository.save(reportedTime);

        reportedTimeRepository.delete(entity);


    }




}
