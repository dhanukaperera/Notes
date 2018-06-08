package com.dhanukaperera.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NotesMain extends AppCompatActivity {

    Button btnAdd;
    ListView notesList;
    DataBaseHelper mDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_main);

        btnAdd = (Button) findViewById(R.id.btn_add);
        notesList = (ListView) findViewById(R.id.listView_notes);
        mDataBaseHelper = new DataBaseHelper(this);

        populateList();
    }


    public  void populateList(){
        List<Note> rawData = mDataBaseHelper.getNotes();
        List<String> data = new ArrayList<>();

        for(Note n:rawData){
            data.add(n.getTitle());
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        notesList.setAdapter(stringArrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesMain.this,AddNotes.class);
                startActivity(intent);
            }
        });


        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
               displayToast(name);
               Intent intent = new Intent(NotesMain.this,ViewNotes.class);
               intent.putExtra("data",name);
               startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        populateList();
    }

    public void displayToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
