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
import androidx.lifecycle.ViewModelProvider;

import com.example.istudentproject.R;
import com.example.istudentproject.viewmodel.RegisterViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

// RegisterActivity.java
public class RegisterActivity extends AppCompatActivity {

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

        registerButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!termsCheckBox.isChecked()) {
                Toast.makeText(this, "Please accept the terms of service and privacy policy", Toast.LENGTH_SHORT).show();
                return;
            }

            registerViewModel.registerUser(email, password);
        });

        registerViewModel.getRegistrationSuccess().observe(this, registrationSuccess -> {
            if (registrationSuccess) {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                finish();
            }
        });

        registerViewModel.getRegistrationErrorMessage().observe(this, registrationErrorMessage -> {
            if (!TextUtils.isEmpty(registrationErrorMessage)) {
                Toast.makeText(this, "Registration failed: " + registrationErrorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        signInTextViewFromRegister.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }
}


