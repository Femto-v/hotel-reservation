

public class Employee extends User{
    private double salary;
	private String job;

    public Employee (double salary,String job)
    {
        this.salary = salary;
        this.job = job;
    }

    public Employee(){}

    public double getSalary() {
		return salary;
	}
	
	public String getJob() {
		return job;
	}

    public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public void setJob(String job) {
		this.job = job;
	}

	@Override
    	public void print() {
		System.out.println("\n-------------------------");
		System.out.println("id: "+id);
		System.out.println("Name: "+name);
		System.out.println("Salary: "+salary);
		System.out.println("Job: "+job);
		System.out.println("-------------------------\n");
	}
}
