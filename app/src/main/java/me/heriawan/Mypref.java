package me.heriawan;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;
import java.util.HashMap;

public class Mypref {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "HeriawanParittaPref";

    public static final String timer = "timer";
    public static final String enano = "enano";

    public static final String tag = "tag";
    public static final String judul = "judul";
    public static final String title = "title";
    public static final String line = "line";
    public static final String man = "man";
    public static final String pin = "pin";
    public static final String id = "id";

    public static final Date schedule= new Date();

    public Mypref(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        if(pref.getString(timer, null)==null){
            editor.putString(timer, "20:00");
        }
        if(pref.getString(enano, null)==null) {
            editor.putString(enano, "true");
        }
        editor.commit();
    }
    public void setEnano(String _enano){
        editor = pref.edit();
        editor.putString(enano, _enano);
        editor.apply();
    }
    public void setSched(String _timer){
        editor = pref.edit();
        editor.putString(timer, _timer);
        editor.apply();
    }
    public HashMap<String, String> getPref(){
        HashMap<String, String> Jam = new HashMap<String, String>();
        Jam.put(timer, pref.getString(timer, null));
        Jam.put(enano, pref.getString(enano, null));
        return Jam;
    }
    public void setVerse(HashMap<String, String> verse){
        editor = pref.edit();
        editor.putString(tag, verse.get("tag"));
        editor.putString(judul, verse.get("judul"));
        editor.putString(title, verse.get("title"));
        editor.putString(line, verse.get("line"));
        editor.putString(man, verse.get("man"));
        editor.putString(pin, verse.get("pin"));
        editor.putString(id, verse.get("id"));
        editor.apply();
    }
    public HashMap<String, String> getVerse(){
        HashMap<String, String> verse = new HashMap<String, String>();
        verse.put("tag", pref.getString(tag, null));
        verse.put("judul", pref.getString(judul, null));
        verse.put("title", pref.getString(title, null));
        verse.put("line", pref.getString(line, null));
        verse.put("man", pref.getString(man, null));
        verse.put("pin", pref.getString(pin, null));
        verse.put("id", pref.getString(id, null));
        return verse;
    }
}
