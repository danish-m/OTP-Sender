package com.example.otpsender;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_ALL_MESSAGES = "messages_details";
    public static final String Messages_ID = "_id";
    public static final String Contact_NAME = "name";
    public static final String OTP = "otp";
    public static final String TIME= "time";

    private static final String DATABASE_NAME = "message.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase myDataBase;
    private final Context context;
    // Database creation sql statement
    private static final String CREATE_TABLE_MESSAGE_DETAILS = "create table "
            + TABLE_ALL_MESSAGES + "( " + Messages_ID
            + " integer primary key autoincrement, "
            + Contact_NAME + " text not null,"
            + OTP + " text not null,"
            + TIME + " TIMESTAMP);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }




    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_MESSAGE_DETAILS);

        Log.v("DATABASEHELPER","tables crated successfully");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALL_MESSAGES);

        onCreate(db);
    }

    /* Insert into database*/
    public void insertMessageDetails(MessageModel model){
        Log.d("insert", "before insert");

        //  get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(Contact_NAME,model.getName());
        values.put(OTP,model.getOtp());
        values.put(TIME ,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        db.insert(TABLE_ALL_MESSAGES, null, values);




        db.close();
        Toast.makeText(context, "insert value", Toast.LENGTH_LONG);
        Log.i("insertd into DB", "After insert");
    }
    /* Retrive  data from database */
    public List<MessageModel> getAllMessages(){
        List<MessageModel> modelList = new ArrayList<MessageModel>();
        String query = "select * from "+TABLE_ALL_MESSAGES + " order by "+TIME+" desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                MessageModel model = new MessageModel();
                model.setM_id(cursor.getInt(0));
                model.setName(cursor.getString(1));
                model.setOtp(cursor.getInt(2));
                model.setTime(cursor.getString(3));


                modelList.add(model);
            }while (cursor.moveToNext());
        }


        Log.d("DataBasehelper", "r_details" + modelList.toString());


        return modelList;
    }


}
