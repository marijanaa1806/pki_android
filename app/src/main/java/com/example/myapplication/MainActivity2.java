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
    public  static List<Order> orders = new ArrayList<>();
    User logged ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        logged = (User) getIntent().getSerializableExtra("loggedInUser");
       /* User changed = (User) getIntent().getSerializableExtra("changedUser");
        if(changed!=null){
            User logged = (User) getIntent().getSerializableExtra("loggedInUser");

        }*/


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


    private void promo() {
        Intent intent = new Intent(this,PromocijeActivity.class);
        startActivity(intent);
        return;
    }

    private void torte() {
        Intent intent = new Intent(this,TorteActivity.class);
        intent.putExtra("loggedInUser",logged);
        startActivity(intent);
        return;
    }
    private void obavestenja() {

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
        Intent intent = new Intent(this,Kontakt.class);
        startActivity(intent);
        return;
    }

    private void korpa() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Order cur=null;
        for (Order o :orders){
            if(o.user.username.equals(logged.username) && o.status.equals("current"))cur=o;
        }
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_k, null);
        List<Artikal> items = cur.items;
        ListView listView = dialogView.findViewById(R.id.listViewItems);
        ArrayAdapter<Artikal> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        builder.setView(dialogView)
                .setPositiveButton("Naruci", (dialogInterface, i) -> {
//Qprodji kroz ordere i promeni status
                })
                .setNegativeButton("Zatvori", (dialogInterface, i) -> {
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void change() {
        Intent intent = new Intent(this, Change.class);
        intent.putExtra("loggedInUser",logged);
       startActivity(intent);
        return;
    }

    private void odjava() {
        Intent intent = new Intent(this,MainActivity.class);
        User logged = (User) getIntent().getSerializableExtra("loggedInUser");
        getIntent().removeExtra("loggedInUser");
        startActivity(intent);
        return;
    }
}