package me.heriawan.obj;

public class K7x2 {
    public String mandarin;
    public String pinyin;

    public K7x2(String man, String pin){
        this.mandarin=man;
        this.pinyin=pin;
    }

    @Override
    public String toString() {
        return pinyin;
    }
}
