package se.consid.applications.tidig.createtimereports;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class SkapaTestData {

	private static final Integer GENERATE_FOR_NUMBER_OF_DAYS = 32;


	String projectNames[] = { "Förstudie", "Datamodellering", "Intranet", "Funktionstest", "Byta maskinpark",
			"Programmering", "Kundutbildning", "Programmeringsutbildning", "Analys av nul�ge", "Försäljning",
			"Eftermarknad" };

	Long activityIds[] = { 1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l, 9l,
			10l };
	String activityNames[] = { "Design", "Programmering", "Test", "Kravarbete", "Dokumentation", "Arkitektarbete",
			"Kund internkod 1", "Kund internkod 2", "Kund internkod 3", "Kund internkod 4" };

	List<String> allEmployees = new ArrayList<>();

	Random rnd = new Random();

	Boolean isLeap(int year) {

		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0)
					return true;
				else
					return false;
			} else
				return true;
		} else {
			return false;
		}
	}

	List<String> getEmployees(Connection conn) throws SQLException {

		List<String> ret = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement("select emp_id  from databas.employee;");

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String empid = rs.getString(1);
			ret.add(empid);
		}
		Db.close(rs);
		Db.close(stmt);

		return ret;
	}


	public void execute() {

		Connection conn = null;
		try {
			conn = Db.open();

			List<String> list = getEmployees(conn);
			allEmployees.addAll(list);

			// THIS IS in setup.sql  createProjectsForAllCustomers(conn);
			// THIS IS in setup.sq   createActivitiesForAllCustomers(conn);
			// THIS IS in setup.sql  createCrossrefApiKeyToEmp_id(conn);

			createTimeReports(conn);


		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			Db.close(conn);
		}

	}

	private void createCrossrefApiKeyToEmp_id(Connection conn) throws SQLException{

		List<String>  empList= getEmployees(conn);

		PreparedStatement stmt = conn.prepareStatement(
				"insert into databas.apikey2empid (api_key, emp_id) values (?,?);");

		for(String emp_id : empList){
			String apikey = UUID.randomUUID().toString();
			stmt.setString(1, apikey);
			stmt.setString(2, emp_id);
			stmt.executeUpdate();

			String theSql = String.format("insert into databas.apikey2empid (api_key, emp_id) values ('%s','%s');",apikey,emp_id);
			System.out.println(theSql);

		}

		Db.close(stmt);;









	}

	private void createProjectsForAllCustomers(Connection conn) throws Exception {

		PreparedStatement stmt = conn.prepareStatement(
				"insert into databas.project (customer_id, project_id, project_name) values (?,?,?);");

		for (int customerId = 1; customerId <= 25; customerId++) {
			if (customerId != 2) {

				List<String> pNames = Arrays.asList(projectNames);

				int projectId = 1;
				for (String projectName : pNames) {
					stmt.setInt(1, customerId);
					stmt.setInt(2, projectId);
					stmt.setString(3, projectName);
					stmt.executeUpdate();
					/*
					String sqlString = String.format(
							"insert into databas.project (customer_id, project_id, project_name) values (%d,%d,'%s');\n",
							customerId, projectId, projectName);
					projectId = projectId + 1;
					Files.writeString(Path.of("C:/tmp", "fil_projects.sql"), sqlString, StandardOpenOption.CREATE,
							StandardOpenOption.APPEND);
					 */
				}
			}
		}

		Db.close(stmt);
	}

	private void createActivitiesForAllCustomers(Connection conn) throws SQLException, IOException {

		PreparedStatement stmt = conn.prepareStatement(
				"insert into databas.activity (customer_id, project_id, activity_id, activity_name) values (?,?, ?, ?);");

		for (int customerId = 1; customerId <= 25; customerId++) {

			if (customerId != 2) {

				List<String> pNames = Arrays.asList(projectNames);

				int projectId = 1;
				for (String projectName : pNames) {

					for (int activityPointer = 0; activityPointer < activityIds.length; activityPointer++) {

						Long activityId = activityIds[activityPointer];
						String activityName = activityNames[activityPointer];

						stmt.setInt(1, customerId);
						stmt.setInt(2, projectId);
						stmt.setLong(3, activityId);
						stmt.setString(4, activityName);
						 stmt.executeUpdate();

				/*		String sqlStr = String.format(
								"insert into databas.activity (customer_id, project_id, activity_id, activity_name) values (%d,%d,%d,'%s');\n",
								customerId, projectId, activityId, activityName);
						Files.writeString(Path.of("C:/tmp", "fil_activities_ny.sql"), sqlStr, StandardOpenOption.CREATE,
								StandardOpenOption.APPEND);

				 */


					}
					projectId = projectId + 1;
				}
			}
		}
		Db.close(stmt);
	}


	private void createTimeReports(Connection conn) throws SQLException, IOException {

		PreparedStatement stmt = conn.prepareStatement(
				"insert into databas.reportedtime (customer_id, project_id, activity_id, emp_id, reported_date,reported_time,time_row_id,is_submitted,description) values (?,?,?,?,?,?,?,?,? );");

		Stack<String> empStack = new Stack<>();
		empStack.addAll(allEmployees);

		Integer radnummer = 0;

		for (int customerId = 1; customerId <= 25; customerId++) {

			if (customerId != 2) {

				List<String> pNames = Arrays.asList(projectNames);

				int projectId = 1;
				for (String projectName : pNames) { // iterate but not used

					int maxactivitiesForThisProject = rnd.nextInt(activityIds.length);

					for (int activityPointer = 0; activityPointer < maxactivitiesForThisProject; activityPointer++) {

						int year = 2018;
						int month = 1;
						int day = 1;

						for (int i = 1; i < GENERATE_FOR_NUMBER_OF_DAYS ; i++) {

							if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
									|| month == 12) {

								if (day > 31) {
									day = 1;
									month = month + 1;
								}

							} else if (month == 2) {

								if (isLeap(year)) {
									if (day > 29) {
										day = 1;
										month = month + 1;
									}
								} else {
									if (day > 28) {
										day = 1;
										month = month + 1;
									}
								}
							} else {
								if (day > 30) {
									day = 1;
									month = month + 1;
								}
							}

							if (month > 12) {
								day = 1;
								month = 1;
								year = year + 1;
							}



							String reportedDate = String.format("%04d-%02d-%02d 02:00:00", year, month, day);
							day = day + 1;
							Timestamp ts = Timestamp.valueOf(reportedDate);

							Long activityId = activityIds[activityPointer];
							float reportedTime = rnd.nextInt(8) + 1;

							if (empStack.isEmpty()) {
								empStack.addAll(allEmployees);
							}
							String empId = empStack.pop();

							stmt.setInt(1, customerId);
							stmt.setInt(2, projectId);
							stmt.setLong(3, activityId);
							stmt.setString(4, empId);
							stmt.setTimestamp(5, ts);
							stmt.setFloat(6, reportedTime / maxactivitiesForThisProject);

							radnummer = radnummer + 1;
							stmt.setInt(7, radnummer);

							stmt.setBoolean(8, false);
							stmt.setString(9, "No Comment");


							stmt.executeUpdate();
						/*
							String rt = String.valueOf(reportedTime);
							rt = rt.replace(',', '.');

							String sqlStr = String.format(

									"insert into databas.reportedtime (customer_id, project_id, activity_id, emp_id, reported_date,reported_time) values (%d,%d,'%s','%s','%s',%s );\n",
									customerId, projectId, activityId, empId, reportedDate, rt);
							Files.writeString(Path.of("C:/tmp", "fil_reportedtime.sql"), sqlStr,
									StandardOpenOption.CREATE, StandardOpenOption.APPEND);

						 */

						}

					}
					projectId = projectId + 1;
				}
			}
		}
		Db.close(stmt);
	}

	public static void main(String[] args) {

		Db.setupDatabasePool();
		SkapaTestData obj = new SkapaTestData();
		obj.execute();
		Db.stopDatabasePool();

	}

}
