package task13.imcs.jdbc.Jdbc_Library;

import java.io.IOException;
import java.sql.SQLException;

public interface EmployeeServices {

	public boolean addEmployee(Employee employee) throws SQLException;

	public String viewEmployee(int id) throws SQLException;

	public String viewAllEmployees() throws SQLException;

	public boolean updateEmployee(int id, Employee updateEmp) throws SQLException;

	public boolean deleteEmployee(int id) throws SQLException;

	public boolean isExists(int id) throws SQLException;

	public double getHRASrvc(int hraId) throws SQLException;

	public double getGrossSrvc(int grossID) throws SQLException;

	public String orderBy(String col) throws SQLException;

	public boolean fileOperation(String fLoc) throws IOException, SQLException;

}
