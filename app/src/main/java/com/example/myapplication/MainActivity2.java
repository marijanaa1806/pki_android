package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity2 extends AppCompatActivity {
    private static final int REQUEST_CHANGE = 1; // You can choose any unique request code
    private static final int RESULT_OK = 200; // You can choose any unique request code
    public List<Order> orders = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
   //     User logged = (User) getIntent().getSerializableExtra("loggedInUser");
        User changed = (User) getIntent().getSerializableExtra("changedUser");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int itemId = item.getItemId();

        if (itemId == R.id.promocije) {
            promo();
            return true;
        } else if (itemId == R.id.torte) {
            torte();
            return true;
        } else if (itemId == R.id.obavestenja) {
            obavestenja();
            return true;
        } else if (itemId == R.id.kontakt) {
            kontakt();
            return true;
        } else if (itemId == R.id.korpa) {
            korpa();
            return true;
        } else if (itemId == R.id.change) {
            change();
            return true;
        } else if (itemId == R.id.odjava) {
            odjava();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    // Define your methods for each menu item click
    private void promo() {
        // Handle the "Promocije" menu item click
        Intent intent = new Intent(this,PromocijeActivity.class);
        startActivity(intent);
        return;
    }

    private void torte() {
        // Handle the "Torte i kolaci" menu item click
    }
    private void obavestenja() {
        User logged = (User) getIntent().getSerializableExtra("loggedInUser");

        List<Order> myOrders = new ArrayList<>();
        for (Order o :orders){
            if(o.user.username.equals(logged.username))myOrders.add(o);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_o, null);
        ListView listView = dialogView.findViewById(R.id.listViewOrders);
        ArrayAdapter<Order> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myOrders);
        listView.setAdapter(adapter);
        builder.setView(dialogView)
                .setNegativeButton("Zatvori", (dialogInterface, i) -> {
                    // Handle negative button click
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void kontakt() {
        // Handle the "Kontakt" menu item click
        Intent intent = new Intent(this,Kontakt.class);
        startActivity(intent);
        return;
    }

    private void korpa() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_k, null);

        builder.setView(dialogView)
                .setPositiveButton("Naruci", (dialogInterface, i) -> {
                    // Handle positive button click
                    // String prezime = ((EditText)dialogView.findViewById(R.id.prezime)).getText().toString();
                   // Toast.makeText(getApplicationContext(), "Prezime je:", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Zatvori", (dialogInterface, i) -> {
                    // Handle negative button click
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CHANGE && resultCode == RESULT_OK) {
            User changedUser = (User) data.getSerializableExtra("changedUser");
            User logged = (User) getIntent().getSerializableExtra("loggedInUser");
            List<User> usersList = (List<User>) getIntent().getSerializableExtra("usersList");
            for(int i = 0;i<usersList.size();i++){
                if(usersList.get(i).username.equals(logged.username)){
                    usersList.set(i,changedUser);
                    getIntent().removeExtra("changedUser");
                    getIntent().removeExtra("loggedInUser");
                    getIntent().putExtra("loggedInUser",logged);
                    getIntent().putExtra("usersList",(Serializable) usersList);
                }
            }
        }
    }
    private void change() {
        // Handle the "Promena podataka" menu item click
        Intent intent = new Intent(this, Change.class);
        startActivityForResult(intent, REQUEST_CHANGE);

       /* startActivity(intent);
        return;*/
    }

    private void odjava() {
        // Handle the "Odjava" menu item click
        Intent intent = new Intent(this,MainActivity.class);
        getIntent().removeExtra("loggedInUser");
        startActivity(intent);
        return;
    }
}