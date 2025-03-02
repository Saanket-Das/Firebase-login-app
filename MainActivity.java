package com.example.firebaseauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private TextView textViewEmail;
    private Button buttonLogout, buttonLogin, buttonRegister;
    private EditText editTextEmail, editTextPassword;
    private CheckBox captchaCheckBox; // Added Captcha CheckBox
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewEmail = findViewById(R.id.textViewEmail);
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        captchaCheckBox = findViewById(R.id.captchaCheckBox); // Initializing Captcha CheckBox
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            textViewEmail.setText(user.getEmail());
        } else {
            textViewEmail.setText("Not logged in");
        }

        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (email.isEmpty()) {
                editTextEmail.setError("Email required");
                return;
            }
            if (password.isEmpty()) {
                editTextPassword.setError("Password required");
                return;
            }
            if (!captchaCheckBox.isChecked()) { // Checking Captcha
                Toast.makeText(MainActivity.this, "Please verify you're not a robot", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        textViewEmail.setText(email);
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        });

        buttonRegister.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RegisterActivity.class)));

        buttonLogout.setOnClickListener(v -> {
            mAuth.signOut();
            textViewEmail.setText("Not logged in");
            Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
        });
    }
}
