package com.bridgelabz.fundooappbackend.note.message;

/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Messages for identifying the Status of
 *         Implementation
 *
 *********************************************************************************************************/

public class Messages {
	
	public static final String INVALID_TOKEN = "Invalid Token**";
	
	// Status Code
		public static final String OK = "200";
		
		public static final String BAD_REQUEST = "400";
		
		public static final String NOT_FOUND = "404";
		
		// For Notes
		public static final String NOTE_CREATED = "Notes created Successfully!!";

		public static final String NOTE_UPDATED = "Note updated Successfully!!";

		public static final String NOTE_DELETED = "Note Deleted";

		// For Labels
		public static final String LABEL_CREATED = "Label created Successfully!!";
		
		public static final String LABEL_UPDATED = "Label updated Successfully!!";

		public static final String LABEL_DELETED = "Label Deleted";

		//  For users
		public static final String USER_NOT_EXISTING = "User Doesn't Exists";
		
	//  For collaboration
			public static final String PING = "Note Pinged!";
			
			public static final String UNPING = "Note UNPinged!";


}