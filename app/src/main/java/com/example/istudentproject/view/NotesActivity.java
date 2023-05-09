package com.example.istudentproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.istudentproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class NotesActivity extends AppCompatActivity {
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
    }
}
