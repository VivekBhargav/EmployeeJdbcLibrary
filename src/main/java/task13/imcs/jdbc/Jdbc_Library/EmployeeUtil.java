package task13.imcs.jdbc.Jdbc_Library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeUtil {

	public static Scanner scan = new Scanner(System.in);

	public static void getDepartment() {
		String str;
		int depNum;

		System.out.println("10.SALES\n20.PURCHASE\n30.ADMINISTRATION\n40.RESEARCH");
		System.out.println("Select Deparment: ");

		do {
			depNum = scan.nextInt();
			switch (depNum) {
			case 10:
				str = "SALES";
				break;
			case 20:
				str = "PURCHASE";
				break;
			case 30:
				str = "ADMINISTRATION";
				break;
			case 40:
				str = "RESEARCH";
				break;
			default:
				System.out.println("Mentioned Depatment Number does not exists. Try again");
				str = "invalid";
			}
			if (str != "invalid") {
				System.out.println(str);
				break;
			}
		} while ((depNum != 10) || (depNum != 20) || (depNum != 30) || (depNum != 40));
	}

	public static Employee createEmployee(int id, String name, float salary, int age, char gender) {
		return new Employee(id, name, salary, age, gender);
	}

	// Used to create Employee object with given id
	public static Employee createEmpObj(int id) throws SalaryException {
		System.out.println("Enter Employee Name: ");
		String name = scan.next();
		System.out.println("Enter Employee Salary(Should be greater than 5000): ");
		float salary = scan.nextFloat();
		System.out.println("Enter Employee Age: ");
		int age = scan.nextInt();
		System.out.println("Enter Employee Gender(M/F): ");
		char gender = scan.next().charAt(0);
		if (salary < 5000) {
			throw new SalaryException("Salary is less than 5000");
		}
		return EmployeeUtil.createEmployee(id, name, salary, age, gender);
	}

	public static double calHRA(Employee emp) {
		double hra = 0;
		if (emp.getSalary() < 10000) {
			hra = emp.getSalary() * .15;
		} else if (emp.getSalary() < 20000) {
			hra = emp.getSalary() * .20;
		} else if (emp.getSalary() < 30000 && emp.getAge() >= 40) {
			hra = emp.getSalary() * .27;
		} else if (emp.getSalary() < 30000 && emp.getSalary() < 40) {
			hra = emp.getSalary() * .25;
		} else
			hra = emp.getSalary() * .30;
		return hra;
	}

	public static double calGross(Employee emp) {
		double gross = 0;
		if (emp.getSalary() < 10000) {
			gross = emp.getSalary() + emp.getSalary() * .8 + calHRA(emp);
		} else if (emp.getSalary() < 20000) {
			gross = emp.getSalary() + emp.getSalary() * .10 + calHRA(emp);
		} else if (emp.getSalary() < 30000 && emp.getAge() >= 40) {
			gross = emp.getSalary() + emp.getSalary() * .15 + calHRA(emp);
		} else if (emp.getSalary() < 30000 && emp.getAge() < 40) {
			gross = emp.getSalary() + emp.getSalary() * .13 + calHRA(emp);
		} else
			gross = emp.getSalary() + emp.getSalary() * .17 + calHRA(emp);
		return gross;
	}

	public static List<Employee> readFromFile(File file) throws IOException {
		
		List<Employee> emp = new ArrayList<>();
		Reader fr = null;
		BufferedReader br = null;
		try {

			fr = new FileReader(file);
			br = new BufferedReader(fr);
			int index = 1;
			String line = null;
			while (true) {
				line = br.readLine();
				
				if (line == null || line.equals("")) {
					break;
				}

				if (index != 0) {
					Employee employee = parseLine(line);
					emp.add(employee);
				}
				index++;
			}
		} finally {
			br.close();
			fr.close();
		}
		return emp;
	}

	public static Employee parseLine(String line) {
		String[] tokens = line.split(",");
		Employee employee = new Employee(Integer.valueOf(tokens[0]), tokens[1], Float.parseFloat(tokens[2]),
				Integer.parseInt(tokens[3]), tokens[4].charAt(0));
		return employee;
	}

}
