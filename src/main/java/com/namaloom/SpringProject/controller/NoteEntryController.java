package com.namaloom.SpringProject.controller;


import com.namaloom.SpringProject.entity.Note;
import com.namaloom.SpringProject.service.NotebookEntryService;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/notebook")
public class NoteEntryController {

    @Autowired
    private NotebookEntryService notebookEntryService;


    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(notebookEntryService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody Note note){
        try {
            notebookEntryService.saveNote(note);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable ObjectId id){
        Note returnedNote = notebookEntryService.getById(id).orElse(null);
        if (returnedNote != null){
            return new ResponseEntity<>(returnedNote, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteNoteById(@PathVariable ObjectId id) {
        if (notebookEntryService.getById(id).orElse(null) != null) {
            notebookEntryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateNote(@PathVariable ObjectId id, @RequestBody Note newNote){
        try {
            Note oldNote = notebookEntryService.getById(id).orElse(null);

            if (oldNote != null) {
                oldNote.setTitle(newNote.getTitle() != null && !newNote.getTitle().equals("") ? newNote.getTitle() : oldNote.getTitle());
                oldNote.setContent(newNote.getContent() != null && newNote.getContent().equals("") ? newNote.getContent() : oldNote.getContent());
                notebookEntryService.saveNote(oldNote);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
