package com.example.istudentproject.viewmodel;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.istudentproject.model.pomodoro;

public class pomodoroViewModel extends ViewModel {
    private MutableLiveData<pomodoro> pomodoroLiveData = new MutableLiveData<>();
    private CountDownTimer timer;

    public void startPomodoro(long duration) {
        pomodoro myPomodoro = new pomodoro(duration);
        pomodoroLiveData.postValue(myPomodoro);

        timer = new CountDownTimer(duration * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                myPomodoro.tick();
                pomodoroLiveData.postValue(myPomodoro);
            }

            @Override
            public void onFinish() {
                pomodoroLiveData.postValue(null);
            }
        };

        timer.start();
    }

    public LiveData<pomodoro> getPomodoroLiveData() {
        return pomodoroLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (timer != null) {
            timer.cancel();
        }
    }


    public void stopPomodoro() {
        pomodoroLiveData.postValue(null);
    }

}
