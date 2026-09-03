package me.heriawan.myparitta;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import me.heriawan.myRV.RVLogs;
import me.heriawan.obj.Logs;
import me.heriawan.MDBhndl;


public class logFragment extends Fragment {
    Context ctx;
    private MDBhndl dao;
    private ArrayList<Logs> logs;
    SwipeRefreshLayout srl;
    RecyclerView rv;
    Button clear;
    public logFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_log, container, false);
        ctx=getContext();
        dao=new MDBhndl(ctx,null,null,1);

        clear=v.findViewById(R.id.clearLog);
        clear.setOnClickListener(new logclear());

        srl=v.findViewById(R.id.srl);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() { load();  }
        });

        rv=(RecyclerView) v.findViewById(R.id.thelog);
        rv.setLayoutManager(new LinearLayoutManager(ctx));
        if (rv instanceof RecyclerView) {  load(); }
        else{ Toast.makeText(ctx, "RV nio Recyc.", Toast.LENGTH_SHORT).show(); }
        return v;
    }
    public void load(){
        srl.setRefreshing(true);
        logs=dao.logsget();
        rv.setAdapter(new RVLogs(logs));
        srl.setRefreshing(false);
    }
    class logclear implements View.OnClickListener{
        @Override
        public void onClick(View v) { dao.logremall(); load(); }
    }
}
