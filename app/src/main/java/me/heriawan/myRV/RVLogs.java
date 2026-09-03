package me.heriawan.myRV;

import android.content.Context;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.heriawan.myparitta.R;
import me.heriawan.obj.Logs;


public class RVLogs extends RecyclerView.Adapter<RVLogs.MyViewHolder> {
    private ArrayList<Logs> list;
    private Context ctx;

    public RVLogs(ArrayList<Logs> Data) {
        list = Data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ctx=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_log, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder h, int position) {
        h.r = list.get(position);
        h.dt.setText(h.r.date);
        h.tit.setText(h.r.title);
        h.cap.setText(h.r.caption);
        String col=(position %2 == 1)?"#AAFFFFFF":"#226699CC";
        h.itemView.setBackgroundColor(Color.parseColor(col));
    }
    @Override
    public int getItemCount() { return list.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dt;
        public TextView tit;
        public TextView cap;
        public Logs r;

        public MyViewHolder(View v) {
            super(v);
            dt=v.findViewById(R.id.logdate);
            tit=v.findViewById(R.id.logtitle);
            cap=v.findViewById(R.id.logcaption);
        }
    }
}
