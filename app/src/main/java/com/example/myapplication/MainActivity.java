package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    List<User> users = new ArrayList<>();
    User buyer = new User("marijana", "123", "marijana", "vacic", "0692314344", "avalska",false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        users.add(buyer);
        User newUser = (User) getIntent().getSerializableExtra("newUser");

        if(newUser!=null){
            users.add(newUser);
            Toast.makeText(getApplicationContext(), "new user "+newUser.username, Toast.LENGTH_SHORT).show();

        }

    }

    public void login(View view) {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        for (int i=0;i<users.size();i++) {
            User u = users.get(i);
            if (u.username.equals(username.getText().toString()) && u.password.equals(password.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this,MainActivity2.class);
                intent.putExtra("loggedInUser",  u);
                intent.putExtra("usersList", (Serializable) users);
                startActivity(intent);
                return; // Exit the loop once a match is found
            }
        }

        // If no matching user is found
        Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
    }

    public void register(View view) {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
        return;
        // Implement registration logic if needed
    }
}
