package com.example.baitap.Other;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baitap.Taichinh;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Taichinh";
    private static final int VERSION = 1;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLQuery = "CREATE TABLE Taichinh (ID INTEGER primary key AUTOINCREMENT,NAME VARCHAR(255),PRICE INTEGER,TYPE INTEGER)";
        db.execSQL(SQLQuery);    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public ArrayList<Taichinh> getAllCountry() {

        ArrayList<Taichinh> taichinhs = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Taichinh", null);

        //Đến dòng đầu của tập dữ liệu
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int Id = cursor.getInt(0);
            String Name = cursor.getString(1);
            int Price = cursor.getInt(2);
            int Type = cursor.getInt(3);



            taichinhs.add(new Taichinh(Id,Name,Price,Type));
            cursor.moveToNext();
        }

        cursor.close();

        return taichinhs;
    }
    public Taichinh getByID(int ID) {
        Taichinh taichinh = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Taichinh where id = ?", new String[]{ID + ""});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int Id = cursor.getInt(0);
            String Name = cursor.getString(1);
            int Price = cursor.getInt(2);
            int Type = cursor.getInt(3);

            taichinh =new Taichinh(Id,Name,Price,Type);
        }
        cursor.close();
        return taichinh;
    }

    //cập nhật
    public void update(Taichinh taichinh) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Taichinh SET name=?, price =?, type=? where id = ?",
                new String[]{taichinh.getName(),taichinh.getPrice()+"",taichinh.getType()+"",taichinh.getId()+""});
    }

    //thêm mới
    public void insertCountry(Taichinh taichinh) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Taichinh ( name, price, type ) VALUES (?,?,?)",
                new String[]{taichinh.getName(),taichinh.getPrice()+"",taichinh.getType()+""});
    }

    //Xoá SV bằng ID
    public void deleteCountryByID(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Taichinh where id = ?", new String[]{String.valueOf(id)});
    }
    //Get item IDS
}
