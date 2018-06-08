package com.dhanukaperera.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhanukaperera on 6/8/18.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DataBaseHelper";

    private static final String DATABASE_NAME = "notes.db";
    private static final Integer DATABASE_VERSION = 5;

    private static final String TABLE_NAME = "notes";
    private static final String COL_ID = "_id";
    private static final String COL_TITLE = "title";
    private static final String COL_DESCRIPTION = "description";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCreateTable = " CREATE TABLE " + TABLE_NAME +
                " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_TITLE + " TEXT," +
                COL_DESCRIPTION + " TEXT)";
        sqLiteDatabase.execSQL(queryCreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String queryDropTable = " DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(queryDropTable);
        onCreate(sqLiteDatabase);

    }

    public void insertNoteToDB(Note note) {
        ContentValues newNote = new ContentValues();
        newNote.put(COL_TITLE, note.getTitle());
        newNote.put(COL_DESCRIPTION, note.getDescription());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,newNote);
//        long insertData = 0;
//
//        try {
//            Log.d(TAG, "insertNoteToDB: inserting note to db");
//            insertData = db.insert(TABLE_NAME, null, newNote);
//            db.close();
//        } catch (Exception e) {
//            Log.e(TAG, "insertNoteToDB: insert fail to db", e);
//            e.printStackTrace();
//        }

//        if (insertData != -1) {
//            return true;
//        } else {
//            return false;
//        }
    }

    public List<Note> getNotes(){
        String queryGetAllNotes = " SELECT "+ COL_ID+ " , " + COL_TITLE + " , " + COL_DESCRIPTION + " FROM " + TABLE_NAME;
        SQLiteDatabase db = getWritableDatabase();

        List<Note> notesList = new ArrayList<>();
        Cursor c = db.rawQuery(queryGetAllNotes,null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COL_ID)) != null &&
                    c.getString(c.getColumnIndex(COL_TITLE))!=null &&
                    c.getString(c.getColumnIndex(COL_DESCRIPTION))!=null){

                Note note = new Note();
                note.set_id(c.getInt(c.getColumnIndex(COL_ID)));
                note.setTitle(c.getString(c.getColumnIndex(COL_TITLE)));
                note.setDescription(c.getString(c.getColumnIndex(COL_DESCRIPTION)));

                notesList.add(note);
            }
            c.moveToNext();
        }
        db.close();
        return notesList;
    }
}
