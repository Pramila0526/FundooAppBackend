package com.bridgelabz.fundooappbackend.collaborate.collaboratemessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**********************************************************************************************************
 * @author :Pramila Mangesh Tawari 
 * Purpose :Messages for identifying the Status of
 *          Implementation
 *
 *********************************************************************************************************/

@Component
public class CollaboratorMessageUtility 
{

	
	public static SimpleMailMessage sendMail(String email1, String email2 ,String note) 
	{
		SimpleMailMessage msg = new SimpleMailMessage();
	
		msg.setFrom(email1);
		msg.setTo(email2); // send mail
		msg.setSubject("Note Shared to you"); // send message to user email account
		msg.setText("Note- " +note); // send token to user email account

		return msg;

	}

}