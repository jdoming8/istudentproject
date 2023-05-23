package com.example.istudentproject.viewmodel;

import android.widget.ArrayAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.istudentproject.model.TaskModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class TaskViewModel extends ViewModel {
    private MutableLiveData<List<TaskModel>> listaTareasLiveData;
    private ArrayList<TaskModel> tareas;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private String currentUserId;

    public TaskViewModel() {
        listaTareasLiveData = new MutableLiveData<>();
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        currentUserId = auth.getCurrentUser().getUid();
        tareas = new ArrayList<>();
    }

    public LiveData<List<TaskModel>> getListaTareasLiveData() {
        return listaTareasLiveData;
    }

    public ArrayList<TaskModel> getTareas() {
        return tareas;
    }

    public void uploadTask(TaskModel task) {
        CollectionReference userTasksCollection = db.collection("usuarios")
                .document(currentUserId)
                .collection("tasks");

        userTasksCollection.add(task);
    }



    public void deleteTask(TaskModel task) {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("your_collection_name");

        // Assuming the object has certain properties to identify it (e.g., name and age)
        String objectName = task.getNombre();
        String objectFecha = task.getFechaLimite();
        String objectDias = task.getDiasRecurrente();

        // Create a query to find the document matching the object properties
        Query query = collectionReference.whereEqualTo("nombre", objectName)
                .whereEqualTo("fechaLimite", objectFecha).whereEqualTo("diasRecurrente", objectDias);

        // Execute the query to get the matching documents
        query.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        // Delete each matching document
                        for (DocumentSnapshot document : queryDocumentSnapshots) {
                            document.getReference().delete();
                        }

                    } else {

                    }
                })
                .addOnFailureListener(e -> {

                });
    }
}
