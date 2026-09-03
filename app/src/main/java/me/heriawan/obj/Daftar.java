package me.heriawan.obj;

public class Daftar {
    public String tag;
    public String imgres;
    public String mandarin;
    public String pinyin;

    public Daftar(String tag, String imgres, String man, String pin){
        this.tag=tag;
        this.imgres=imgres;
        this.mandarin=man;
        this.pinyin=pin;
    }

    @Override
    public String toString() {
        return tag;
    }
}
