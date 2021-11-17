package com.example.androidnote.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.androidnote.Dao.NotesDao;
import com.example.androidnote.Database.NotesDatabase;
import com.example.androidnote.Model.Notes;

import java.util.List;

public class NoteRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getAllNote;

    public NoteRepository(Application application){
        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);
        notesDao = database.notesDao();
        getAllNote = notesDao.getAllNote();
    }

    public void insertNote(Notes notes){
        notesDao.insertNote(notes);
    }

    public void deleteNote(int id){
        notesDao.deleteNote(id);
    }

    public void updateNote(Notes notes){
        notesDao.updateNote(notes);
    }
}
