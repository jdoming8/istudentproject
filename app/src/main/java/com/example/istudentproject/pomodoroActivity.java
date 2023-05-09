package com.example.istudentproject;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class pomodoroActivity extends AppCompatActivity {
    private pomodoroViewModel viewModel;

    private TextView timerTextView;
    private ImageButton startButton;
    private ImageButton stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock);

        viewModel = new ViewModelProvider(this).get(pomodoroViewModel.class);

        timerTextView = findViewById(R.id.timerText);
        startButton = findViewById(R.id.playPauseButton);
        stopButton = findViewById(R.id.stopButton);

    }
}
