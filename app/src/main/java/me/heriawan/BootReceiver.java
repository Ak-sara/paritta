package me.heriawan;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import androidx.core.app.NotificationCompat;

import java.util.HashMap;
import me.heriawan.myparitta.R;
import me.heriawan.myparitta.notifActivity;
import me.heriawan.obj.Logs;

public class BootReceiver extends BroadcastReceiver {
    MDBhndl dao;
    @Override
    public void onReceive(Context context, Intent intent) {
        //Get notification manager to manage/send notifications
        RandVerse r=new RandVerse(context);
        dao=new MDBhndl(context,null,null,1);

//        HashMap<String, String> verse= (HashMap<String, String>) intent.getSerializableExtra("verse");
        HashMap<String, String> verse= r.randomizedverse();
        dao.logadd(new Logs("BR","add verse ["+verse.get("judul")+" - "+verse.get("line")+"]"));
        //Intent to invoke app when click on notification.
        //In this sample, we want to start/launch this sample app when user clicks on notification
        Intent ni = new Intent(context, notifActivity.class);
        ni.putExtra("verse", verse);
        //set flag to restart/relaunch the app
        ni.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //Pending intent to handle launch of Activity in intent above
        PendingIntent pendingIntent = PendingIntent.getActivity(context, NotificationHelper.ALARM_TYPE_RTC, ni, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        //Build notification
        Notification thenotify = buildLocalNotification(context, pendingIntent,verse.get("man"),verse.get("pin")).build();

        //Send local notification
        NotificationHelper.getNotificationManager(context).notify(NotificationHelper.ALARM_TYPE_RTC, thenotify);
    }

    public NotificationCompat.Builder buildLocalNotification(Context context, PendingIntent pendingIntent, String Title, String notify) {
        Uri SoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "paritta_01")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notif_24dp)
                .setContentTitle(Title)
                .setContentText(notify)
                .setSound(SoundUri)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);
        return builder;
    }

}
