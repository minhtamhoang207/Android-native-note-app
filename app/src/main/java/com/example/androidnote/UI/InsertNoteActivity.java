package com.example.androidnote.UI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.format.DateFormat;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProviders;

import com.example.androidnote.Model.Notes;
import com.example.androidnote.R;
import com.example.androidnote.ViewModel.NoteViewModel;
import com.example.androidnote.databinding.ActivityAddNoteBinding;

import java.util.Date;


@SuppressLint("CustomSplashScreen")
public class InsertNoteActivity extends AppCompatActivity {

    ActivityAddNoteBinding addNoteBinding;
    NoteViewModel noteViewModel;
    String priority = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addNoteBinding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(addNoteBinding.getRoot());
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        addNoteBinding.priorityGreen.setOnClickListener(v -> {
            addNoteBinding.priorityGreen.setImageResource(R.drawable.ic_baseline_check_24);
            addNoteBinding.priorityYellow.setImageResource(0);
            addNoteBinding.priorityRed.setImageResource(0);
            priority = "1";
        });

        addNoteBinding.priorityYellow.setOnClickListener(v -> {
            addNoteBinding.priorityYellow.setImageResource(R.drawable.ic_baseline_check_24);
            addNoteBinding.priorityGreen.setImageResource(0);
            addNoteBinding.priorityRed.setImageResource(0);
            priority = "2";
        });

        addNoteBinding.priorityRed.setOnClickListener(v -> {
            addNoteBinding.priorityRed.setImageResource(R.drawable.ic_baseline_check_24);
            addNoteBinding.priorityGreen.setImageResource(0);
            addNoteBinding.priorityYellow.setImageResource(0);
            priority = "3";
        });

        addNoteBinding.floatingActionButton.setOnClickListener(v -> {
            Editable subtitle = addNoteBinding.edtSubtitle.getText();
            Editable title = addNoteBinding.edtTitle.getText();
            Editable content = addNoteBinding.edtContent.getText();

            createNote(subtitle, title, content);
        });
    }

    private void createNote(Editable subtitle, Editable title, Editable content) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM dd, YYYY", date.getTime());
        Notes note = new Notes();
        note.subtitle = subtitle.toString();
        note.title = title.toString();
        note.notes = content.toString();
        note.createDate = sequence.toString();
        note.priority = priority;

        if (!subtitle.toString().isEmpty() && !title.toString().isEmpty() && !content.toString().isEmpty()) {
            noteViewModel.insertNote(note);
            Toast.makeText(this, "Success <3", Toast.LENGTH_LONG).show();
        }
        finish();
    }
}
