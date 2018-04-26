package com.stackroute.keepnote.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.repository.NoteRepository;

@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	NoteRepository noteRepository;

	@PostMapping("/create")
	public Map<String, Object> create(Note note) {
		note.setCreatedAt(LocalDateTime.now());
		note = noteRepository.save(note);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Note created successfully");
		dataMap.put("status", "1");
		dataMap.put("note", note);
		return dataMap;
	}

	@GetMapping("/read")
	public Map<String, Object> read(@RequestParam int noteId) {
		Note note = noteRepository.findByNoteId(noteId);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Note found successfully");
		dataMap.put("status", "1");
		dataMap.put("note", note);
		return dataMap;
	}

	/**
	 * GET /update --> Update a note record and save it in the database.
	 */
	@PutMapping("/update")
	public Map<String, Object> update(@RequestParam int noteId, @RequestParam String noteTitle) {
		Note note = noteRepository.findByNoteId(noteId);
		note.setNoteTitle(noteTitle);
		note = noteRepository.save(note);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Note updated successfully");
		dataMap.put("status", "1");
		dataMap.put("note", note);
		return dataMap;
	}

	/**
	 * GET /delete --> Delete a note from the database.
	 */
	@DeleteMapping("/delete")
	public Map<String, Object> delete(@RequestParam int noteId) {
		noteRepository.deleteById(noteId);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Note deleted successfully");
		dataMap.put("status", "1");
		return dataMap;
	}

	/**
	 * GET /read --> Read all note from the database.
	 */
	@GetMapping("/read-all")
	public Map<String, Object> readAll() {
		List<Note> notes = noteRepository.findAll();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Note found successfully");
		dataMap.put("totalNote", notes.size());
		dataMap.put("status", "1");
		dataMap.put("notes", notes);
		return dataMap;
	}
}
