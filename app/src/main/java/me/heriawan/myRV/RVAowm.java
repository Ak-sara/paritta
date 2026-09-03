package me.heriawan.myRV;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.heriawan.myparitta.R;
import me.heriawan.obj.Owm;

public class RVAowm  extends RecyclerView.Adapter<RVAowm.MyViewHolder> {
    private ArrayList<Owm> list;
    private Context ctx;

    public RVAowm(ArrayList<Owm> Data) {
        list = Data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ctx=parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_onewithmeaning, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.r = list.get(position);
        holder.man1.setText(list.get(position).man);
        holder.pin1.setText(list.get(position).pin);
        holder.id.setText(list.get(position).idn);
    }
    @Override
    public int getItemCount() { return list.size(); }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView man1,pin1;
        public TextView id;
        public Owm r;

        public MyViewHolder(View v) {
            super(v);
            man1=v.findViewById(R.id.owm_m);
            pin1=v.findViewById(R.id.owm_p);
            id=v.findViewById(R.id.meaning);
        }
    }
}
