package me.heriawan.myparitta;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import me.heriawan.myRV.RVAcontent;
import me.heriawan.obj.Daftar;

public class ContentFragment extends Fragment {
    RecyclerView contentitem;
    Context ctx;
    private cListen ctlisten;
/*
論語選讀: 論君子誼 LUN YÜ SÜEN TU: Lun Cün Ce Yi
論 語 選 讀: 論 性 與 天 道 (一) LUN YÜ SÜEN TU: Lun  Sing  Yü  Thien Tao
論 語 選 讀: 論 仁 (二) LUN YÜ SÜEN TU: Lun Jen
論 語 選 讀: 論 孝 悌 (三) LUN YÜ SÜEN TU: Lun Siao Thi
論 語 選 讀: 論 忠 信 (四) LUN YÜ SÜEN TU: Lun Cong Sin
*/
    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_content, container, false);
//        v.setBackgroundColor(Color.WHITE);
        ctx = v.getContext();
        ArrayList<Daftar> data=new ArrayList<Daftar>();
        contentitem=v.findViewById(R.id.contentitem);
        contentitem.setLayoutManager(new LinearLayoutManager(ctx));
        data.add(new Daftar("a1","a1", getString(R.string.a1_Title), getString(R.string.a1_Judul)));
        data.add(new Daftar("a2","a2", getString(R.string.a2_Title), getString(R.string.a2_Judul)));
        data.add(new Daftar("a3","a3", getString(R.string.a3_Title), getString(R.string.a3_Judul)));
        data.add(new Daftar("a4","a4", getString(R.string.a4_Title), getString(R.string.a4_Judul)));
//        data.add(new Daftar("a5","a5",getString(R.string.a5_Title), getString(R.string.a5_Judul)));
        data.add(new Daftar("a6","a6", getString(R.string.a6_Title), getString(R.string.a6_Judul)));
//        data.add(new Daftar("a7","a7",getString(R.string.a7_Title), getString(R.string.a7_Judul)));
//        data.add(new Daftar("a8","a8",getString(R.string.a8_Title), getString(R.string.a8_Judul)));
//        data.add(new Daftar("a9","a9",getString(R.string.a9_Title), getString(R.string.a9_Judul)));
        data.add(new Daftar("a10","a10",getString(R.string.a10_Title), getString(R.string.a10_Judul)));
//        data.add(new Daftar("a11","a11",getString(R.string.a11_Title), getString(R.string.a11_Judul)));
        data.add(new Daftar("a12","a12", getString(R.string.a12_Title), getString(R.string.a12_Judul)));
//        data.add(new Daftar("a13","a13",getString(R.string.a13_Title), getString(R.string.a13_Judul)));
//        data.add(new Daftar("a14","a14", getString(R.string.a14_Title), getString(R.string.a14_Judul)));
        contentitem.setAdapter(new RVAcontent(data,ctlisten));
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof cListen) {
            ctlisten = (cListen) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement cListen");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        ctlisten = null;
    }
    public interface cListen {
        void showteks(String tag);
    }
}
