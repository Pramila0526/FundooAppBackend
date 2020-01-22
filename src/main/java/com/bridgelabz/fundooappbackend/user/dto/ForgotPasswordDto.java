package com.bridgelabz.fundooappbackend.user.dto;

/**********************************************************************************************************
 * @author 	:Pramila Tawari 
 * Purpose	:ForgetPasswordDto uses Email for password recovery
 *
 *********************************************************************************************************/

public class ForgotPasswordDto 
{
	private String email;
	
	public ForgotPasswordDto() 
	{
	}
	
	public ForgotPasswordDto(String email) 
	{		
		super();
		this.email = email;
	}
	
	public String getEmail() 
	{		
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String toString() 
	{
		return "ForgetPasswordDto [email=" + email + "]";
	}
}