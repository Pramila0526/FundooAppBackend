package com.bridgelabz.fundooappbackend.collaborate.service;
import com.bridgelabz.fundooappbackend.collaborate.dto.CollaborateDto;
import com.bridgelabz.fundooappbackend.note.response.Responses;
/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Collaborate Service
 *
 ***********************************************************************************************************/
public interface CollaborateService
{
	public Responses Collaborate(CollaborateDto collaboratorDto , String token );
}
