package se.consid.applications.tidig.reportedtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.consid.applications.tidig.api.dto.ArticleDto;
import se.consid.applications.tidig.api.dto.CustomerDto;
import se.consid.applications.tidig.api.dto.ReportedTimeResultDto;
import se.consid.applications.tidig.customer.CustomerRepository;
import se.consid.applications.tidig.employee.Employee;
import se.consid.applications.tidig.employee.EmployeeService;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class ReportedTimeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportedTimeService.class);
    
    
    String timesql = "select rt.customer_id,rt.project_id,rt.activity_id, rt.emp_id,rt.reported_date,rt.reported_time,rt.time_row_id,rt.is_submitted,rt.description,c.customer_name,a.activity_name from databas.reportedtime rt join databas.customer c on rt.customer_id = c.customer_id left outer join  databas.activity a on rt.customer_id = a.customer_id AND rt.project_id = a.project_id AND rt.activity_id = a.activity_id ";
    
    

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CustomerRepository customerRepository;


    @Autowired
    DataSource dataSource;


    public List<ReportedTimeResultDto> get(String apiUser, String empId, LocalDate fromDate, LocalDate toDate, Long customerId, String customerName, Long projectId, String projectName) {

        List<Employee> myEmployees = employeeService.getApiEmployeeTimePermission(apiUser);
        Set<String> allowedToSeeEmployees = toIdStrings(myEmployees);
        allowedToSeeEmployees.add(apiUser); // myself

        //String sql = "select rt.customer_id ,rt.project_id,rt.activity_id, rt.emp_id,rt.reported_date,rt.reported_time,rt.time_row_id,rt.is_submitted,rt.description from databas.reportedtime rt ";
        String sql = timesql;
        String whereClause = buildWhereClause(empId, fromDate, toDate, customerId, customerName, projectId, projectName, allowedToSeeEmployees);
        sql += whereClause;
        LOGGER.info(sql);

        List<ReportedTimeResultDto> resultList = new ArrayList<>();


        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            bindParameters(stmt, empId, fromDate, toDate, customerId, customerName, projectId, projectName);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                ReportedTimeResultDto line = new ReportedTimeResultDto();

                Long rs_CustomerId = resultSet.getLong("customer_id");
                Long rs_ProjectId = resultSet.getLong("project_id");
                Long rs_ActivityId = resultSet.getLong("activity_id");
                String rs_EmpId = resultSet.getString("emp_id");
                Timestamp rs_ReportedDate = resultSet.getTimestamp("reported_date");
                Double rs_ReportedTime = resultSet.getDouble("reported_time");
                Long rs_RowId = resultSet.getLong("time_row_id");
                Boolean rs_Isubmitted = resultSet.getBoolean("is_submitted");
                String rs_Description = resultSet.getString("description");
                String rs_customerName = resultSet.getString("customer_name");
                String rs_articleName = resultSet.getString("activity_name");

                LocalDateTime reportedDate = rs_ReportedDate.toLocalDateTime();

                CustomerDto customer = new CustomerDto();
                customer.setCustomerId(rs_CustomerId);
                customer.setName(rs_customerName);
                line.setCustomer(customer);

                ArticleDto article = new ArticleDto();
                article.setArticleId(rs_ActivityId);
                article.setName(rs_articleName);


                line.setProject(rs_ProjectId);
                line.setActivity(rs_ActivityId);
                line.setEmpId(rs_EmpId);
                line.setDate(reportedDate);
                line.setHours(rs_ReportedTime);
                line.setTimeRowId(rs_RowId);
                line.setIsSubmitted(rs_Isubmitted);
                line.setDescription(rs_Description);
                line.setArticle(article);
                line.setCaseNumber(rs_RowId * 42);  // que ???


                resultList.add(line);
            }

            resultSet.close();

        } catch (SQLException sqle) {
            LOGGER.error(sql);
            LOGGER.error(sqle.toString(), sqle);
        }


        return resultList;

    }



    private String buildWhereClause(String empId, LocalDate fromDate, LocalDate toDate, Long customerId, String customerName, Long projectId, String projectName, Set<String> allowedToSeeEmployees) {
        String ret = " where ";

        // AND MUST be blank first, since we dont know where it is going to be more than one in the selection
        String AND = "";

        if (empId != null) {
            ret += " emp_id = ? ";
            AND = " and ";
        }
        if (customerId != null) {
            ret += AND;
            ret += " customer_id = ? ";
            AND = " and ";
        }
        if (projectId != null) {
            ret += AND;
            ret += " project_id = ? ";
            AND = " and ";
        }
        if (fromDate != null) {
            ret += AND;
            ret += " reported_date >= ? ";
            AND = " and ";
        }
        if (toDate != null) {
            ret += AND;
            ret += " reported_date < ? ";
            AND = " and ";
        }
        String inClause = createInClause(allowedToSeeEmployees);
        if ((empId != null) || (customerId != null) || (projectId != null) || (fromDate != null) || (toDate != null)) {
            ret += " and ";
        }
        ret += " emp_id in " + inClause;

        // allowedToSeeEmployees are always at least 1  = myself
        return ret;
    }

    // OBS bindParameter and buildWhereClause  MUST be exacly in sync  otherwise selcetiondata is plced on wrong places
    private void bindParameters(PreparedStatement stmt, String empId, LocalDate fromDate, LocalDate toDate, Long customerId, String customerName, Long projectId, String projectName) throws SQLException {

        int parameterIndex = 0;  // we dont no in advance where to put data so it must be a counter

        if (empId != null) {
            parameterIndex = parameterIndex + 1;
            stmt.setString(parameterIndex, empId);
        }
        if (customerId != null) {
            parameterIndex = parameterIndex + 1;
            stmt.setLong(parameterIndex, customerId);
        }
        if (projectId != null) {
            parameterIndex = parameterIndex + 1;
            stmt.setLong(parameterIndex, projectId);
        }
        if (fromDate != null) {
            parameterIndex = parameterIndex + 1;
            stmt.setObject(parameterIndex, fromDate);
        }
        if (toDate != null) {
            parameterIndex = parameterIndex + 1;
            stmt.setObject(parameterIndex, toDate);
        }
    }

    private String createInClause(Set<String> allowedToSeeEmployees) {
        // can never be null always at least myself
        String ret = "(";
        for (String empId : allowedToSeeEmployees) {
            ret += "'";
            ret += empId;
            ret += "'";
            ret += ",";
        }
        ret = ret.substring(0, ret.length() - 1);  // remove last ,  (will always be there)
        ret += (")");
        return ret;
    }


    private Set<String> toIdStrings(List<Employee> myEmployees) {
        Set<String> ret = new HashSet<>();
        for (Employee employee : myEmployees) {
            String id = employee.getEmpId();
            ret.add(id);
        }
        return ret;
    }
}
