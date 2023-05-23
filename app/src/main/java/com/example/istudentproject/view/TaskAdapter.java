package com.example.istudentproject.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.istudentproject.R;
import com.example.istudentproject.model.TaskModel;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<TaskModel> {
    private Context context;
    private ArrayList<TaskModel> taskList;

    public TaskAdapter(Context context, ArrayList<TaskModel> taskList) {
        super(context, 0, taskList);
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_view, parent, false);
            holder = new ViewHolder();
            holder.nombre = convertView.findViewById(R.id.nombreTextView);
            holder.fechaLimite = convertView.findViewById(R.id.fechaTextView);
            holder.diasRecurrentes = convertView.findViewById(R.id.diasTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TaskModel task = taskList.get(position);

        // Set the task details in the views
        if (holder.nombre != null) {
            holder.nombre.setText(task.getNombre());
        }
        if (holder.fechaLimite != null) {
            holder.fechaLimite.setText(task.getFechaLimite());
        }
        if (holder.diasRecurrentes != null) {
            if (task.getRecurrente()) {
                holder.diasRecurrentes.setText(task.getDiasRecurrente());
            } else {
                holder.diasRecurrentes.setText("No recurrente");
            }
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView nombre;
        TextView fechaLimite;
        TextView diasRecurrentes;
    }
}