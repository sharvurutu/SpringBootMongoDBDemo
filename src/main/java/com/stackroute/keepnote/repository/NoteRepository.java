package com.stackroute.keepnote.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.keepnote.model.Note;

@Transactional
public interface NoteRepository extends MongoRepository<Note, Integer> {

	public Note findByNoteId(int noteId);

}
