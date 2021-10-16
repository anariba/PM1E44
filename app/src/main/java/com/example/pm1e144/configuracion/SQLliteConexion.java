package com.example.pm1e144.configuracion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLliteConexion extends SQLiteOpenHelper
{
    public SQLliteConexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version)
    {
        super (context,dbname,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Transacciones.CreateTablePersonas);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(Transacciones.DROPTablePersonas);

        onCreate(db);
    }
}
