package feicuiedu.test;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/15.
 */
public class DBReader {
    static String packageName="data/data/feicuiedu.test/databases/";
    //定义一个file对象
    static  File telFile;
    static {
        //指定路径
        File path=new File(packageName);
        //创建文件夹
        path.mkdir();
        telFile = new File(packageName,"commonnum.db");
    }
    public static boolean isExistDBFile(){
        File file = DBReader.telFile;
        //如果File对象不存在 或者长度小于等于0
        if(!file.exists()||file.length()<=0){
          return  false;
        }
        return  true;
    }
    public  static ArrayList<TelclassInfo> readTeldbClasslist() throws Exception{
        ArrayList<TelclassInfo> classListInfos = new  ArrayList<TelclassInfo>();
        //打开DB文件
        SQLiteDatabase db=null;
        //执行查询的SQL语句 select *from classlist
        Cursor cursor =null;
        try {
            db=SQLiteDatabase.openOrCreateDatabase(telFile,null);
            cursor=db.rawQuery("select * from classlist",null);
            if(cursor.moveToFirst()){
                do {
                    String name=cursor.getString(cursor.getColumnIndex("name"));
                    //idx为classlist表中电话的ID，根据idx值进行指定页面的跳转
                    int idx=cursor.getInt(cursor.getColumnIndex("idx"));
                    TelclassInfo classlistInfo=new TelclassInfo(name,idx);
                    classListInfos.add(classlistInfo);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
              throw e;
        }finally {
            try {
                cursor.close();
                db.close();
            }catch (Exception e2) {
                // TODO: handle exception
                throw e2;
            }
        }
        return classListInfos;
    }
    public static  ArrayList<TelnumberInfo> readTeldbTable(int idx) throws Exception{
        ArrayList<TelnumberInfo> telnumberInfos = new ArrayList<TelnumberInfo>();
        //idx为classlist表中电话的ID 根据idx值进行指定页面的跳转
        String sql = "select * from table" + idx;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            // 打开 DB 文件
            db = SQLiteDatabase
                    .openOrCreateDatabase(telFile, null);
            // 执行查询的 SQL 语句 select * from table +idx
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor
                            .getString(cursor
                                    .getColumnIndex("name"));
                    String number = cursor
                            .getString(cursor
                                    .getColumnIndex("number"));
                    TelnumberInfo numberInfo = new TelnumberInfo(
                            name, number);
                    telnumberInfos.add(numberInfo);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }finally{
            try {
                cursor.close();
                db.close();
            } catch (Exception e2) {
                // TODO: handle exception
                throw e2;
            }
        }
        return telnumberInfos;
    }

}
