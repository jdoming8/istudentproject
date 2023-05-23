package com.example.istudentproject.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.istudentproject.R;
import com.example.istudentproject.model.TaskModel;
import com.example.istudentproject.viewmodel.TaskViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {
    private ArrayList<TaskModel> tareas;
    private TaskAdapter tareasAdaptador;
    private ListView lvTareas;
    private ActivityResultLauncher<Intent> crearTareaLauncher;
    private TaskViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list);

        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        lvTareas = (ListView) findViewById(R.id.lvLlistaTareas);
        tareas = new ArrayList<>();
        tareasAdaptador = new TaskAdapter(this, tareas);
        lvTareas.setAdapter(tareasAdaptador);

        lvTareas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TaskModel tarea = tareas.get(position);
                viewModel.deleteTask(tarea);
                tareas.remove(position);
                tareasAdaptador.notifyDataSetChanged();
                return true;
            }
        });

        crearTareaLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                TaskModel nuevaTarea = (TaskModel) data.getSerializableExtra("nuevaTarea");
                                if (nuevaTarea != null) {
                                    tareas.add(nuevaTarea);
                                    tareasAdaptador.notifyDataSetChanged();
                                    lvTareas.smoothScrollToPosition(0);
                                    viewModel.uploadTask(nuevaTarea);
                                }
                            }
                        }
                    }
                });

        Button añadirTarea = findViewById(R.id.btnAñadirTarea);
        añadirTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskListActivity.this, AddTaskActivity.class);
                crearTareaLauncher.launch(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_task_list:
                        // Already in TaskListActivity, no need to start a new instance
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
