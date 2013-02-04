package sanapeli;

import java.io.File;

public class Sanat {
    static File txt;

    private String suomi;
    private String englanti;

    public Sanat(String suomi, String enkku) {
        this.suomi = suomi;
        englanti = enkku;
    }

    public String getSuomi() {
        return suomi;
    }

    public String getEnglanti() {
        return englanti;
    }

    @Override
    public String toString() {
        return "Suomeksi " + suomi + ", englanniksi " + englanti;

    }
}
