package com.bridgelabz.fundooappbackend.note.service;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundooappbackend.note.dto.LabelDto;
import com.bridgelabz.fundooappbackend.note.dto.UpdateLabelDto;
import com.bridgelabz.fundooappbackend.note.model.Label;
import com.bridgelabz.fundooappbackend.note.repository.LabelRepository;
import com.bridgelabz.fundooappbackend.note.response.Responses;
import com.bridgelabz.fundooappbackend.note.utility.TokensUtility;
import com.bridgelabz.fundooappbackend.user.exception.custom.TokenException;
import com.bridgelabz.fundooappbackend.user.exception.custom.UserNotFoundException;
import com.bridgelabz.fundooappbackend.user.model.User;
import com.bridgelabz.fundooappbackend.user.repository.UserRepository;
import com.bridgelabz.fundooappbackend.note.message.Messages;

/**********************************************************************************************************
 * @author 	:Pramila Tawari
 * Purpose	:Label Service Implementation to perform operations On Label
 *
 *******************************************************************************************************/
@Service
public class LabelServiceImplementation implements LabelService {
	
	@Autowired
	private ModelMapper mapper;
	 
	@Autowired
	private TokensUtility tokenUtility;
	
	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired
	private UserRepository repository;
	
/**
*     @return  Function to add a New Label
*
*************************************************************************************************/
	public Responses addLabel(LabelDto labelDto,String token) 
	{
		Label label = mapper.map(labelDto, Label.class); // Mapping new User data into the user Class
		String userToken = tokenUtility.getUserToken(token); 
		User user = repository.findByEmail(userToken);
		if(user ==  null)
		{
			throw new UserNotFoundException(Messages.USER_NOT_EXISTING);
		}
		label.setUser(user);
		label = labelRepository.save(label); // Storing Users Data in Database
		return new Responses(Messages.OK, null, Messages.LABEL_CREATED);
	}
	
/**
*     @return  Function to upadte Label
*
*************************************************************************************************/
	public Responses updateLabel(UpdateLabelDto updateLabelDto, String token) 
	{
		Label label = mapper.map(updateLabelDto, Label.class);
		String userToken = tokenUtility.getUserToken(token);
		User user = repository.findByEmail(userToken);	
		label.setUser(user);
		updateLabelDto.setId(updateLabelDto.getId());
		updateLabelDto.setName(updateLabelDto.getName());
		label = labelRepository.save(label);
		return new Responses(Messages.OK, null, Messages.LABEL_UPDATED);
	}

/**
*     @return  Function to delete Note
*
*************************************************************************************************/
	public Responses deleteLabel(int id,String token) 
	{
		//Note note = mapper.map(deleteNoteDto, Note.class); // Mapping new User data into the user Class
		String userToken = tokenUtility.getUserToken(token); 
		User user = repository.findByEmail(userToken);
		Label label = labelRepository.findById(id);
		label.setUser(user);
		labelRepository.delete(label);
		return new Responses(Messages.OK, null, Messages.LABEL_DELETED);
	}

/**
*     @return  Function to find a Label
*
*************************************************************************************************/
	public Responses findLabel(int id, String token) {
		String userToken = tokenUtility.getUserToken(token);
		if (userToken.isEmpty())
		{
				throw new TokenException(Messages.INVALID_TOKEN);
		}
		User user = repository.findByEmail(userToken);
		Label label = labelRepository.findById(id);
		label.setUser(user);
		return new Responses(Messages.OK, "User Registration ", labelRepository.findById(id));
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
		
		List<Label> label = labelRepository.findAll().stream().filter(e -> e.getUser().getId() == user.getId()).collect(Collectors.toList());
		return label;
	}
}

/***********************************************************************************************/