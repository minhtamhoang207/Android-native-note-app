package com.example.androidnote.Adapter;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidnote.MainActivity;
import com.example.androidnote.Model.Notes;
import com.example.androidnote.R;
import com.example.androidnote.UI.UpdateNoteActivity;
import com.example.androidnote.ViewModel.NoteViewModel;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    MainActivity mainActivity;
    List<Notes> notesList;
    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notesList = notes;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Notes note = notesList.get(position);
        switch (note.priority) {
            case "1":
                holder.priority.setBackgroundResource(R.drawable.green_circle);
                break;
            case "2":
                holder.priority.setBackgroundResource(R.drawable.yellow_circle);
                break;
            case "3":
                holder.priority.setBackgroundResource(R.drawable.red_circle);
                break;
        }
        holder.title.setText(note.title);
        holder.subtitle.setText(note.subtitle);
        holder.content.setText(note.notes);
        holder.cardItem.setOnClickListener( v -> {
            Intent intent = new Intent(mainActivity, UpdateNoteActivity.class);
            intent.putExtra("id",note.id);
            intent.putExtra("title",note.title);
            intent.putExtra("subtitle",note.subtitle);
            intent.putExtra("content",note.notes);
            intent.putExtra("priority",note.priority);
            mainActivity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder{

        TextView title, subtitle, content;
        View priority, cardItem;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            subtitle = itemView.findViewById(R.id.note_subtitle);
            content = itemView.findViewById(R.id.note_content);
            priority = itemView.findViewById(R.id.note_item_priority);
            cardItem = itemView.findViewById(R.id.card_item);
        }
    }
}
