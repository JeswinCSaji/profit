package com.example.tabapp;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class View extends AppCompatActivity {

    private ListView mListView;
    private DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        mListView = findViewById(R.id.myListView);

        // Retrieve three contents from the database
        ArrayList<String> contents = retrieveContentsFromDatabase();

        // Create an adapter for the ListView and set it
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contents);
        mListView.setAdapter(adapter);
    }

    private ArrayList<String> retrieveContentsFromDatabase() {
        // Your database query logic here
        // This is just sample data for demonstration purposes
        ArrayList<String > contents = new ArrayList<>();
        Cursor cursor = db.getData();
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            double proteinIntake = cursor.getDouble(3);
            String res = name + "------" + proteinIntake;
            contents.add(res);
        }

        return contents;
    }
}
