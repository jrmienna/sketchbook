package examples.friends;

import java.util.ArrayList;

public class Employer {
	
	String name;
	ArrayList<Employee> employees;
	
	public Employer(String name) {
		this.name = name;
		employees = new ArrayList<Employee>();
	}
	
	public String getName() {
		return name;
	}
	
	public Employee getEmployee(int index) {
		return employees.get(index);
	}
	
	public void addEmployee(Employee employee) {
		if (! employees.contains(employee)) {
			employees.add(employee);
			employee.setEmployer(this);
		}
	}
	
	public void removeEmployee(Employee employee) {
		if (employees.contains(employee)) {
			employees.remove(employee);
			employee.setEmployer(null);
		}
	}
	
	public String toString() {
		return name + ": " + employees.toString();
	}
	
	public static void main(String[] args) {
		Employer mcdonalds = new Employer("McDonalds");
		Employer burgerking = new Employer("BurgerKing");
		Employee per = new Employee("Per");
		Employee erik = new Employee("Erik");
		Employee carl = new Employee("Carl");
		mcdonalds.addEmployee(per);
		mcdonalds.addEmployee(erik);
		mcdonalds.addEmployee(carl);
		System.out.println(mcdonalds);
		System.out.println(burgerking);
		System.out.println();
		
//		burgerking.addEmployee(per);
		per.setEmployer(burgerking);
		
		System.out.println(mcdonalds);
		System.out.println(burgerking);
	}
}