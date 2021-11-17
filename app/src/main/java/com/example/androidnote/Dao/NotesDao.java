package com.example.androidnote.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidnote.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM Notes_database")
    LiveData<List<Notes>> getAllNote();

    @Insert()
    void insertNote(Notes... notes);

    @Query("DELETE FROM Notes_database WHERE id = :id")
    void deleteNote(int id);

    @Update
    void updateNote(Notes notes);
}
