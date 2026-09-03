package me.heriawan.myRV;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.heriawan.myparitta.R;
import me.heriawan.obj.T4x3;

public class RVAdrei extends RecyclerView.Adapter<RVAdrei.MyViewHolder> {
    private ArrayList<T4x3> list;
    private Context ctx;

    public RVAdrei(ArrayList<T4x3> Data) {
        list = Data;
    }

    @Override
    public RVAdrei.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ctx=parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_drei, parent, false);
        RVAdrei.MyViewHolder holder = new RVAdrei.MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final RVAdrei.MyViewHolder holder, int position) {
        holder.r = list.get(position);
        holder.man1.setText(list.get(position).man1);
        holder.man2.setText(list.get(position).man2);
        holder.man3.setText(list.get(position).man3);
        holder.pin1.setText(list.get(position).pin1);
        holder.pin2.setText(list.get(position).pin2);
        holder.pin3.setText(list.get(position).pin3);
    }
    @Override
    public int getItemCount() { return list.size(); }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView man1,man2,man3;
        public TextView pin1,pin2,pin3;
        public TextView id;
        public T4x3 r;

        public MyViewHolder(View v) {
            super(v);
            man1=v.findViewById(R.id.twm_s1_m);
            man2=v.findViewById(R.id.twm_s2_m);
            man3=v.findViewById(R.id.twm_s3_m);
            pin1=v.findViewById(R.id.twm_s1_p);
            pin2=v.findViewById(R.id.twm_s2_p);;
            pin3=v.findViewById(R.id.twm_s3_p);;
        }
    }
}
