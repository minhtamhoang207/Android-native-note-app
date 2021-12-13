package com.example.androidnote.UI;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.androidnote.Model.Notes;
import com.example.androidnote.R;
import com.example.androidnote.ViewModel.NoteViewModel;
import com.example.androidnote.databinding.ActivityUpdateNoteBinding;

import java.util.Date;


public class UpdateNoteActivity extends AppCompatActivity {

    ActivityUpdateNoteBinding updateNoteBinding;
    NoteViewModel noteViewModel;
    int id;
    String title;
    String subtitle;
    String content;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateNoteBinding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(updateNoteBinding.getRoot());
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
        subtitle = getIntent().getStringExtra("subtitle");
        content = getIntent().getStringExtra("content");
        priority = getIntent().getStringExtra("priority");

        updateNoteBinding.edtTitle.setText(title);
        updateNoteBinding.edtSubtitle.setText(subtitle);
        updateNoteBinding.edtContent.setText(content);


        switch (priority){
            case "1":
                updateNoteBinding.priorityGreen.setImageResource(R.drawable.ic_baseline_check_24);
                break;
            case "2":
                updateNoteBinding.priorityYellow.setImageResource(R.drawable.ic_baseline_check_24);
                break;
            case "3":
                updateNoteBinding.priorityRed.setImageResource(R.drawable.ic_baseline_check_24);
                break;
        }

        updateNoteBinding.priorityGreen.setOnClickListener(v -> {
            updateNoteBinding.priorityGreen.setImageResource(R.drawable.ic_baseline_check_24);
            updateNoteBinding.priorityYellow.setImageResource(0);
            updateNoteBinding.priorityRed.setImageResource(0);
            priority = "1";
        });

        updateNoteBinding.priorityYellow.setOnClickListener(v -> {
            updateNoteBinding.priorityYellow.setImageResource(R.drawable.ic_baseline_check_24);
            updateNoteBinding.priorityGreen.setImageResource(0);
            updateNoteBinding.priorityRed.setImageResource(0);
            priority = "2";
        });

        updateNoteBinding.priorityRed.setOnClickListener(v -> {
            updateNoteBinding.priorityRed.setImageResource(R.drawable.ic_baseline_check_24);
            updateNoteBinding.priorityGreen.setImageResource(0);
            updateNoteBinding.priorityYellow.setImageResource(0);
            priority = "3";
        });

        updateNoteBinding.floatingActionButton.setOnClickListener(v -> {
            String title = updateNoteBinding.edtTitle.getText().toString();
            String subtitle = updateNoteBinding.edtSubtitle.getText().toString();
            String content = updateNoteBinding.edtContent.getText().toString();

            editNote(id, title, subtitle, content, priority);
            finish();
        });
    }

    private void editNote(int id, String title, String subtitle, String content, String priority) {
        Date date = new Date();
        String createDate = DateFormat.format("dd/MM/yyyy", date.getTime()).toString();
        Notes note = new Notes();
        note.id = id;
        note.title = title;
        note.subtitle = subtitle;
        note.notes = content;
        note.priority = priority;
        note.createDate = createDate;


        if (!subtitle.isEmpty() && !title.isEmpty() && !content.isEmpty()) {
            noteViewModel.editNote(note);
            Toast.makeText(this, "Updated success <3", Toast.LENGTH_LONG).show();
        }
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.btnDelete){
            noteViewModel.deleteNote(id);
            Toast.makeText(this, "Deleted note", Toast.LENGTH_LONG).show();
            finish();
        }
        return true;

    }
}
