package com.example.istudentproject.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.istudentproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class TaskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_task_list:
                        Intent intent1 = new Intent(TaskListActivity.this, TaskListActivity.class);
                        startActivity(intent1);
                        return true;
                    case R.id.action_pomodoro:
                        Intent intent2 = new Intent(TaskListActivity.this, pomodoroActivity.class);
                        startActivity(intent2);
                        return true;
                    case R.id.action_notifications:
                        Intent intent3 = new Intent(TaskListActivity.this, NotificationsActivity.class);
                        startActivity(intent3);
                        return true;
                    case R.id.action_settings:
                        Intent intent4 = new Intent(TaskListActivity.this, ConfiguracionesActivity.class);
                        startActivity(intent4);
                        return true;
                    case R.id.action_notes:
                        Intent intent5 = new Intent(TaskListActivity.this, NotesActivity.class);
                        startActivity(intent5);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }


}