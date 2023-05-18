package com.application.week8media_class2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.week8media_class2.database.AppDatabase;
import com.application.week8media_class2.database.User;
import com.application.week8media_class2.database.UserDao;

public class LoginActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    Button signupButton;
    private UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        signupButton = findViewById(R.id.signup);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "UserDatabase").build();
        userDao = db.userDao();
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            new AsyncTask<String, Void, User>() {
                @Override
                protected User doInBackground(String... params) {
                    return userDao.getUser(params[0], params[1]);
                }

                @Override
                protected void onPostExecute(User user) {
                    if(user != null){
                        // Login successful. Navigate to MainActivity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        // Login failed. Show an error message
                        Toast.makeText(LoginActivity.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }.execute(username, password);
        });



        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
