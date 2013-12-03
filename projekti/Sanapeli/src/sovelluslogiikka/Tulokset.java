/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.io.IOException;
import java.util.ArrayList;

/**
 * listaa pelin aikana tulleet tulokset
 *
 * @author BRZM
 */
public class Tulokset {

    private ArrayList<String> vastaukset = new ArrayList<>();
    private int kuinkamontaoikein = 0;
    private String kaikkiVastauksetLkm;
    private String kaikkiOikeinLkm;
    private String kaikkiVaarinLkm;

    /**
     * listaa oikein olevian tulosten lkm
     *
     * @param suomi
     * @throws IOException
     */
    public void tuloksienListaaminenOikein(String suomi) throws IOException {
        vastaukset.add(suomi + " oikein");
        kuinkamontaoikein++;
    }

    /**
     * listaa väärin olevien tulosten lkm
     *
     * @param suomi
     * @throws IOException
     */
    public void tuloksienListaaminenVaarin(String suomi) throws IOException {
        vastaukset.add(suomi + " väärin");
    }

    private int kuinkaMontaVaarin() {
        return vastaukset.size() - kuinkamontaoikein;
    }

    public String getOikein() {
        return "Oikein   " + kuinkamontaoikein;
    }

    public String getVaarin() {
        return "Väärin   " + kuinkaMontaVaarin();
    }

    public String getYhteensa() {
        return "Yhteensä " + vastaukset.size();
    }

    public ArrayList<String> getVastaukset() {
        return vastaukset;
    }

    public void yhteensaVastauksia(int i) {
        int yhteensa = i + vastaukset.size();
        kaikkiVastauksetLkm = "Yhteensä " + yhteensa;
    }

    public void yhteensaOikein(int i) {
        int yhteensa = i + kuinkamontaoikein;
        kaikkiOikeinLkm = "Oikein   " + yhteensa;
    }

    public void yhteensaVaarin(int i) {
        int yhteensa = i + kuinkaMontaVaarin();
        kaikkiVaarinLkm = "Väärin   " + yhteensa;
    }

    public String palautaKaikkiYht() {
        return kaikkiVastauksetLkm;
    }

    public String palautaKaikkiOik() {
        return kaikkiOikeinLkm;
    }

    public String palautaKaikkiVaa() {
        return kaikkiVaarinLkm;
    }
}
