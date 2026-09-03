package me.heriawan.myparitta;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.heriawan.MyswipeListener;
import me.heriawan.myRV.RVAdrei;
import me.heriawan.obj.T4x3;


/**
 * A simple {@link Fragment} subclass.
 */
public class a2Fragment extends Fragment {
    RecyclerView sentences;
    Context ctx;
    TextView deb, judul, title;
    ArrayList<T4x3> texts;
    String m_man, m_pin;
    String[] l_man, l_pin;
    int coll,rows, totPages, currPage;


    public a2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_a2, container, false);
//        v.setBackgroundColor(Color.WHITE);
        ctx = v.getContext();
        deb=v.findViewById(R.id.deb);
        judul=v.findViewById(R.id.Judul);
        title=v.findViewById(R.id.Title);

        judul.setText(R.string.a2_Judul);
        title.setText(R.string.a2_Title);
        m_man = getString(R.string.a2_man);
        m_pin = getString(R.string.a2_pin);
        maketext();
        sentences=v.findViewById(R.id.sentences);
        sentences.setLayoutManager(new LinearLayoutManager(ctx));

        sentences.setHasFixedSize(true);
        coll=1;
        rows=8;
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
        texts=new ArrayList<T4x3>();
        for(int i=0; i < l_pin.length; i++){
            String[] w_man=l_man[i].split("\t");
            String[] w_pin=l_pin[i].split("\t");

            texts.add(new T4x3(w_man[0], w_man[1], w_man.length>2?w_man[2]:"",  w_pin[0], w_pin[1],  w_pin.length>2?w_pin[2]:"" ));
        }
    }
    public void fillSentences(int page){
        ArrayList<T4x3> thispage=new ArrayList<T4x3>();
        int onepage=coll*rows;
        totPages= (int) Math.ceil(texts.size()/(onepage*1.0));
        deb.setText("Pages:"+page+"/"+totPages);
        for(int x =(page-1)*onepage;x < (page)*onepage;x++){
            if(x<texts.size()){
                thispage.add(texts.get(x));
            }
        }

        if (thispage.size() > 0 && sentences != null) {
            sentences.setAdapter(new RVAdrei(thispage));
        }
    }
}
