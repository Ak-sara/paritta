package me.heriawan.myparitta;


import android.app.TimePickerDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.HashMap;

import me.heriawan.Mypref;
import me.heriawan.NotificationHelper;

public class AboutFragment extends Fragment {
    Context ctx;
    Button play,noti,settime;
    Switch enano;
    TextView time,dedica;
    MediaPlayer mediaPlayer;
    Mypref pref;
    Boolean playing=false;
    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_about, container, false);
//        v.setBackgroundColor(Color.WHITE);
        ctx=getContext();
        pref=new Mypref(ctx);
        HashMap<String,String> p=pref.getPref();
        String t=p.get("timer");
        String e=p.get("enano");
        if(t==null){t="12:00";pref.setSched("12:00");}
        if(e==null){e="true";pref.setEnano("true");}

        time=v.findViewById(R.id.time);
        time.setOnClickListener(new TimeClick());
        time.setText(t);
        enano=v.findViewById(R.id.enano);
        enano.setChecked(e.equals("true"));
        enano.setOnClickListener(new chenano());
        play=v.findViewById(R.id.btnplay);
        play.setOnClickListener(new clikplay());
        noti=v.findViewById(R.id.notifyme); noti.setOnClickListener(new notifyme());
        dedica=v.findViewById(R.id.dedica); dedica.setOnClickListener(new notifyme());
        return v;
    }

    class clikplay implements View.OnClickListener{
        @Override
        public void onClick(View v){
            if(!playing){
                mediaPlayer = MediaPlayer.create(ctx, R.raw.mi_lek_cen_cing);
                mediaPlayer.start(); playing=true;
            }else{ mediaPlayer.stop(); playing=false; }
        }
    }
    class notifyme implements View.OnClickListener{
        @Override
        public void onClick(View v){
            int vid = v.getId();
            if (vid == R.id.notifyme) { ((MainActivity)getActivity()).addNotification(); }
            else if (vid == R.id.dedica) { ((MainActivity)getActivity()).showteks("log"); }

        }
    }
    class chenano implements View.OnClickListener{
        @Override
        public void onClick(View v){
            pref.setEnano( enano.isChecked()?"true":"false");
            MainActivity ma=(MainActivity)getActivity();
            if(enano.isChecked()){
                NotificationHelper.cancelRTC();
                NotificationHelper.disableBootReceiver(ctx);
            } else { NotificationHelper.enableBootReceiver(ctx); }
        }
    }

    class TimeClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final MainActivity ma=(MainActivity)getActivity();
            Calendar cal=Calendar.getInstance();
            int mHour=cal.get(Calendar.HOUR);
            int mMinute=cal.get(Calendar.MINUTE);
            final View fv=v;
            TimePickerDialog dpD1=new TimePickerDialog(fv.getContext(),
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hour, int minute) {
                            String h=("0"+hour);
                            String m=("0"+minute);
                            h=(h == null || h.length() < 2) ? h : h.substring(h.length() - 2);
                            m=(m == null || m.length() < 2) ? m : m.substring(m.length() - 2);
                            time.setText(h+":"+m);
                            pref.setSched( h+":"+m );
                            if(enano.isChecked()){
                                NotificationHelper.scheduleRTC(ctx, hour, minute,ma.randv.randomizedverse());
                                NotificationHelper.enableBootReceiver(ctx);
                                //ma.logs("About sched ["+h+":"+m+"]","time click");
                            }
                        }
                    },mHour,mMinute,true);
            dpD1.show();
        }
    }
}
