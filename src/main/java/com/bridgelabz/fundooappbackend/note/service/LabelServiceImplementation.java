package com.bridgelabz.fundooappbackend.note.service;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooappbackend.note.dto.LabelDto;
import com.bridgelabz.fundooappbackend.note.message.Messages;
import com.bridgelabz.fundooappbackend.note.model.Label;
import com.bridgelabz.fundooappbackend.note.noteresponse.Response;
import com.bridgelabz.fundooappbackend.note.repository.LabelRepository;
import com.bridgelabz.fundooappbackend.note.utility.TokensUtility;
import com.bridgelabz.fundooappbackend.user.exception.custom.InputNotFoundException;
import com.bridgelabz.fundooappbackend.user.exception.custom.LabelNotFoundException;
import com.bridgelabz.fundooappbackend.user.exception.custom.TokenException;
import com.bridgelabz.fundooappbackend.user.exception.custom.UserNotFoundException;
import com.bridgelabz.fundooappbackend.user.model.User;
import com.bridgelabz.fundooappbackend.user.repository.UserRepository;

/**********************************************************************************************************
 * @author 	:Pramila Tawari
 * Purpose	:Label Service Implementation to perform operations On Label
 *
 *******************************************************************************************************/
@Service
@PropertySource("message.properties")
public class LabelServiceImplementation implements LabelService {
	
	@Autowired
	private ModelMapper mapper;
	 
	@Autowired
	private TokensUtility tokenUtility;
	
	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private Environment environment;
	
/**
*     @return  Function to add a New Label
*
*************************************************************************************************/
	public Response addLabel(LabelDto labelDto,String token) 
	{
		// If Name field is Empty then throw exception
		if(labelDto.getName().isEmpty())
		{
			throw new InputNotFoundException(Messages.INPUT_NOT_FOUND);
		}
		
		Label label = mapper.map(labelDto, Label.class); 
		
		String userEmail = tokenUtility.getUserToken(token); 
		
		User user = repository.findByEmail(userEmail);
		
		if(user ==  null)
		{
			throw new UserNotFoundException(Messages.USER_NOT_FOUND);
		}
		
		label.setUser(user);
		
		labelRepository.save(label); // Storing Label Data in Database
		
		return new Response(Integer.parseInt(environment.getProperty("http.status.code") ),
				environment.getProperty("status.success.labelcreated"), environment.getProperty("success.status"));
	}
	
/**
*     @return  Function to upadte Label
*
*************************************************************************************************/
	public Response updateLabel(@Valid int id,LabelDto updateLabelDto, String token) 
	{
		// If Name field is Empty then throw exception
		if(updateLabelDto.getName().isEmpty())
		{
			throw new InputNotFoundException(Messages.INPUT_NOT_FOUND);
		}
		
		//Label label = mapper.map(updateLabelDto, Label.class);
		
		String userEmail = tokenUtility.getUserToken(token);
		
		User user = repository.findByEmail(userEmail);	
		
		if(user ==  null)
		{
			throw new UserNotFoundException(Messages.USER_NOT_FOUND);
		}
		
		Label labelUpdate = labelRepository.findById(id);
		
		if( labelUpdate == null)
		{
			throw new  LabelNotFoundException(Messages.LABEL_NOT_FOUND);
		}
				
		labelUpdate.setName(updateLabelDto.getName());
		
		updateLabelDto.setName(updateLabelDto.getName());
		
		labelRepository.save(labelUpdate);
		
		return new Response(Integer.parseInt(environment.getProperty("http.status.code") ),
				environment.getProperty("status.success.labelupdated"), environment.getProperty("success.status"));
	}

/**
*     @return  Function to delete Note
*
*************************************************************************************************/
	public Response deleteLabel(int id,String token) 
	{
		//Note note = mapper.map(deleteNoteDto, Note.class); // Mapping new User data into the user Class
		String userToken = tokenUtility.getUserToken(token); 
		
		if (userToken.isEmpty())
		{
				throw new TokenException(Messages.INVALID_TOKEN);
		}
		
		User user = repository.findByEmail(userToken);
		
		if(user ==  null)
		{
			throw new UserNotFoundException(Messages.USER_NOT_FOUND);
		}
		
		Label label = labelRepository.findById(id);
		
		if( label == null)
		{
			throw new  LabelNotFoundException(Messages.LABEL_NOT_FOUND);
		}
		
	//	label.setUser(user);
		
		labelRepository.delete(label);
		
		return new Response(Integer.parseInt(environment.getProperty("http.status.code") ),
				environment.getProperty("status.success.labeldeleted"), environment.getProperty("success.status"));
	}

/**
*     @return  Function to find a Label
*
*************************************************************************************************/
	public Response findLabel(int id, String token) {
		String userToken = tokenUtility.getUserToken(token);
		
		if (userToken.isEmpty())
		{
				throw new TokenException(Messages.INVALID_TOKEN);
		}
		
		User user = repository.findByEmail(userToken);
		
		if(user ==  null)
		{
			throw new UserNotFoundException(Messages.USER_NOT_FOUND);
		}
		
		Label label = labelRepository.findById(id);
	//	label.setUser(user);
	
		if( label == null)
		{
			throw new  LabelNotFoundException(Messages.LABEL_NOT_FOUND);
		}
		return new Response(Integer.parseInt(environment.getProperty("http.status.code") ),
				environment.getProperty("status.success.labelfound"), label);
	//	return new Response(Messages.OK, "User Registration ", labelRepository.findById(id));
	}
	
/**
*     @return  Function to get all labels
*
*************************************************************************************************/                                                                                                      
	public List<Label> getAllLabels(String token)
	{
		String userToken = tokenUtility.getUserToken(token);
		
		if (userToken.isEmpty())
		{
				throw new TokenException(Messages.INVALID_TOKEN);
		}
		User user = repository.findByEmail(userToken);
		
		if(user ==  null)
		{
			throw new UserNotFoundException(Messages.USER_NOT_FOUND);
		}
		
		List<Label> label = labelRepository.findAll().stream().filter(e -> e.getUser().getId() == user.getId()).collect(Collectors.toList());
		
		return label;
	}
}

/***********************************************************************************************/