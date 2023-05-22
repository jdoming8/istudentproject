package com.example.istudentproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.istudentproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {
    private EditText noteEditText;
    private EditText filenameEditText;
    private Button saveButton;
    private Button loadButton;
    private Button listButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notas);
        
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_task_list:
                        Intent intent1 = new Intent(NotesActivity.this, TaskListActivity.class);
                        startActivity(intent1);
                        return true;
                    case R.id.action_pomodoro:
                        Intent intent2 = new Intent(NotesActivity.this, pomodoroActivity.class);
                        startActivity(intent2);
                        return true;
                    case R.id.action_notifications:
                        Intent intent3 = new Intent(NotesActivity.this, NotificationsActivity.class);
                        startActivity(intent3);
                        return true;
                    case R.id.action_settings:
                        Intent intent4 = new Intent(NotesActivity.this, ConfiguracionesActivity.class);
                        startActivity(intent4);
                        return true;
                    case R.id.action_notes:
                        Intent intent5 = new Intent(NotesActivity.this, NotesActivity.class);
                        startActivity(intent5);
                        return true;
                    default:
                        return false;
                }
            }
        });

        noteEditText = findViewById(R.id.noteEditText);
        filenameEditText = findViewById(R.id.filenameEditText);
        saveButton = findViewById(R.id.saveButton);
        loadButton = findViewById(R.id.loadButton);
        listButton = findViewById(R.id.listButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = noteEditText.getText().toString();
                String filename = filenameEditText.getText().toString();

                if (note.isEmpty()) {
                    Toast.makeText(NotesActivity.this, "Please enter a note", Toast.LENGTH_SHORT).show();
                } else if (filename.isEmpty()) {
                    Toast.makeText(NotesActivity.this, "Please enter a filename", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        FileOutputStream outputStream = openFileOutput(filename, MODE_PRIVATE);
                        outputStream.write(note.getBytes());
                        outputStream.close();
                        Toast.makeText(NotesActivity.this, "Note saved as " + filename, Toast.LENGTH_SHORT).show();
                        noteEditText.setText("");
                        filenameEditText.setText("");
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(NotesActivity.this, "Note not saved", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = filenameEditText.getText().toString();

                if (filename.isEmpty()) {
                    Toast.makeText(NotesActivity.this, "Please enter a filename", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        FileInputStream inputStream = openFileInput(filename);
                        byte[] bytes = new byte[inputStream.available()];
                        inputStream.read(bytes);
                        inputStream.close();
                        String note = new String(bytes);
                        noteEditText.setText(note);
                        Toast.makeText(NotesActivity.this, "Note loaded from " + filename, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(NotesActivity.this, "Note not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> filenames = getNoteFilenames();
                StringBuilder fileList = new StringBuilder();
                for (String filename : filenames) {
                    fileList.append(filename).append("\n");
                }
                Toast.makeText(NotesActivity.this, "Note filenames:\n" + fileList.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<String> getNoteFilenames() {
        List<String> filenames = new ArrayList<>();
        File directory = getFilesDir();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                filenames.add(file.getName());
            }
        }
        return filenames;
    }


}
