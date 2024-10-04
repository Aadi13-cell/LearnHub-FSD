package in.learnhub.demo.Model;

import jakarta.persistence.*;




@Entity
@Table(name = "Users_data")
public class AppUser{
	
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int fid;

	private String name;
	
	private String email;
	
	private String phone;
	
	private String address;
	
	private String password;
	
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public AppUser(int fid, String name, String email, String phone, String address, String password, String role) {
		super();
		this.fid = fid;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.password = password;
		this.role = role;
	}

	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
}
