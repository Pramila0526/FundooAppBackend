package com.bridgelabz.fundooappbackend.user.response;
import java.io.Serializable;
/************************************************************************************************************************
 * @author 	:Pramila Tawari
 * Purpose 	:Response for Postman
 *
 *****************************************************************************************************************/
public class Response implements Serializable
{
private static final long serialVersionUID = 1L;
	
	private String status;  
	@SuppressWarnings("unused")
	private String message;
	private Object data;

	public Response()
	{
		
	}
	
	public Response(String status,String message, Object data)
	{
		super();
		this.status = status;
		this.message=message;
		this.data = data;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Object getData() 
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public String toString() 
	{
		return "Response [status=" + status + " data=" + data + "]";
	} 
}
