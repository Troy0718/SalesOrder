package main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

//	@OneToMany(mappedBy = "users" ,cascade = CascadeType.ALL , orphanRemoval = true )
//	private List<Roles> roles;

	private String login;

	@Column(length = 68)
	private String password;

	@Transient
	private String confirmePassword;

	private boolean enabled;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
//	public List<Roles> getRoles() {
//		return roles;
//	}
//	public void setRoles(List<Roles> roles) {
//		this.roles = roles;
//	}

	public boolean isEnabled() {
		return enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getConfirmePassword() {
		return confirmePassword;
	}

	public void setConfirmePassword(String confirmePassword) {
		this.confirmePassword = confirmePassword;
	}

}
