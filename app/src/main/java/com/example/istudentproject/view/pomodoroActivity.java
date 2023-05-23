package com.example.istudentproject.view;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.istudentproject.R;
import com.example.istudentproject.model.pomodoro;
import com.example.istudentproject.viewmodel.pomodoroViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Locale;


public class pomodoroActivity extends AppCompatActivity {
    private pomodoroViewModel viewModel;

    private TextView timerTextView;
    private ImageButton startButton;
    private ImageButton stopButton;

    private ImageButton pauseButton;

    private ProgressBar progressBar;

    private CountDownTimer timer;
    private long timeRemaining;
    private boolean isTimerRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_task_list:
                        Intent intent1 = new Intent(pomodoroActivity.this, TaskListActivity.class);
                        startActivity(intent1);
                        return true;
                    case R.id.action_pomodoro:
                        Intent intent2 = new Intent(pomodoroActivity.this, pomodoroActivity.class);
                        startActivity(intent2);
                        return true;
                    case R.id.action_notifications:
                        Intent intent3 = new Intent(pomodoroActivity.this, NotificationsActivity.class);
                        startActivity(intent3);
                        return true;
                    case R.id.action_settings:
                        Intent intent4 = new Intent(pomodoroActivity.this, ConfiguracionesActivity.class);
                        startActivity(intent4);
                        return true;
                    case R.id.action_notes:
                        Intent intent5 = new Intent(pomodoroActivity.this, NotesActivity.class);
                        startActivity(intent5);
                        return true;
                    default:
                        return false;
                }
            }
        });


        viewModel = new ViewModelProvider(this).get(pomodoroViewModel.class);

        timerTextView = findViewById(R.id.timerText);

        startButton = findViewById(R.id.playButton);
        stopButton = findViewById(R.id.stopButton);
        pauseButton = findViewById(R.id.pauseButton);
        progressBar = findViewById(R.id.progressBar);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPomodoro();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausePomodoro();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPomodoro();
            }
        });

        initializeTimer();
    }

    private void initializeTimer() {
        timer = new CountDownTimer(0, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished / 1000;
                updateTimerTextView();
            }

            @Override
            public void onFinish() {
                stopPomodoro();
            }
        };
    }
    private void pausePomodoro() {
        if (isTimerRunning) {
            timer.cancel();
            isTimerRunning = false;
        }
    }
    private void startPomodoro() {
        if (!isTimerRunning) {
            if(timerTextView.getText().equals("00:00")){
                timeRemaining = 25 * 60 ; // 25 minutes in seconds
                timerTextView.setText("25:00");
            }
            timer = new CountDownTimer(timeRemaining * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeRemaining = millisUntilFinished / 1000;
                    updateTimerTextView();
                }

                @Override
                public void onFinish() {
                    stopPomodoro();
                }
            };
            timer.start();
            isTimerRunning = true;
        }
    }
    private void stopPomodoro() {
        timer.cancel();
        timerTextView.setText("00:00");
        isTimerRunning = false;
    }


    private void updateTimerTextView() {
        long minutes = timeRemaining / 60;
        long seconds = timeRemaining % 60;
        String timeString = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timerTextView.setText(timeString);

        int progress = (int) (timeRemaining * 10); // Convertimos el timeRemaining a un valor proporcional
        progressBar.setProgress(progress);

    }
}
