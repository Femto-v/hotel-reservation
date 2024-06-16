public class Customer extends User {
    private String email;
	private String phoneNo;

	public Customer(){}
    public Customer (String email,String phoneNo)
    {
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phoneNo;
	}

    public void setEmail(String email) {
		this.email = email;
	}
	
	public void setphoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public void print() {
		System.out.println("id: "+id);
		System.out.println("Name: "+name);
		System.out.println("Email: "+email);
		System.out.println("phoneNo: "+phoneNo);
	}
}
