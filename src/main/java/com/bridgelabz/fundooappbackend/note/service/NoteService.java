package com.bridgelabz.fundooappbackend.note.service;
import java.util.List;

import org.springframework.web.bind.annotation.RequestHeader;

import com.bridgelabz.fundooappbackend.note.dto.NoteDto;
import com.bridgelabz.fundooappbackend.note.model.Note;
import com.bridgelabz.fundooappbackend.note.noteresponse.Response;

/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Note Service Interface
 *
 ***********************************************************************************************************/
public interface NoteService {
	public Response addNewNote(NoteDto noteDto,String token);
	public Response updateNote(int id,NoteDto updateNoteDto, String token); 
	public Response deleteNote(int id, String token); 
	//public Responses findNote(int id,String token);
	//public List<Note> showUserNotes(int id,String token);
	public List<Note> getAllNotes(@RequestHeader String token);
	public List<Note> sortByDescription(String token);
	public Response pinAndUnpin( int id,String token);

}
