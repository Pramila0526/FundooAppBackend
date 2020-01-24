package com.bridgelabz.fundooappbackend.note.service;
import com.bridgelabz.fundooappbackend.note.dto.LabelDto;
import com.bridgelabz.fundooappbackend.note.noteresponse.Response;

public interface LabelService {
	public Response addLabel(LabelDto labelDto,String token);
	public Response updateLabel(int id,LabelDto updateLabelDto, String token);
	public Response deleteLabel(int id,String token);
	public Response findLabel(int id, String token);
}
