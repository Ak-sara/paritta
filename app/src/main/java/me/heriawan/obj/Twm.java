package me.heriawan.obj;

public class Twm {
    public String man1,man2;
    public String pin1,pin2,idn;

    public Twm(String man1, String pin1,String man2, String pin2, String idn){
        this.man1=man1;
        this.pin1=pin1;
        this.man2=man2;
        this.pin2=pin2;
        this.idn=idn;
    }

    @Override
    public String toString() {
        return idn;
    }
}

