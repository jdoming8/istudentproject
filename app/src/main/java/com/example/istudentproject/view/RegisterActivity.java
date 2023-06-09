package com.example.istudentproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.istudentproject.R;
import com.example.istudentproject.model.RegisterModel;
import com.example.istudentproject.viewmodel.RegisterViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

// RegisterActivity.java
public class RegisterActivity extends AppCompatActivity implements RegisterModel.OnRegistrationListener {

    private EditText emailEditText, passwordEditText;
    private CheckBox termsCheckBox;
    private Button registerButton;
    private TextView alreadyHaveAccountTextView, signInTextViewFromRegister;

    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        emailEditText = findViewById(R.id.register_email_edit_text);
        passwordEditText = findViewById(R.id.register_password_edit_text);
        termsCheckBox = findViewById(R.id.terms_checkbox);
        registerButton = findViewById(R.id.register_button);
        alreadyHaveAccountTextView = findViewById(R.id.already_have_account_text_view);
        signInTextViewFromRegister = findViewById(R.id.sign_in_text_view_from_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!termsCheckBox.isChecked()) {
                    Toast.makeText(RegisterActivity.this, "Please accept the terms of service and privacy policy", Toast.LENGTH_SHORT).show();
                    return;
                }

                registerViewModel.register(email, password, RegisterActivity.this);
            }
        });

        signInTextViewFromRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onRegistrationSuccess() {
        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity.this, TaskListActivity.class));
        finish();
    }

    @Override
    public void onRegistrationFailure(String errorMessage) {
        Toast.makeText(this, "Registration failed: " + errorMessage, Toast.LENGTH_SHORT).show();
    }
}

