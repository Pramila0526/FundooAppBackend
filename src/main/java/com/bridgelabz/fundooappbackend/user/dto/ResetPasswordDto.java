package com.bridgelabz.fundooappbackend.user.dto;
 
/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Confriming the Password
 *
 *********************************************************************************************************/

public class ResetPasswordDto {
	private String password;
	private String confirmpassword;
	
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getConfirmpassword()
	{
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword)
	{
		this.confirmpassword = confirmpassword;
	}
	public ResetPasswordDto() 
	{
		
	}
	public ResetPasswordDto(String password, String confirmpassword) 
	{
		super();
		this.password = password;
		this.confirmpassword = confirmpassword;
	}
	@Override
	public String toString() {
		return "ResetPasswordDto [password=" + password + ", confirmpassword=" + confirmpassword + "]";
	}
}