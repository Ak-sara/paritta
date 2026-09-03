package me.heriawan;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Random;

import me.heriawan.myparitta.R;


public class RandVerse {
    Context ctx;
    Mypref pref;

    public RandVerse(Context _ctx){
        ctx=_ctx;
        pref=new Mypref(ctx);
    }
    
    public HashMap<String, String> randomizedverse(){
        String[] a=new String[]{"a1","a3","a4","a10","a12"};
        Random r=new Random();
        int line= 1;
        int idx= r.nextInt(a.length - 1 + 1);
        String Judul="",Title="",man="",pin="",id="";
        switch (a[idx]){
            case "a3":
                Judul=ctx.getString(R.string.a3_Judul);
                Title=ctx.getString(R.string.a3_Title);
                line= r.nextInt(6 - 1 + 1)-1;
                line=line<=0?1:line;
                switch(line){
                    case 1:
                        man=ctx.getString(R.string.a3_1man);
                        pin=ctx.getString(R.string.a3_1pin);
                        id=ctx.getString(R.string.a3_1id);
                        break;
                    case 2:
                        man=ctx.getString(R.string.a3_2man);
                        pin=ctx.getString(R.string.a3_2pin);
                        id=ctx.getString(R.string.a3_2id);
                        break;
                    case 3:
                        man=ctx.getString(R.string.a3_3man);
                        pin=ctx.getString(R.string.a3_3pin);
                        id=ctx.getString(R.string.a3_3id);
                        break;
                    case 4:
                        man=ctx.getString(R.string.a3_4man);
                        pin=ctx.getString(R.string.a3_4pin);
                        id=ctx.getString(R.string.a3_4id);
                        break;
                    case 5:
                        man=ctx.getString(R.string.a3_5man);
                        pin=ctx.getString(R.string.a3_5pin);
                        id=ctx.getString(R.string.a3_5id);
                        break;
                    case 6:
                        man=ctx.getString(R.string.a3_6man);
                        pin=ctx.getString(R.string.a3_6pin);
                        id=ctx.getString(R.string.a3_6id);
                        break;
                }line=line-1;
                break;
            case "a4":
                Judul=ctx.getString(R.string.a4_Judul);
                Title=ctx.getString(R.string.a4_Title);
                String[] b4=ctx.getString(R.string.a4_man).split("\n");
                line= r.nextInt(b4.length);line=line<=0?0:line;
                man=b4[line];
                pin=ctx.getString(R.string.a4_pin).split("\n")[line];
                id=ctx.getString(R.string.a4_id).split("\n")[line];
                break;
            case "a10":
                Judul=ctx.getString(R.string.a10_Judul);
                Title=ctx.getString(R.string.a10_Title);
                String[] b10=ctx.getString(R.string.a10_man).split("\n");
                line= r.nextInt(b10.length);line=line<=0?0:line;
                man=b10[line];
                pin=ctx.getString(R.string.a10_pin).split("\n")[line];
                id=ctx.getString(R.string.a10_id).split("\n")[line];
                break;
            case "a12":
                Judul=ctx.getString(R.string.a12_Judul);
                Title=ctx.getString(R.string.a12_Title);
                String[] b12=ctx.getString(R.string.a12_man).split("\n");
                line= r.nextInt(b12.length);line=line<=0?0:line;
                man=b12[line];
                pin=ctx.getString(R.string.a12_pin).split("\n")[line];
                id=ctx.getString(R.string.a12_id).split("\n")[line];
                break;
            default:
                Judul=ctx.getString(R.string.a1_Judul);
                Title=ctx.getString(R.string.a1_Title);
                String[] b1=ctx.getString(R.string.a1_man).split("\n");
                line= r.nextInt(b1.length);line=line<=0?0:line;
                man=b1[line];
                pin=ctx.getString(R.string.a1_pin).split("\n")[line];
                id=ctx.getString(R.string.a1_id).split("\n")[line];
        }

        HashMap<String, String> verse = new HashMap<String, String>();
        verse.put("tag", a[idx]);
        verse.put("judul", Judul);
        verse.put("title", Title);
        verse.put("line", (line+1)+"");
        verse.put("man", man);
        verse.put("pin", pin);
        verse.put("id", id);
        pref.setVerse(verse);
        return verse;
    }
}
