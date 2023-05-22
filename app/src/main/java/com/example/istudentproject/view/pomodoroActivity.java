package com.example.istudentproject.view;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.istudentproject.R;
import com.example.istudentproject.model.pomodoro;
import com.example.istudentproject.viewmodel.pomodoroViewModel;

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

        viewModel = new ViewModelProvider(this).get(pomodoroViewModel.class);

        timerTextView = findViewById(R.id.timerText);
        startButton = findViewById(R.id.playButton);
        stopButton = findViewById(R.id.stopButton);
        pauseButton = findViewById(R.id.pauseButton);
        progressBar = findViewById(R.id.progressBar);

        startButton.setOnClickListener(v -> startPomodoro());

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
        timer = new CountDownTimer(25*60*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished / 1000;
                updateTimerTextView();
                updateProgressBar();
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
            if(timeRemaining==0){
                timeRemaining = 25 * 60 ; // 25 minutes in seconds
                timerTextView.setText("25:00");
            }
            timer = new CountDownTimer(timeRemaining * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeRemaining = millisUntilFinished / 1000;
                    updateTimerTextView();
                    updateProgressBar();
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
        timeRemaining=0;
        isTimerRunning = false;
        updateProgressBar();
    }


    private void updateTimerTextView() {
        long minutes = timeRemaining / 60;
        long seconds = timeRemaining % 60;
        String timeString = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timerTextView.setText(timeString);

        int progress = (int) (timeRemaining * 10); // Convertimos el timeRemaining a un valor proporcional
        progressBar.setProgress(progress);
    }

    private void updateProgressBar() {
        int progress = (int) (progressBar.getMax() - timeRemaining);
        progressBar.setProgress(progress);
    }
}
