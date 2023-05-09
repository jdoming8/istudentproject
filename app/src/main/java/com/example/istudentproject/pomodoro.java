package com.example.istudentproject;

public class pomodoro {
    private long duration;
    private long timeRemaining;

    public pomodoro(long duration) {
        this.duration = duration;
        this.timeRemaining = duration;
    }

    public void tick() {
        timeRemaining -= 1;
    }

    public boolean isFinished() {
        return timeRemaining <= 0;
    }

    public long getDuration() {
        return duration;
    }

    public long getTimeRemaining() {
        return timeRemaining;
    }
}
