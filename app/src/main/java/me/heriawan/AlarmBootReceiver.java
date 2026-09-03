package me.heriawan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.HashMap;

import me.heriawan.obj.Logs;

public class AlarmBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Mypref p=new Mypref(context);
            MDBhndl dao=new MDBhndl(context,null,null,1);
            RandVerse r=new RandVerse(context);
            String[] t=p.getPref().get(p.timer).split(":");
            HashMap <String,String> v=r.randomizedverse();
            NotificationHelper.scheduleRTC(context, Integer.parseInt(t[0]), Integer.parseInt(t[1]), v );
            dao.logadd(new Logs("ABR["+t[0]+"]"+"["+t[1]+"]","add verse ["+v.get("judul")+" - "+v.get("line")+"]"));
        }
    }
}
