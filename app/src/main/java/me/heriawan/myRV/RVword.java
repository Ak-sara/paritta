package me.heriawan.myRV;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.heriawan.myparitta.R;
import me.heriawan.obj.K7x2;

public class RVword extends RecyclerView.Adapter<RVword.MyViewHolder> {
    private ArrayList<K7x2> list;
    private Context ctx;

    public RVword(ArrayList<K7x2> Data) {
        list = Data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ctx=parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_words, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.r = list.get(position);
        holder.man.setText(list.get(position).mandarin);
        holder.pin.setText(list.get(position).pinyin);
    }
    @Override
    public int getItemCount() { return list.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView man;
        public TextView pin;
        public K7x2 r;

        public MyViewHolder(View v) {
            super(v);
            man=v.findViewById(R.id.mandarin);
            pin=v.findViewById(R.id.pinyin);
        }
    }
}
