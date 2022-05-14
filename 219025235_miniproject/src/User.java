
/**
 * 
 */

/**
 * @author Azan S
 *
 */
public class User implements Comparable<User> {
	
	private String Name;
	private String Surname;
	private String Location;
	private String Age;
	private String Expertise; //Years of experience
	//private String Contact;
	
	/*
	 * Default Constructor
	 */
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Overloaded Constructor
	 */
	public User(String Name, String Surname, String Location, String Age, String Expertise) {
		// TODO Auto-generated constructor stub
		this.Name = Name;
		this.Surname = Surname;
		this.Location = Location;
		this.Age = Age;
		this.Expertise = Expertise;
	}
	
	public String toString() {
//		return "{" + Name + " " + Location + " " + Age + " " + Expertise + " " + Contact + "}";
		return Name + "," + Location + "," + Age + "," + Expertise ;
	}
		
	public String getName() {
		return Name;
	}
	
	public void setName(String Name) {
		this.Name = Name;
	}
	
	public String getSurname() {
		return Surname;
	}
	
	public void setSurname(String Surname) {
		this.Surname = Surname;
	}
	
	public String getLocation() {
		return Location;
	}
	
	public void setLocation(String Location) {
		this.Location = Location;
	}
	
	public String getAge() {
		return Age;
	}
	
	public void setAge(String Age) {
		this.Age = Age;
	}
	
	public String getExpertise() {
		return Expertise;
	}
	
	public void setExpertise(String Expertise) {
		this.Expertise = Expertise;
	}
	
//	public String getContact() {
//		return Contact;
//	}
//	
//	public void setContact(String Contact) {
//		this.Contact = Contact;
//	}


	@Override
	public int compareTo(User u) {
		// TODO Auto-generated method stub
		
		if(!this.Location.equals(u.Location)) {
			return -1;
		}
		if(this.Age.equals(u.Age) || this.Expertise.equals(u.Expertise)) {
			return 0;
		}
		return 1;
	}

}
