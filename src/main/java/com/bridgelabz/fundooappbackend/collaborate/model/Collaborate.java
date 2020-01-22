package com.bridgelabz.fundooappbackend.collaborate.model;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Collaborate Model
 *
 ***********************************************************************************************************/
@Entity
public class Collaborate implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	private int noteId;
	
	@NotNull
	private String SenderMail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getSenderMail() {
		return SenderMail;
	}

	public void setSenderMail(String senderMail) {
		SenderMail = senderMail;
	}

	public Collaborate(int id, @NotNull int noteId, @NotNull String senderMail) {
		super();
		this.id = id;
		this.noteId = noteId;
		SenderMail = senderMail;
	}

	public Collaborate() {
		super();
	}

	@Override
	public String toString() {
		return "Collaborate [id=" + id + ", noteId=" + noteId + ", SenderMail=" + SenderMail + "]";
	}	
}