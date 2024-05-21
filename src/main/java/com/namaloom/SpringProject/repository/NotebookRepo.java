package com.namaloom.SpringProject.repository;

import com.namaloom.SpringProject.entity.Note;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface NotebookRepo extends MongoRepository<Note, ObjectId> {

}
