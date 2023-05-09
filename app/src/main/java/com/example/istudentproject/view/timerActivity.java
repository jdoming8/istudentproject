package com.example.istudentproject.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.istudentproject.R;

public class timerActivity extends AppCompatActivity{

    ImageButton mPlayPauseButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock);
        mPlayPauseButton = (ImageButton) findViewById(R.id.playPauseButton);

    }

    String showing;
    public void playPauseTimer(View view){
        showing = (String) mPlayPauseButton.getContentDescription(); //actualizamos si el botón muestra funcion de play o pause
        if(showing == "play") {
            mPlayPauseButton.setContentDescription("pause");
            mPlayPauseButton.setImageResource(R.drawable.pause_rojo);
        }else{   //el boton muestra pause y hay que ponerlo listo para play
            mPlayPauseButton.setContentDescription("play");
            mPlayPauseButton.setImageResource(R.drawable.play_verde);
        }
    }


}
