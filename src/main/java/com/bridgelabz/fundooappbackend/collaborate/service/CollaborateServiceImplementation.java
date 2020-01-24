package com.bridgelabz.fundooappbackend.collaborate.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundooappbackend.collaborate.collaboratemessage.CollaborateMessages;
import com.bridgelabz.fundooappbackend.collaborate.collaboratemessage.CollaboratorMessageUtility;
import com.bridgelabz.fundooappbackend.collaborate.dto.CollaborateDto;
import com.bridgelabz.fundooappbackend.collaborate.model.Collaborate;
import com.bridgelabz.fundooappbackend.collaborate.repository.CollaborateRepository;
import com.bridgelabz.fundooappbackend.collaborate.utility.CollaborateTokenUtility;
import com.bridgelabz.fundooappbackend.note.message.Messages;
import com.bridgelabz.fundooappbackend.note.model.Note;
import com.bridgelabz.fundooappbackend.note.repository.NotesRepository;
import com.bridgelabz.fundooappbackend.user.exception.custom.NoteNotFoundException;
import com.bridgelabz.fundooappbackend.user.exception.custom.UserNotFoundException;
import com.bridgelabz.fundooappbackend.user.response.Response;
/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Collaborate Implementation Class To Perform Collaboration
 *
 ***********************************************************************************************************/
@Service
public class CollaborateServiceImplementation implements CollaborateService
{
	@Autowired
	private CollaborateRepository collaborateRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CollaborateTokenUtility tokenUtility;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private NotesRepository noteRepository;
	
	public Response Collaborate(CollaborateDto collaborateDto , String token )
	{
		Collaborate collaborator = mapper.map(collaborateDto , Collaborate.class);
		
		String useremail = tokenUtility.getUserToken(token);

		if(useremail == null)
		{
			 throw new UserNotFoundException(Messages.USER_NOT_EXISTING);
		}

		collaborator.setSenderMail(useremail);
		
		Note note = noteRepository.findById(collaborateDto.getNoteID());
		
		if (note == null) {
			throw new NoteNotFoundException(Messages.NOTE_NOT_FOUND);
		}
		
		javaMailSender.send(CollaboratorMessageUtility.sendMail(collaborator.getSenderMail(), collaborateDto.getReceiverMail(), "this is the note"+note ));
		
		collaborateRepository.save(collaborator);
		
		return new Response(Messages.OK, null, CollaborateMessages.COLLABORATOR_ADDED);
	}	
}