package capstone.gonggancapsule.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import java.util.ArrayList;
//import java.util.List;



public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "diary_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Diary.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Diary.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertDiary(String context, String picture) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `index`,'latitude','longitude','create_date' 는 자동으로 삽입
        // 넣어줄 필요 없음
        values.put(Diary.COLUMN_CONTENT, context);
        values.put(Diary.COLUMN_PICTURE, picture);

        // insert row
        long index = db.insert(Diary.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return index;
    }

    //일기 가져와서 읽기 : 이건 다음에 DiaryAdapter를 만들고 써야될 거 같아요~~
    public Diary getDiary(long index) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Diary.TABLE_NAME,
                new String[]{Diary.COLUMN_INDEX, Diary.COLUMN_LATITUDE, Diary.COLUMN_LONGITUDE,
                        Diary.COLUMN_CREATEDATE, Diary.COLUMN_CONTENT, Diary.COLUMN_PICTURE},
                Diary.COLUMN_INDEX + "=?",
                new String[]{String.valueOf(index)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Diary diary = new Diary(
                cursor.getInt(cursor.getColumnIndex(Diary.COLUMN_INDEX)),
                cursor.getDouble(cursor.getColumnIndex(Diary.COLUMN_LATITUDE)),
                cursor.getDouble(cursor.getColumnIndex(Diary.COLUMN_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(Diary.COLUMN_CREATEDATE)),
                cursor.getString(cursor.getColumnIndex(Diary.COLUMN_CONTENT)),
                cursor.getString(cursor.getColumnIndex(Diary.COLUMN_PICTURE)));

        // close the db connection
        cursor.close();

        return diary;
    }

    //얘도 adapter만들고 나서
    public List<Diary> getAllNotes() {
        List<Diary> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Diary.TABLE_NAME + " ORDER BY " +
                Diary.COLUMN_CREATEDATE + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Diary diary = new Diary();
                diary.setIndex(cursor.getInt(cursor.getColumnIndex(Diary.COLUMN_INDEX));
                diary.setLatitude(cursor.getDouble(cursor.getColumnIndex(Diary.COLUMN_LATITUDE));
                diary.setLongitude(cursor.getDouble(cursor.getColumnIndex(Diary.COLUMN_LONGITUDE)));
                diary.setTimeStamp(cursor.getString(cursor.getColumnIndex(Diary.COLUMN_CREATEDATE)));
                diary.setPicture(cursor.getString(cursor.getColumnIndex(Diary.COLUMN_PICTURE)));

                notes.add(diary);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Diary.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }
}
