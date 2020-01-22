package com.bridgelabz.fundooappbackend.user.model;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import com.bridgelabz.fundooappbackend.note.model.Note;

/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :User Model Class
 *
 *********************************************************************************************************/

@Component
@Entity
@Table(name = "userDetails")
public class User implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull
	private String firstname;
	
	@NotNull
	private String lastname;
	
	@NotNull
	@Email(regexp =  "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.(?:[A-Z]{2,}|com|org))+$")
	private String email;
	
	@NotNull
	// @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*+\\/|!\"£$%^&*()#[\\]@~'?><,.=-_]).{6,20}", message="Password must be between 6 and 20 characters and contain one uppercase letter, one lowercase letter, one digit and one special character.")
	private String password;
	
	@NotNull
	//@Pattern(regexp = "{10}")
	private long phonenumber;
	
	@NotNull
	private boolean validate;
	
	@OneToMany
    private List<Note> note;
	
	public User() 
	{

	}

	public User(int id, @NotNull String firstname, @NotNull String lastname, @NotNull String email,
			@NotNull String password, @NotNull long phonenumber, @NotNull boolean validate)
	{
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.phonenumber = phonenumber;
		this.validate = validate;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getFirstname() 
	{
		return firstname;
	}

	public void setFirstname(String firstname) 
	{
		this.firstname = firstname;
	}

	public String getLastname() 
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public long getPhonenumber() 
	{
		return phonenumber;
	}

	public void setPhonenumber(long phonenumber)
	{
		this.phonenumber = phonenumber;
	}

	public boolean isValidate()
	{
		return validate;
	}

	public void setValidate(boolean validate) 
	{
		this.validate = validate;
	}

	public String toString() 
	{
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", phonenumber=" + phonenumber + ", validate=" + validate + "]";
	}
}
