package me.heriawan.myparitta;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class notifActivity extends Activity {
    TextView nid,nman,npin,njudul,ntitle,nline;
    Button show;
    String tag="";
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mynotify);
        ctx=getApplicationContext();
        njudul=findViewById(R.id.noti_Judul);
        ntitle=findViewById(R.id.noti_Title);
        nline=findViewById(R.id.noti_line);
        nman=findViewById(R.id.noti_man);
        npin=findViewById(R.id.noti_pin);
        nid=findViewById(R.id.noti_id);
        show=findViewById(R.id.noti_more);
        Intent intent = getIntent();
        HashMap<String, String> verse= (HashMap<String, String>) intent.getSerializableExtra("verse");
        tag=verse.get("tag");

        njudul.setText(verse.get("judul"));
        ntitle.setText(verse.get("title"));
        nline.setText(verse.get("line"));
        nman.setText(verse.get("man"));
        npin.setText(verse.get("pin"));
        nid.setText(verse.get("id"));
        show.setOnClickListener(new showmore());
    }

    class showmore implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Intent i = new Intent(ctx, MainActivity.class);
            i.putExtra("frag", tag);
            startActivity(i);
            finish();
        }
    }
}
