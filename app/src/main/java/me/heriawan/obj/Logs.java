package me.heriawan.obj;

import android.content.ContentValues;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Logs {
    public int logid;
    public String date;
    public String title;
    public String caption;

    public Logs(String tit,String cap){
        logid=0;
        Date c= Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        date=df.format(c);
        title=tit;caption=cap;
    }
    public Logs(int id,String dt,String tit,String cap){
        logid=id; date=dt;title=tit;caption=cap;
    }

    public String tostring(){
        return title;
    }
    public ContentValues getHash(String usg){
        ContentValues args = new ContentValues();
        if(!usg.equals("I")){
            args.put("logid", logid);
        }
        args.put("date", date);
        args.put("title", title);
        args.put("caption", caption);
        return args;
    }
}
