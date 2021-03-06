package com.example.androidnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidnote.Adapter.NotesAdapter;
import com.example.androidnote.ViewModel.NoteViewModel;
import com.example.androidnote.databinding.ActivityMainBinding;
import com.example.androidnote.UI.InsertNoteActivity;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    NoteViewModel noteViewModel;
    RecyclerView recyclerView;
    NotesAdapter notesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setTitle("My android note app");
        setContentView(activityMainBinding.getRoot());

        recyclerView = activityMainBinding.noteRecycleView;
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        activityMainBinding.floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, InsertNoteActivity.class);
            startActivity(intent);
        });

        noteViewModel.getAllNote.observe(this, notes -> {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
            notesAdapter = new NotesAdapter(MainActivity.this, notes);
            recyclerView.setAdapter(notesAdapter);
        });
    }
}