package feicuiedu.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/7/15.
 */
public class MySQLHelper extends SQLiteOpenHelper {
    public  static  final String SQL="CREATE TABLE IF NOT exists Tab1" +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar(20),phone varchar(20))";
    //简化constructor
    public MySQLHelper(Context context) {
        super(context,"DBName.db",null,1);
    }
      //创建数据库的方法
    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
