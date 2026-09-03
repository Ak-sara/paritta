package me.heriawan.obj;

public class T4x3 {
    public String man1,man2,man3;
    public String pin1,pin2,pin3;

    public T4x3(String man1, String man2, String man3, String pin1, String pin2, String pin3){
        this.man1=man1;
        this.man2=man2;
        this.man3=man3;
        this.pin1=pin1;
        this.pin2=pin2;
        this.pin3=pin3;
    }

    @Override
    public String toString() {
        return pin1+" "+pin2+" "+pin3;
    }
}
