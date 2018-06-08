package com.dhanukaperera.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNotes extends AppCompatActivity {

    EditText tile,description;
    Button addNewNote;
    DataBaseHelper mDataBaseHelper;

    private static final String TAG = "AddNotes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        setTitle(R.string.title_addNotes);

        tile = (EditText) findViewById(R.id.editText_title);
        description = (EditText) findViewById(R.id.editText_desc);
        addNewNote = (Button) findViewById(R.id.btn_addNote);
        mDataBaseHelper =  new DataBaseHelper(this);

        addNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTitle = tile.getText().toString();
                String newDesc = description.getText().toString();

                Log.d(TAG, "onClick: newTitle : "  + newTitle + " <--> " + newDesc);

                if(newTitle.length() != 0 && newDesc.length() !=0){
                    Note newNote = new Note(newTitle,newDesc);
                    mDataBaseHelper.insertNoteToDB(newNote);
                    displayToast("New Note added successfully");
                    finish();
//                    if(addNewNote){
//                        displayToast("New Note added successfully");
//                    }else{
//                        displayToast("Error, note couldn't add");
//                    }

                } else{
                    displayToast("Note Title and Note Description can't be empty");
                }

            }
        });





    }

    public void displayToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}

