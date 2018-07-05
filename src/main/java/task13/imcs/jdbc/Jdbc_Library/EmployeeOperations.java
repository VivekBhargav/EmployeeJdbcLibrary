package task13.imcs.jdbc.Jdbc_Library;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeOperations {
	
	public boolean addEmployee(Employee employee) throws SQLException;

	public String viewEmployee(int id) throws SQLException;

	public String viewAllEmployees() throws SQLException;

	public boolean updateEmployee(int id, Employee updateEmp) throws SQLException;

	public boolean deleteEmployee(int id) throws SQLException;

	public boolean isExists(int id) throws SQLException;

	public double getHRA(int hraId) throws SQLException;

	public double getGross(int grossID) throws SQLException;

	public String orderBy(String col) throws SQLException;
	
	public boolean writeToDB(List<Employee> emp) throws SQLException;

}
