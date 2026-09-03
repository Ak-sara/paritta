package me.heriawan.myparitta;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import me.heriawan.obj.K7x2;
import me.heriawan.MyswipeListener;
import me.heriawan.myRV.RVword;


public class a6Fragment extends Fragment {
    RecyclerView sentences;
    Context ctx;
    TextView deb, judul, title;
    ArrayList<K7x2> texts;
    String m_man, m_pin;
    String[] l_man, l_pin;
    int coll,rows, totPages, currPage;
    public a6Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_a6, container, false);
//        v.setBackgroundColor(Color.WHITE);
        ctx = v.getContext();
        deb=v.findViewById(R.id.deb);
        judul=v.findViewById(R.id.Judul);
        title=v.findViewById(R.id.Title);

        judul.setText(R.string.a6_Judul);
        title.setText(R.string.a6_Title);
        m_man = getString(R.string.a6_man);
        m_pin = getString(R.string.a6_pin);
        maketext();

        sentences=v.findViewById(R.id.sentences);
        sentences.setHasFixedSize(true);
        coll=7;
        rows=9;
        currPage=1;
        fillSentences(currPage);
        sentences.setOnTouchListener(new MyswipeListener(ctx) {
            public void onSwipeRight(){
                currPage=currPage-1<1 ? totPages : currPage-1;
                fillSentences(currPage);
            }
            public void onSwipeLeft() {
                currPage=currPage+1>totPages?1 : currPage+1;
                fillSentences(currPage);
            }
        });
        return v;
    }
    private void maketext(){
        l_man=m_man.split("\n");
        l_pin=m_pin.split("\n");
        texts=new ArrayList<K7x2>();
        for(int i=0; i < l_pin.length; i++){
            String[] w_man=l_man[i].split(" ");
            String[] w_pin=l_pin[i].split(" ");
            for(int j=0; j < w_pin.length; j++) {if(!w_man[j].equals("")){
                texts.add(new K7x2(w_man[j], w_pin[j]));
            }}
        }
    }
    public void fillSentences(int page){
        ArrayList<K7x2> thispage=new ArrayList<K7x2>();
        int onepage=coll*rows;
        totPages= (int) Math.ceil(texts.size()/(onepage*1.0));
        deb.setText("Pages:"+page+"/"+totPages);
        for(int x =(page-1)*onepage;x < (page)*onepage;x++){
            if(x<texts.size()){
                thispage.add(texts.get(x));
            }
        }

        sentences.setLayoutManager(new GridLayoutManager(ctx, coll));
        if (thispage.size() > 0 && sentences != null) {
            sentences.setAdapter(new RVword(thispage));
        }
    }

}
