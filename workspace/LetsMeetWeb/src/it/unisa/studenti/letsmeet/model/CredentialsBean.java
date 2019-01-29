package it.unisa.studenti.letsmeet.model;

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
	
	
	
	
}
