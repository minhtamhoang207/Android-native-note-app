package com.example.androidnote.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidnote.MainActivity;
import com.example.androidnote.Model.Notes;
import com.example.androidnote.R;

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
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder{

        TextView title, subtitle, content;
        View priority;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.note_title);
            subtitle = itemView.findViewById(R.id.note_subtitle);
            content = itemView.findViewById(R.id.note_content);
            priority = itemView.findViewById(R.id.note_item_priority);
        }
    }
}
