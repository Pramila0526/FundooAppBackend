package com.bridgelabz.fundooappbackend.note.service;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestHeader;
import com.bridgelabz.fundooappbackend.note.dto.NoteDto;
import com.bridgelabz.fundooappbackend.note.model.Note;
import com.bridgelabz.fundooappbackend.note.response.Responses;

/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Note Service Interface
 *
 ***********************************************************************************************************/
public interface NoteService {
	public Responses addNewNote(NoteDto noteDto,String token);
	public Responses updateNote(int id,NoteDto updateNoteDto, String token); 
	public Responses deleteNote(int id, String token); 
	//public Responses findNote(int id,String token);
	//public List<Note> showUserNotes(int id,String token);
	public List<Note> getAllNotes(@RequestHeader String token);
	public List<Note> sortByDescription(String token);
	public Responses pinAndUnpin( int id,String token);

}
