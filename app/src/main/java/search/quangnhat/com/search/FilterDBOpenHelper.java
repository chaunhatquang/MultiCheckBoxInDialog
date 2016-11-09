package search.quangnhat.com.search;

import android.content.ClipData;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static android.R.id.message;

/**
 * Created by Me on 11/1/2016.
 */

public class FilterDBOpenHelper extends SQLiteOpenHelper {
    public static String DB_PATH = "";
    public static String DB_NAME = "CITY.sqlite";
    private static final int DB_VERSION = 1;
    Context context;
    private SQLiteDatabase db;

    public FilterDBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.context = context;
    }

    public void create() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            //do nothing - database already exist
        } else {
            // By calling this method and empty database will be created into the default system path
            // of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private void copyDataBase() throws IOException {
        //Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DB_NAME);

        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    //Open the database
    public boolean open() {
        try {
            String myPath = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            return true;
        } catch (SQLException sqle) {
            db = null;
            return false;
        }
    }

    @Override
    public synchronized void close() {
        if (db != null)
            db.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private boolean checkDataBase() {
        // Check if the database exist to avoid re-copy the data
        SQLiteDatabase checkDB = null;
        try {
            String path = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            // database don't exist yet.
            e.printStackTrace();
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    public List<String> getListCity() {
        List<String> list = new ArrayList<>();
        open();
        try {
            Cursor cursor = db.rawQuery("SELECT DISTINCT City FROM City", null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(cursor.getColumnIndex("City"));
                list.add(name);
            }

        } catch (Exception e) {
            Log.d("fix", e.getMessage());

        }
        return list;
    }
    public List<String> getListHCM() {
        List<String> list = new ArrayList<String>();
        open();
        try {
            Cursor cursor = db.rawQuery("SELECT Dictrict FROM City WHERE IDCity = 1 ", null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(cursor.getColumnIndex("Dictrict"));
                //ItemDistrict district = new ItemDistrict(name,false);
                list.add(name);
            }

        } catch (Exception e) {
            Log.d("fix", e.getMessage());

        }
        return list;
    }

    public List<String> getListHN() {
        List<String> list = new ArrayList<>();
        open();
        try {
            Cursor cursor = db.rawQuery("SELECT Dictrict FROM City WHERE IDCity = 2 ", null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(cursor.getColumnIndex("Dictrict"));
                list.add(name);
            }

        } catch (Exception e) {
            Log.d("fix", e.getMessage());

        }
        return list;
    }
    public List<String> getListDN() {
        List<String> list = new ArrayList<>();
        open();
        try {
            Cursor cursor = db.rawQuery("SELECT Dictrict FROM City WHERE IDCity = 3 ", null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(cursor.getColumnIndex("Dictrict"));
                list.add(name);
            }

        } catch (Exception e) {
            Log.d("fix", e.getMessage());

        }
        return list;
    }
}
