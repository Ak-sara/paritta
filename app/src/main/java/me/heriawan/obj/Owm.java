package me.heriawan.obj;

public class Owm {

        public String man,pin,idn;

        public Owm(String man1, String pin1, String idn){
            this.man=man1;
            this.pin=pin1;
            this.idn=idn;
        }

        @Override
        public String toString() {
            return idn;
        }
}
