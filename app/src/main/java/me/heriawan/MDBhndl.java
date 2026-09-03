package me.heriawan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import me.heriawan.obj.Logs;

public class MDBhndl extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "parittaDB.db";
    private static final String LOGS_TABLE_NAME = "Logs";

    private static final String SQL_CREATE_LOGS = "CREATE TABLE "+ LOGS_TABLE_NAME +" ( " +
            "logid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "date TEXT," +
            "title TEXT, " +
            "caption TEXT)";

    private static final String LOGS_LOAD_Q = "SELECT * FROM " + LOGS_TABLE_NAME;

    //initialize the database
    public MDBhndl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_LOGS);
    }
    /* LogS */
    public Logs logbyid(int id) {
        Logs sets = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(LOGS_LOAD_Q + " WHERE setid  = '" + String.valueOf(id) + "'", null);
        if (cursor.moveToFirst()) { cursor.moveToFirst();
            sets = new Logs(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3) );
            cursor.close();
        }
        db.close(); return sets;
    }
    public ArrayList<Logs> logsget() {
        ArrayList<Logs> list=new ArrayList<Logs>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(LOGS_LOAD_Q+ " ORDER BY date DESC", null);
        while (cursor.moveToNext()) {
            list.add(new Logs(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3) ));
        } cursor.close(); db.close(); return list;
    }
    public int logadd(Logs s) {
        SQLiteDatabase db = this.getWritableDatabase();
        long insertId = db.insert(LOGS_TABLE_NAME, null, s.getHash("I"));
        db.close();
        return (int) insertId;
    }
    public boolean logupdate(int ID, Logs s) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(LOGS_TABLE_NAME, s.getHash("U"), "logid=" + ID, null) > 0;
    }
    public boolean logrem(int ID) {
        boolean result = false;
        String query = LOGS_LOAD_Q + " WHERE logid='" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int idondb= Integer.parseInt(cursor.getString(0));
            db.delete(LOGS_TABLE_NAME, "logid=?", new String[] { String.valueOf(idondb) });
            cursor.close();
            result = true;
        } db.close();
        return result;
    }
    public boolean logremall() {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LOGS_TABLE_NAME, "logid>?",new String[] { "0"} );
        return result;
    }


}