package it.unisa.studenti.letsmeet.model;

import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

/**
 *Descrive un SuperAdmin della piattaforma
 *
 */
public class SuperAdminBean {
	
	
	private String username;
	private byte[] password;
	private int idSuperAdmin;
	
	/**
	 * Costruttore vuoto
	 */
	public SuperAdminBean() {
		this.idSuperAdmin = 0;
		this.password = null;
		this.username = null;
	}
	
	/**Costruttore 
	 * @param username nome da dare al superAdmin con cui verrà riconosciuto 
	 * @param password password del superAdmin per accedere alla piattaforma
	 * @param idSuperAdmin id univoco del superAdmin
	 */
	public SuperAdminBean(String username, byte[] password, int idSuperAdmin) {
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
	public byte[] getPassword() {
		return password;
	}
	public void setPassword(byte[] password) {
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
		String pswHex = DatatypeConverter.printHexBinary(password);
		return "SuperAdminBean [username=" + username + ", password=" + pswHex + ", idSuperAdmin=" + idSuperAdmin
				+ "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SuperAdminBean other = (SuperAdminBean) obj;
		if (idSuperAdmin != other.idSuperAdmin)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!Arrays.equals(password, other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
