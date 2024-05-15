package com.onss.test.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotesSavingRequest {
	private String subject;
	private String notesTitle;
	private String description;
}