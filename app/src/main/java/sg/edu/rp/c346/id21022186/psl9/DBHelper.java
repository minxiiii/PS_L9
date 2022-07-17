package sg.edu.rp.c346.id21022186.psl9;



import static java.lang.String.valueOf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Songs.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_SONGS = "songs";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE= "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEAR = "_year";
    private static final String COLUMN_STARS = "_stars" ;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_SONGS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT ," + COLUMN_SINGERS + "TEXT, "
                + COLUMN_YEAR + "INTEGER ," + COLUMN_STARS + "INTEGER )";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        onCreate(db);
    }
   public long insertSong(String title, String singers,int year,int stars) {
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues values = new ContentValues();
       values.put(COLUMN_TITLE, title);
       values.put(COLUMN_SINGERS, singers);
       values.put(COLUMN_YEAR, year);
       values.put(COLUMN_STARS, stars);
       long result = db.insert(TABLE_SONGS, null, values);

       if (result == -1) {
           Log.d("DBHelper", "Insert failed");
       } else {
           db.close();
       }
       return result;
   }
   public ArrayList<Songs> getAllSongs() {
        ArrayList<Songs> songs = new ArrayList<Songs>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_TITLE, COLUMN_SINGERS,COLUMN_YEAR,COLUMN_STARS};
       Cursor cursor = db.query(TABLE_SONGS, columns, null, null,
               null, null, null, null);

       if (cursor.moveToFirst()) {
           do {
               String title = cursor.getString(1);
               String singer = cursor.getString(1);
               int year = cursor.getInt(0);
               int star = cursor.getInt(0);
               Songs song = new Songs(title, singer, year, star);
               songs.add(song);
           } while (cursor.moveToNext());
       }
       cursor.close();
       db.close();
       return songs;

           }

    public ArrayList<Songs> getAllFilteredSongs(int keyword) {
        ArrayList<Songs> songs = new ArrayList<Songs>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_TITLE, COLUMN_SINGERS,COLUMN_YEAR,COLUMN_STARS};;
        String condition = COLUMN_STARS + " EQUALS TO?";
        int[] args = { '5'};
        Cursor cursor = db.query(TABLE_SONGS, columns, condition, new String[] {valueOf(args)},
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(1);
                String singer = cursor.getString(1);
                int year = cursor.getInt(0);
                int star = cursor.getInt(0);
                Songs song = new Songs(title, singer, year, star);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }


    public int updateSongs(Songs data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE,   data.getTitle());
        values.put(COLUMN_SINGERS, data.getSingers());
        values.put(COLUMN_YEAR,data.getYear());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SONGS, values, condition, args);
        db.close();
        return result;
    }

    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONGS, condition, args);
        db.close();
        return result;
    }





}













