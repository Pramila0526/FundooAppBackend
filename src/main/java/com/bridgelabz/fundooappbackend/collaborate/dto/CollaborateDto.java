package com.bridgelabz.fundooappbackend.collaborate.dto;
/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Collaborate DTO 
 *
 ***********************************************************************************************************/
public class CollaborateDto {
	private int noteId;
	
	private String receiverMail;

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getReceiverMail() {
		return receiverMail;
	}

	public void setReceiverMail(String receiverMail) {
		this.receiverMail = receiverMail;
	}

	@Override
	public String toString() {
		return "CollaborateDto [noteid=" + noteId + ", receiverMail=" + receiverMail + "]";
	}

	public CollaborateDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CollaborateDto(int noteId, String receiverMail) {
		super();
		this.noteId = noteId;
		this.receiverMail = receiverMail;
	}	
}
