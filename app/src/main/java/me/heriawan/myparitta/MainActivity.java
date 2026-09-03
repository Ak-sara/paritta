package me.heriawan.myparitta;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.core.app.NotificationCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import me.heriawan.Mypref;
import me.heriawan.NotificationHelper;
import me.heriawan.RandVerse;
import me.heriawan.obj.Logs;
import me.heriawan.MDBhndl;

public class MainActivity extends AppCompatActivity implements ContentFragment.cListen {
    Context ctx;
    Mypref pref;
    RandVerse randv;
    private MDBhndl dao;
    final logFragment fr_log = new logFragment();
    final ContentFragment fr_contents = new ContentFragment();
    final a1Fragment fr_a1 = new a1Fragment();
    final a2Fragment fr_a2 = new a2Fragment();
    final A12Fragment fr_a12 = new A12Fragment();
    final a3Fragment fr_a3 = new a3Fragment();
    final a4Fragment fr_a4 = new a4Fragment();
    final a6Fragment fr_a6 = new a6Fragment();
    final a10Fragment fr_a10 = new a10Fragment();
    final StoryFragment fr_story = new StoryFragment();
    final AboutFragment fr_about = new AboutFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active;
    ArrayList<String> taghist=new ArrayList<String>();
    boolean doubleBackToExitPressedOnce=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = getApplicationContext();

        if (android.os.Build.VERSION.SDK_INT >= 33
                && checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                   != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 101);
        }

        pref=new Mypref(ctx);
        randv=new RandVerse(ctx);
        dao=new MDBhndl(ctx,null,null,1);

        fm.beginTransaction().add(R.id.main_container, fr_log, "log").hide(fr_log).commit();
        fm.beginTransaction().add(R.id.main_container, fr_story, "story").hide(fr_story).commit();
        fm.beginTransaction().add(R.id.main_container, fr_about, "about").hide(fr_about).commit();
        fm.beginTransaction().add(R.id.main_container, fr_a1, "a1").hide(fr_a1).commit();
        fm.beginTransaction().add(R.id.main_container, fr_a2, "a2").hide(fr_a2).commit();
        fm.beginTransaction().add(R.id.main_container, fr_a3, "a3").hide(fr_a3).commit();
        fm.beginTransaction().add(R.id.main_container, fr_a4, "a4").hide(fr_a4).commit();
        fm.beginTransaction().add(R.id.main_container, fr_a6, "a6").hide(fr_a6).commit();
        fm.beginTransaction().add(R.id.main_container, fr_a10, "a10").hide(fr_a10).commit();
        fm.beginTransaction().add(R.id.main_container, fr_a12, "a12").hide(fr_a12).commit();
        fm.beginTransaction().add(R.id.main_container, fr_contents, "contents").show(fr_contents).commit();
        taghist.add("about");
        taghist.add("contents");
        active = fr_contents;

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            String j =(String) b.get("frag");
            if(j!=null){ showteks(j); }
            else{
                try{
                    HashMap<String,String> p=pref.getPref();
                    if(p.get("timer")==null){ pref.setSched("20:00");dao.logadd(new Logs("MA no bundle","null timer")); }
                    if(p.get("enano")==null){ pref.setEnano("true"); dao.logadd(new Logs("MA no bundle","null enano")); }
                    else{
                        if (p.get("enano").equals("true")) {
                            String[] t=p.get("timer").split(":");
                            HashMap<String,String> v=randv.randomizedverse();
                            NotificationHelper.scheduleRTC(ctx, Integer.parseInt(t[0]), Integer.parseInt(t[1]),v );
                            NotificationHelper.enableBootReceiver(ctx);
                            dao.logadd(new Logs("MA cek sched", ""+t[0]+":"+t[1]+" - ["+v.get("judul")+" - "+v.get("line")+"]"));
                        }else{
                            NotificationHelper.cancelRTC();
                            NotificationHelper.disableBootReceiver(ctx);
                            dao.logadd(new Logs("MA no sched","cancel notif"));
                        }
                    }
                }catch (Exception e){
                    dao.logadd(new Logs("MA no bundle","Err:"+e.getMessage()));
                    Log.d("ERROR",e.getMessage());
                }
            }
        }
    }
    private void showfrag(){
        if(! taghist.get(taghist.size()-1).trim().equals(active.getTag().trim())){
            taghist.add(active.getTag().trim());
            if(taghist.size()>5){taghist.remove(0);}
        } fm.beginTransaction().show(active).commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_home) {
            fm.beginTransaction().hide(active).commit();
            active = fr_contents; showfrag();
        } else if (id == R.id.action_story) {
            fm.beginTransaction().hide(active).commit();
            active = fr_story; showfrag();
        } else if (id == R.id.action_about) {
            fm.beginTransaction().hide(active).commit();
            active = fr_about; showfrag();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void showteks(String tag){
        //Toast.makeText(ctx,tag,Toast.LENGTH_SHORT).show();
        fm.beginTransaction().hide(active).commit();
        switch(tag){
            case "log":
                active= fr_log; fr_log.load();fm.beginTransaction().show(active).commit();
                break;
            case "a1":
                active= fr_a1; showfrag();
                break;
            case "a2":
                active= fr_a2; showfrag();
                break;
            case "a3":
                active= fr_a3; showfrag();
                break;
            case "a4":
                active= fr_a4; showfrag();
                break;
            case "a6":
//                fr_a6.title.setText(R.string.a6_Title);
//                fr_a6.judul.setText(R.string.a6_Judul);
//                fr_a6.m_man=getString(R.string.a6_man);
//                fr_a6.m_pin=getString(R.string.a6_pin);
                active= fr_a6; showfrag();
                break;
            case "a10":
                active= fr_a10; showfrag();
                break;
            case "a12":
                active= fr_a12; showfrag();
                break;
        }
    }
    @Override
    public void onBackPressed() {
        int pos=taghist.size()-1;
        if(pos>=1){
            taghist.remove(pos);
            fm.beginTransaction().hide(active).commit();
            switch (taghist.get(pos-1).trim()){
                case "contents":
                    active=fr_contents; showfrag();
                    break;
                case "story":
                    active=fr_story; showfrag();
                    break;
                case "about":
                    active=fr_about; showfrag();
                    break;
                case "a1":
                    active= fr_a1; showfrag();
                    break;
                case "a2":
                    active= fr_a2; showfrag();
                    break;
                case "a3":
                    active= fr_a3; showfrag();
                    break;
                case "a4":
                    active= fr_a4; showfrag();
                    break;
                case "a6":
                    active= fr_a6; showfrag();
                    break;
                case "a10":
                    active= fr_a10; showfrag();
                    break;
                case "a12":
                    active= fr_a12; showfrag();
            }
        }else{
            if (doubleBackToExitPressedOnce) {
                moveTaskToBack(true);
                finish();
                //return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(ctx, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() { doubleBackToExitPressedOnce=false; }
            }, 2000);
        }
    }

    public void addNotification() {
        HashMap<String, String> verse=randv.randomizedverse();
        dao.logadd(new Logs("Test notif","add verse ["+verse.get("judul")+"] - ["+verse.get("line")+"]"));
        NotificationManager manager = (NotificationManager) getSystemService(ctx.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID="paritta_01";
        String NOTIFICATION_CHANNEL_NAME="Daily Verse";
        Uri SoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel nc = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importance);
            nc.enableLights(true);
            nc.setLightColor(Color.RED);
            nc.enableVibration(true);
            nc.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            manager.createNotificationChannel(nc);
        }
        Intent ni = new Intent(ctx, notifActivity.class);
        ni.putExtra("verse", verse);
        PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 0, ni, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx,NOTIFICATION_CHANNEL_ID )
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notif_24dp)
            .setContentTitle(verse.get("man"))
            .setContentText(verse.get("pin"))
            .setSound(SoundUri)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true);
        manager.notify(0,  builder.build());
    }
}
