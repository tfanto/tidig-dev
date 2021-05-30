package se.consid.applications.tidig.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.consid.applications.tidig.api.dto.EmployeeListSimpleDto;
import se.consid.applications.tidig.api.dto.ReportedTimeResultDto;
import se.consid.applications.tidig.employee.Employee;
import se.consid.applications.tidig.employee.EmployeeService;
import se.consid.applications.tidig.employee.EmployeeTree;
import se.consid.applications.tidig.error.InvalidInputException;
import se.consid.applications.tidig.reportedtime.ReportedTimeService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "Api")
public class EmployeeApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeApi.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ReportedTimeService reportedTimeService;


    /**
     * Observera att du kan endast hämta din egna tid samt tid som du har behörighet till via projekttidsfliken. Om alla parametrar förutom fromDate och toDate utesluts hämtas din egna tid.
     * Exempel
     * GET https://tidig.consid.net/Api/Time?empId=TFO&fromDate=2021-05-01&toDate=2021-06-01
     *
     * @param empId        - Det interna ID:t, vanligtvis tre tecken långt, för den anställde. T.ex. ditt egna ID är TFO.
     * @param fromDate     - Inklusivt startdatum i format YYYY-MM-DD. T.ex. 2021-05-01
     * @param toDate       - Exklusivt slutdatum i format YYYY-MM-DD. T.ex. 2021-06-01
     * @param customerId   - Tidigs interna ID på kund. T.ex. 2 för Consid.
     * @param customerName - Namn på kund. T.ex. Consid
     * @param projectId-   Tidigs interna ID på projekt. T.ex. 304 för Consid interntid.
     * @param projectName  - Namn på projekt. T.ex. internt (beskriv)
     * @return
     */
    @GetMapping(
            value = "/Time",
            produces = "application/json")
    List<ReportedTimeResultDto> get(@RequestHeader(value = "X-ApiKey", required = false) String apiKeyfromHeader,
                           @RequestParam(value = "apiKey", required = false) String apiKeyfromParam,
                           @RequestParam(value = "empId", required = false) String empId,
                           @RequestParam(value = "fromDate", required = false) LocalDate fromDate,
                           @RequestParam(value = "toDate", required = false) LocalDate toDate,
                           @RequestParam(value = "customerId", required = false) Long customerId,
                           @RequestParam(value = "customerName", required = false) String customerName,
                           @RequestParam(value = "projectId", required = false) Long projectId,
                           @RequestParam(value = "projectName", required = false) String projectName) {

        String apiUser = getApiUser(apiKeyfromHeader, apiKeyfromParam);

        List<ReportedTimeResultDto> ret =  reportedTimeService.get(apiUser, empId,fromDate,toDate,customerId,customerName,projectId,projectName);
                return ret;
    }




    /**
     * Hämta ID och namn på användare som du har tillgång att se tid för.
     * Exempel
     * GET https://tidig.consid.net/Api/Employee/TimePermission
     *
     * @return
     */
    @GetMapping(
            value = "/Employee/TimePermission",
            produces = "application/json")
    EmployeeListSimpleDto  getApiEmployeeTimePermission(@RequestHeader(value = "X-ApiKey", required = false) String apiKeyfromHeader,
                                                           @RequestParam(value = "apiKey", required = false) String apiKeyfromParam) {
        String apiUser = getApiUser(apiKeyfromHeader, apiKeyfromParam);

        List<Employee> list = employeeService.getApiEmployeeTimePermission( apiUser);

        // convert to tidig result format
        EmployeeListSimpleDto ret = new EmployeeListSimpleDto();
        for(Employee e : list){
            ret.addEmployees(e);
        }

        return ret;
    }

    /**
     * Hämta hierarkin för dina underanställda.
     * Exempel
     * GET https://tidig.consid.net/Api/Employee/SubTree
     *
     * @return
     */
    @GetMapping(
            value = "/Employee/SubTree",
            produces = "application/json")

    EmployeeTree getEmployeeSubtree(@RequestHeader(value = "X-ApiKey", required = false) String apiKeyfromHeader,
                                           @RequestParam(value = "apiKey", required = false) String apiKeyfromParam) throws Exception{

        String apiUser = getApiUser(apiKeyfromHeader, apiKeyfromParam);

        EmployeeTree tree = employeeService.buildTree(apiUser);
        return tree;
    }
    
    
	private String getApiUser(String apiKeyfromHeader, String apiKeyfromParam) {
		String apiKey = apiKeyfromHeader;
        if (apiKey == null) {
            apiKey = apiKeyfromParam;
        }
        if (apiKey == null) {
        	String msg = "Not authorized";
            LOGGER.info(msg);
            throw new InvalidInputException(msg);
        }
        LOGGER.info("apiKey : " + apiKey);
        String apiUser = employeeService.getEmpNoFromApiKey(apiKey);
        if(apiUser == null){
        	String msg = "Invalid user";
            LOGGER.info(msg);
            throw new InvalidInputException(msg);
        }
        LOGGER.info("apiUser : " + apiUser);
		return apiUser;
	}



}
