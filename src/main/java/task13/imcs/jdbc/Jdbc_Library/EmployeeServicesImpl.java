package task13.imcs.jdbc.Jdbc_Library;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServicesImpl implements EmployeeServices {
	EmployeeOperations empArrayDao = new EmployeeOperationsImpl();

	public boolean addEmployee(Employee employee) throws SQLException {
		return empArrayDao.addEmployee(employee);
	}

	public boolean isExists(int id) throws SQLException {
		return empArrayDao.isExists(id);
	}

	public String viewEmployee(int id) throws SQLException {
		return empArrayDao.viewEmployee(id);
	}

	public String viewAllEmployees() throws SQLException {
		return empArrayDao.viewAllEmployees();
	}

	public boolean updateEmployee(int id, Employee updateEmp) throws SQLException {
		return empArrayDao.updateEmployee(id, updateEmp);
	}

	public boolean deleteEmployee(int id) throws SQLException {
		return empArrayDao.deleteEmployee(id);
	}

	public double getHRASrvc(int hraId) throws SQLException {
		return empArrayDao.getHRA(hraId);
	}

	public double getGrossSrvc(int grossID) throws SQLException {
		return empArrayDao.getGross(grossID);
	}

	public String orderBy(String col) throws SQLException {
		return empArrayDao.orderBy(col);
	}

	@Override
	public boolean fileOperation(String fLoc) throws IOException, SQLException {
		List<Employee> emp = new ArrayList<>();
		boolean status = false;
		File file = new File(fLoc);
		if (file.exists()) {
			emp = EmployeeUtil.readFromFile(file);
		}
		
		status = empArrayDao.writeToDB(emp);
		return status;
	}

}
