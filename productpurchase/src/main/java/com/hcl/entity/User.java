package com.hcl.entity;

public class User {
	private int id;
	private String name;
	private boolean isNewUser;
	private String email;
	
	
	
	
	public User(int id, String name, boolean newUser,String email) {
		super();
		this.id = id;
		this.name = name;
		this.isNewUser = newUser;
		this.email=email;
	}
	
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getIsNewUser() {
		return isNewUser;
	}
	public void setIsNewUser(boolean newUser) {
		this.isNewUser = newUser;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + (isNewUser ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (isNewUser != other.isNewUser)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", newUser=" + isNewUser + "]";
	}
	
	
	

}
