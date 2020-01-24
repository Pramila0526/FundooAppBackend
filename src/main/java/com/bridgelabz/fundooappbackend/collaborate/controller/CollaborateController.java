package com.bridgelabz.fundooappbackend.collaborate.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundooappbackend.collaborate.dto.CollaborateDto;
import com.bridgelabz.fundooappbackend.collaborate.service.CollaborateServiceImplementation;
import com.bridgelabz.fundooappbackend.user.response.Response;
/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Collaborate Controller For Generating API's
 *
 ***********************************************************************************************************/
@RestController
@RequestMapping(value ="/fundoo")
public class CollaborateController
{
	@Autowired
	CollaborateServiceImplementation collaborateServiceImplementation;
	
	@GetMapping("/coldemo")
	public String demoz()
	{
		return "Hello User";
	}
	
	 // Method to collaborate
	 @PutMapping("/collaborate")
     public ResponseEntity<Response> Collaborate(@RequestBody CollaborateDto collaborateDto, @RequestHeader String token)
     {
		return new ResponseEntity<Response>(collaborateServiceImplementation.Collaborate(collaborateDto, token ), HttpStatus.OK); // give
     } 
}