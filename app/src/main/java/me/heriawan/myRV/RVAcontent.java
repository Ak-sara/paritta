package me.heriawan.myRV;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import me.heriawan.myparitta.ContentFragment;
import me.heriawan.myparitta.R;
import me.heriawan.obj.Daftar;


public class RVAcontent extends RecyclerView.Adapter<RVAcontent.MyViewHolder>{
    private final ContentFragment.cListen clisten;
    private ArrayList<Daftar> list;
    private Context ctx;

    public RVAcontent(ArrayList<Daftar> Data,ContentFragment.cListen clistener) {
        list = Data;clisten=clistener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ctx=parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_content, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.r = list.get(position);
        int drawable = this.ctx.getResources().getIdentifier(list.get(position).imgres , "drawable", this.ctx.getPackageName());
        holder.img.setImageResource(drawable);
        holder.man.setText(list.get(position).mandarin);
        holder.pin.setText(list.get(position).pinyin);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != clisten) { clisten.showteks(holder.r.tag);}
            }
        });
    }
    @Override
    public int getItemCount() { return list.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView man;
        public TextView pin;
        public Daftar r;

        public MyViewHolder(View v) {
            super(v);
            img=v.findViewById(R.id.contimg);
            man=v.findViewById(R.id.contman);
            pin=v.findViewById(R.id.contpin);
        }
    }
}
