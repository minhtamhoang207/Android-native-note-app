package com.example.androidnote.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidnote.Model.Notes;
import com.example.androidnote.Repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    public NoteRepository noteRepository;
    public LiveData<List<Notes>> getAllNote;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteRepository = new NoteRepository(application);
        getAllNote = noteRepository.getAllNote;
    }

    public void insertNote(Notes notes){
        noteRepository.insertNote(notes);
    }

    public void deleteNote(int id){
        noteRepository.deleteNote(id);
    }

    public void editNote(Notes notes){
        noteRepository.updateNote(notes);
    }
}
