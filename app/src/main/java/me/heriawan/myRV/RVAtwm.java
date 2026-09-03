package me.heriawan.myRV;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import me.heriawan.myparitta.R;
import me.heriawan.obj.Twm;

public class RVAtwm extends RecyclerView.Adapter<RVAtwm.MyViewHolder> {
    private ArrayList<Twm> list;
    private Context ctx;

    public RVAtwm(ArrayList<Twm> Data) {
        list = Data;
    }

    @Override
    public RVAtwm.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ctx=parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_twowithmeaning, parent, false);
        RVAtwm.MyViewHolder holder = new RVAtwm.MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final RVAtwm.MyViewHolder holder, int position) {
        holder.r = list.get(position);
        holder.man1.setText(list.get(position).man1);
        holder.pin1.setText(list.get(position).pin1);
        holder.man2.setText(list.get(position).man2);
        holder.pin2.setText(list.get(position).pin2);
        holder.id.setText(list.get(position).idn);
    }
    @Override
    public int getItemCount() { return list.size(); }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView man1,man2;
        public TextView pin1,pin2;
        public TextView id;
        public Twm r;

        public MyViewHolder(View v) {
            super(v);
            man1=v.findViewById(R.id.twm_s1_m);
            pin1=v.findViewById(R.id.twm_s1_p);
            man2=v.findViewById(R.id.twm_s2_m);
            pin2=v.findViewById(R.id.twm_s2_p);
            id=v.findViewById(R.id.twm_meaning);
        }
    }
}
