package com.namaloom.SpringProject.service;

import com.namaloom.SpringProject.entity.Note;
import com.namaloom.SpringProject.repository.NotebookRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
public class NotebookEntryService {

    @Autowired
    private NotebookRepo notebookRepo;

    public void saveNote(Note note){
        note.setDate(LocalDateTime.now());
        notebookRepo.save(note);
    }

    public List<Note> getAll(){
        return notebookRepo.findAll();
    }

    public Optional<Note> getById(ObjectId id){
        return notebookRepo.findById(id);
    }

    public void deleteById(ObjectId id){
        notebookRepo.deleteById(id);
    }
}
