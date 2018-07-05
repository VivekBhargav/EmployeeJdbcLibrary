package task13.imcs.jdbc.Jdbc_Library;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeOperationsImpl extends ConnectionJDBC implements EmployeeOperations {

	public boolean writeToDB(List<Employee> emp) throws SQLException {
		boolean status = false;
		for (Employee e : emp) {
			status = addEmployee(e);
		}
		return status;
	}

	@Override
	public boolean addEmployee(Employee emp) throws SQLException {
		boolean status = false;
		int check = 0;
		try (PreparedStatement pst = getConnection()
				.prepareStatement("insert into employee (id, name, salary, age, gender) values (?,?,?,?,?)");) {
			pst.setInt(1, emp.getId());
			pst.setString(2, emp.getName());
			pst.setFloat(3, emp.getSalary());
			pst.setInt(4, emp.getAge());
			pst.setString(5, String.valueOf(emp.getGender()));
			check = pst.executeUpdate();
		} finally {
			if (check > 0) {
				status = true;
			}
		}
		return status;
	}

	@Override
	public String viewEmployee(int id) throws SQLException {
		String result = null;
		ResultSet rs = null;
		try (PreparedStatement pst = getConnection()
				.prepareStatement("select id, name, salary, age, gender from employee where id = ?");) {
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getFloat(3) + " " + rs.getInt(4) + " "
						+ rs.getString(5);
			}
		} finally {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
		return result;
	}

	@Override
	public String viewAllEmployees() throws SQLException {
		String result = null;
		ResultSet rs = null;
		try (PreparedStatement pst = getConnection()
				.prepareStatement("select id, name, salary, age, gender from employee");) {
			rs = pst.executeQuery();
			if (rs.next()) {
				result = "Employees info: \n";
				do {
					result = result + rs.getInt(1) + " " + rs.getString(2) + " " + rs.getFloat(3) + " " + rs.getInt(4)
							+ " " + rs.getString(5) + "\n";
				} while (rs.next());
			}

		} finally {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
		return result;
	}

	@Override
	public boolean updateEmployee(int id, Employee updateEmp) throws SQLException {
		int check = 0;
		boolean status = false;
		try (PreparedStatement pst = getConnection()
				.prepareStatement("update employee set name = ?, salary = ? , age = ?, gender = ? where id = ?");) {
			pst.setString(1, updateEmp.getName());
			pst.setFloat(2, updateEmp.getSalary());
			pst.setInt(3, updateEmp.getAge());
			pst.setString(4, String.valueOf(updateEmp.getGender()));
			pst.setInt(5, id);

			check = pst.executeUpdate();
		} finally {
			if (check > 0) {
				status = true;
			}
		}
		return status;
	}

	@Override
	public boolean deleteEmployee(int id) throws SQLException {
		int check = 0;
		boolean status = false;
		try (PreparedStatement pst = getConnection().prepareStatement("delete from employee where id = ?");) {
			pst.setInt(1, id);
			check = pst.executeUpdate();
		} finally {
			if (check > 1) {
				status = true;
			}
		}
		return status;
	}

	@Override
	public boolean isExists(int id) throws SQLException {
		boolean result = false;
		ResultSet rs = null;
		try (PreparedStatement pst = getConnection()
				.prepareStatement("select id, name, salary, age, gender from employee where id = ?");) {
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs != null) {
				result = true;
			}
		} finally {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
		return result;
	}

	@Override
	public double getHRA(int hraId) throws SQLException {
		double hra = 0;
		ResultSet rs = null;
		try (PreparedStatement pst = getConnection().prepareStatement("select salary from employee where id = ?");) {
			pst.setInt(1, hraId);
			rs = pst.executeQuery();
			if (rs.next()) {
				float temp = rs.getFloat(1);
				Employee e = new Employee();
				e.setSalary(temp);
				hra = EmployeeUtil.calHRA(e);
			}
		}
		return hra;
	}

	@Override
	public double getGross(int grossID) throws SQLException {
		double gross = 0;
		ResultSet rs = null;
		try (PreparedStatement pst = getConnection().prepareStatement("select salary from employee where id = ?");) {
			pst.setInt(1, grossID);
			rs = pst.executeQuery();
			if (rs.next()) {
				float temp = rs.getFloat(1);
				Employee e = new Employee();
				e.setSalary(temp);
				gross = EmployeeUtil.calHRA(e);
			}
		}
		return gross;
	}

	@Override
	public String orderBy(String col) throws SQLException {
		String result = null;
		ResultSet rs = null;
		try (PreparedStatement pst = getConnection()
				.prepareStatement("select id, name, salary, age, gender from employee order by " + col);) {
			rs = pst.executeQuery();
			if (rs != null) {
				result = "Employees info \n";
				while (rs.next()) {
					result = result + rs.getInt(1) + " " + rs.getString(2) + " " + rs.getFloat(3) + " " + rs.getInt(4)
							+ " " + rs.getString(5) + "\n";
				}
			}
		}
		return result;
	}

}
