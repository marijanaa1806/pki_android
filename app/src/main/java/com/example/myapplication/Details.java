package com.example.myapplication;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Artikal item  = (Artikal)getIntent().getSerializableExtra("clickedImage");
        TextView naziv = findViewById(R.id.naziv);
        TextView opis = findViewById(R.id.opis);
        naziv.setText(item.name);
        opis.setText(item.about);
        String sl = item.src;
        sl = sl.replace(".jpg", "");
        int resourceId = getResources().getIdentifier(sl, "drawable", this.getPackageName());
        ImageView slika = findViewById(R.id.slika);
        slika.setImageResource(resourceId);
        // logged = (User) getIntent().getSerializableExtra("loggedInUser");

    }
    public void addOrder(){
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userJson = preferences.getString("loggedIn", null);
        Artikal item  = (Artikal)getIntent().getSerializableExtra("clickedImage");
        User logged = null;
        if (userJson != null) {
            Gson gson = new Gson();
            logged = gson.fromJson(userJson, User.class);

        }
        EditText quant =  findViewById(R.id.quantity);
        int k = Integer.parseInt(quant.getText().toString());
        boolean exists = false;
        for(Order o :MainActivity2.orders){

            if(o.status.equals("current") && o.user.username.equals(logged.username)){
                o.items.add(item);
                o.total = o.total+item.price*k;
                exists=true;
            }

        }
        if(!exists){
            List<Artikal> items = new ArrayList<>();
            items.add(item);
            Order o  = new Order(logged,items,item.price*k,"current");
            MainActivity2.orders.add(o);
        }
    }




}
