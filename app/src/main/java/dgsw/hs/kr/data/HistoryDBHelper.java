package dgsw.hs.kr.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class HistoryDBHelper extends SQLiteOpenHelper {
    public HistoryDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table history ( sequenceNumber integer primary key autoincrement, name text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table history";
        db.execSQL(sql);
        onCreate(db);
    }

    public long insert(UserBean user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("name", user.getName());
        return db.insert("history",null, value);
    }

    public ArrayList<UserBean> getAll(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("history", null, null, null, null, null, null);
        ArrayList<UserBean> result = new ArrayList<>();
        while(cursor.moveToNext()){
            UserBean user = new UserBean();
            user.setSequenceNumber(cursor.getInt(cursor.getColumnIndex("sequenceNumber")));
            user.setName(cursor.getString(cursor.getColumnIndex("name")));
            result.add(user);
        }
        return result;
    }

    public boolean deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.delete("history", "1", null);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
