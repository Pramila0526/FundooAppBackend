package com.bridgelabz.fundooappbackend.note.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundooappbackend.note.dto.NoteDto;
import com.bridgelabz.fundooappbackend.note.message.Messages;
import com.bridgelabz.fundooappbackend.note.model.Label;
import com.bridgelabz.fundooappbackend.note.model.Note;
import com.bridgelabz.fundooappbackend.note.repository.NotesRepository;
import com.bridgelabz.fundooappbackend.note.response.Responses;
import com.bridgelabz.fundooappbackend.note.utility.TokensUtility;
import com.bridgelabz.fundooappbackend.user.exception.custom.InputNotFoundException;
import com.bridgelabz.fundooappbackend.user.exception.custom.LabelNotFoundException;
import com.bridgelabz.fundooappbackend.user.exception.custom.TokenException;
import com.bridgelabz.fundooappbackend.user.exception.custom.UserNotFoundException;
import com.bridgelabz.fundooappbackend.user.model.User;
import com.bridgelabz.fundooappbackend.user.repository.UserRepository;

/******************************************************************************************************************
 * @author :Pramila Mangesh Tawari Purpose :To perform Operations on Note
 *
 *********************************************************************************************************/

@Service
public class NoteServiceImplementation implements NoteService {

	@Autowired
	private NotesRepository notesRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private TokensUtility tokenUtility;

	@Autowired
	private UserRepository repository;

	/**
	 * @return Function to add a New Note
	 *
	 *************************************************************************************************/
	public Responses addNewNote(@Valid NoteDto noteDto, String token) {

		if (noteDto.getTitle().isEmpty() || noteDto.getDescription().isEmpty()
				|| (noteDto.getTitle().isEmpty() && noteDto.getDescription().isEmpty())) {
			throw new InputNotFoundException(Messages.INPUT_NOT_FOUND);
		}

		Note note = mapper.map(noteDto, Note.class);

		String userEmail = tokenUtility.getUserToken(token);

		User user = repository.findByEmail(userEmail);

		if (user == null) {
			throw new UserNotFoundException(Messages.USER_NOT_EXISTING);
		}

		note.setUser(user);

		notesRepository.save(note); // Storing Users Data in Database

		return new Responses(Messages.OK, null, Messages.NOTE_CREATED);
	}

	/**
	 * @return Function to Update a note
	 *
	 *************************************************************************************************/
	public Responses updateNote(@Valid int id, NoteDto updateNoteDto, String token) {

		if (updateNoteDto.getTitle().isEmpty() || updateNoteDto.getDescription().isEmpty()
				|| (updateNoteDto.getTitle().isEmpty() && updateNoteDto.getDescription().isEmpty())) {
			throw new InputNotFoundException(Messages.INPUT_NOT_FOUND);
		}

		Note note = mapper.map(updateNoteDto, Note.class);

		String useremail = tokenUtility.getUserToken(token);

		User user = repository.findByEmail(useremail);

		if (user == null) {
			throw new UserNotFoundException(Messages.USER_NOT_EXISTING);
		}

		Note noteUpdate = notesRepository.findById(id);

		if (noteUpdate == null) {
			throw new LabelNotFoundException(Messages.NOTE_NOT_FOUND);
		}

		noteUpdate.setTitle(updateNoteDto.getTitle());

		noteUpdate.setDescription(updateNoteDto.getDescription());

		notesRepository.save(note);

		return new Responses(Messages.OK, null, Messages.NOTE_UPDATED);
	}

	/**
	 * @return Function to Delete a note
	 *
	 *************************************************************************************************/
	public Responses deleteNote(int id, String token) {

		String userEmail = tokenUtility.getUserToken(token);

		User user = repository.findByEmail(userEmail);

		if (user == null) {
			throw new UserNotFoundException(Messages.USER_NOT_EXISTING);
		}

		Note note = notesRepository.findById(id);

		if (note == null) {
			throw new LabelNotFoundException(Messages.NOTE_NOT_FOUND);
		}

		// note.setUser(user);

		notesRepository.delete(note);

		return new Responses(Messages.OK, null, Messages.NOTE_DELETED);
	}

	/**
	 * @return Function get all notes
	 *
	 *************************************************************************************************/
	public List<Note> getAllNotes(String token) {

		String usertoken = tokenUtility.getUserToken(token);
		if (usertoken.isEmpty()) {
			throw new TokenException(Messages.INVALID_TOKEN);
		}
		User user = repository.findByEmail(usertoken);

		if (user == null) {
			throw new UserNotFoundException(Messages.USER_NOT_EXISTING);
		} 
		
		List<Note> note = notesRepository.findAll().stream().filter(e -> e.getUser().getId() == user.getId())
				.collect(Collectors.toList());
		
		if (note == null) {
			throw new LabelNotFoundException(Messages.NOTE_NOT_FOUND);
		}

		System.out.println(note);
		return note;
	}

	/**
	 * @return Function to sort notes by title
	 *
	 *************************************************************************************************/
	public List<Note> sortByTitle(String token) {
		String usertoken = tokenUtility.getUserToken(token);
		System.out.println(usertoken);
		if (usertoken.isEmpty()) {
			throw new TokenException(Messages.INVALID_TOKEN);
		}
		User user = repository.findByEmail(usertoken);
		System.out.println("user1" + user);
		user.getId();
		List<Note> list = notesRepository.findAll().stream().filter(e -> e.getUser().getId() == user.getId())
				.collect(Collectors.toList());

		list = list.stream().sorted((list1, list2) -> list1.getTitle().compareTo(list2.getTitle()))
				.collect(Collectors.toList());
		return list;
	}

	/**
	 * @return Function to sort notes by Description
	 *
	 *************************************************************************************************/
	public List<Note> sortByDescription(String token) {
		String usertoken = tokenUtility.getUserToken(token);
		System.out.println(usertoken);
		if (usertoken.isEmpty()) {
			throw new TokenException(Messages.INVALID_TOKEN);
		}
		User user = repository.findByEmail(usertoken);
		System.out.println("user1" + user);
		user.getId();
		List<Note> list = notesRepository.findAll().stream().filter(e -> e.getUser().getId() == user.getId())
				.collect(Collectors.toList());

		list = list.stream().sorted((list1, list2) -> list1.getDescription().compareTo(list2.getDescription()))
				.collect(Collectors.toList());
		return list;
	}

	/**
	 * @return Function to sort notes by Date
	 *
	 *************************************************************************************************/
	public List<Note> sortByDate(String token) {
		String usertoken = tokenUtility.getUserToken(token);
		System.out.println(usertoken);
		if (usertoken.isEmpty()) {
			throw new TokenException(Messages.INVALID_TOKEN);
		}
		User user = repository.findByEmail(usertoken);
		System.out.println("user1" + user);
		user.getId();
		List<Note> list = notesRepository.findAll().stream().filter(e -> e.getUser().getId() == user.getId())
				.collect(Collectors.toList());

		list = list.stream()
				.sorted((list1, list2) -> list1.getNoteRegistrationDate().compareTo(list2.getNoteRegistrationDate()))
				.collect(Collectors.toList());
		return list;
	}
}

/*
 * 
 * /**
 * 
 * @return Function to find a note
 *
 ************************************************************************************************
 * public Responses findNote(int id, String token) { String userToken =
 * tokenUtility.getUserToken(token);
 * 
 * if (userToken.isEmpty()) { throw new TokenException(Messages.INVALID_TOKEN);
 * }
 * 
 * User user = repository.findByEmail(userToken);
 * 
 * if (user == null) { throw new
 * UserNotFoundException(Messages.USER_NOT_EXISTING); }
 * 
 * Note note = notesRepository.findById(id);
 * 
 * return new Responses(Messages.OK, "User Registration ", note); }
 * 
 *//**
	 * @return Function to Show a particular user's note
	 *
	 *************************************************************************************************//*
																										 * public
																										 * List<Note>
																										 * showUserNotes
																										 * (int id,
																										 * String token)
																										 * { String
																										 * usertoken =
																										 * tokenUtility.
																										 * getUserToken(
																										 * token); if
																										 * (usertoken.
																										 * isEmpty()) {
																										 * throw new
																										 * TokenException
																										 * (Messages.
																										 * INVALID_TOKEN
																										 * ); } User
																										 * user =
																										 * repository.
																										 * findByEmail(
																										 * usertoken);
																										 * Note note =
																										 * notesRepository
																										 * .findById(id)
																										 * ; return
																										 * notesRepository
																										 * .findAll(); }
																										 */