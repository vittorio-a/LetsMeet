package it.unisa.studenti.letsmeet.model;

import java.util.Arrays;

public class CredentialsBean {
	
	
	private String username;
	private byte[] password;
	private StatoUtente state;
	
	
	public CredentialsBean() {
		username = null;
		password = null;
		state = StatoUtente.INVALIDO;
	}
	
	
	public CredentialsBean(String username, byte[] password, StatoUtente state) {
		this.username = username;
		this.password = password;
		this.state = state;
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
	public StatoUtente getState() {
		return state;
	}
	public void setState(StatoUtente state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "CredentialsBean [username=" + username + ", password=" + password + ", state=" + state + "]";
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CredentialsBean other = (CredentialsBean) obj;
		if (!Arrays.equals(password, other.password))
			return false;
		if (state != other.state)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	

}
