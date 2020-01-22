package com.bridgelabz.fundooappbackend.note.service;
import com.bridgelabz.fundooappbackend.note.dto.LabelDto;
import com.bridgelabz.fundooappbackend.note.dto.UpdateLabelDto;
import com.bridgelabz.fundooappbackend.note.response.Responses;

public interface LabelService {
	public Responses addLabel(LabelDto labelDto,String token);
	public Responses updateLabel(UpdateLabelDto updateLabelDto, String token);
	public Responses deleteLabel(int id,String token);
	public Responses findLabel(int id, String token);
}
