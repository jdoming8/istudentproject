package com.example.istudentproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.istudentproject.R;
import com.example.istudentproject.model.TaskModel;

public class AddTaskActivity extends AppCompatActivity {
    private EditText nombre;
    private EditText fechaLimite;
    private EditText diasRecurrentes;
    private Switch recurrente;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        nombre = findViewById(R.id.editTextNombreTarea);
        fechaLimite = findViewById(R.id.editTextFechaTarea);
        diasRecurrentes = findViewById(R.id.editTextDiasTarea);
        recurrente = findViewById(R.id.switchRecurrente);
        Button btnAñadir = findViewById(R.id.btnAñadir);

        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreText = nombre.getText().toString().trim();
                String fechaLimiteText = fechaLimite.getText().toString().trim();
                String diasRecurrentesText = diasRecurrentes.getText().toString().trim();
                boolean recurrenteText = recurrente.isChecked();

                TaskModel tarea = new TaskModel(nombreText, recurrenteText, fechaLimiteText, diasRecurrentesText);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("nuevaTarea", tarea);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
