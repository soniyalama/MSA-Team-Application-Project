package users;

public abstract class User {
	private int id;
	private String firstname;
	private String lastname;
	private int YearsOfExperience;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getUsername() {
		return firstname+lastname;
	}

	public String getFullname() {
		return firstname + " " + lastname;
	}
	
	public abstract Role getRole();

	public int getYearsOfExperience() {
		return YearsOfExperience;
	}
	public void setYearsOfExperience(int yearsOfExperience) {
		YearsOfExperience = yearsOfExperience;
	}
	
}
