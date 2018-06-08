package com.dhanukaperera.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ViewNotes extends AppCompatActivity {

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        title = (TextView) findViewById(R.id.txtView_title);

        title.setText(getIntent().getExtras().get("data").toString());


    }
}
