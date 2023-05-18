package com.application.week8media_class2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.week8media_class2.database.AppDatabase;
import com.application.week8media_class2.database.User;

public class SignupActivity extends AppCompatActivity {
    EditText fullnameEditText;
    EditText usernameEditText;
    EditText passwordEditText;
    EditText confirmPasswordEditText;
    Button saveButton;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullnameEditText = findViewById(R.id.fullname);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirm_password);
        saveButton = findViewById(R.id.save);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "UserDatabase").build();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = fullnameEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User();
                user.fullname = fullname;
                user.username = username;
                user.password = password;
                new Thread(() -> db.userDao().insertUser(user)).start();
            }
        });
    }
}
