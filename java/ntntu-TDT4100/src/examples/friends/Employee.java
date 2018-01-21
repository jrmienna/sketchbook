package examples.friends;


public class Employee {
	
	String name;
	Employer employer;
	
	public Employee(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Employer getEmployer() {
		return employer;
	}
	
	public void setEmployer(Employer employer) {
		if (this.employer == employer) {
			return;
		} else if (employer == null) {
			this.employer.removeEmployee(this);
			this.employer = null;
		} else if (this.employer == null) {
			this.employer = employer;
			this.employer.addEmployee(this);
		} else {
			this.employer.removeEmployee(this);
			this.employer = employer;
			this.employer.addEmployee(this);
			
		}
	}
	
	public String toString() {
		if (employer != null) {
			return name + "@" + employer.getName();
		} else {
			return name + "@null";
		}
	}
}