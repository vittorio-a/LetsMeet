package it.unisa.studenti.letsmeet.model;

public class SuperAdminBean {
	
	
	private String username;
	private String password;
	private int idSuperAdmin;
	
	public SuperAdminBean() {
		this.idSuperAdmin = 0;
		this.password = null;
		this.username = null;
	}
	
	public SuperAdminBean(String username, String password, int idSuperAdmin) {
		this.username = username;
		this.password = password;
		this.idSuperAdmin = idSuperAdmin;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIdSuperAdmin() {
		return idSuperAdmin;
	}
	public void setIdSuperAdmin(int idSuperAdmin) {
		this.idSuperAdmin = idSuperAdmin;
	}
	@Override
	public String toString() {
		return "SuperAdminBean [username=" + username + ", password=" + password + ", idSuperAdmin=" + idSuperAdmin
				+ "]";
	}

}
