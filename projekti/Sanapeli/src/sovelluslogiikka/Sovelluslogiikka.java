package sovelluslogiikka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * hoitaa sanalistan ja pelin metodit
 *
 * @author BRZM
 */
public class Sovelluslogiikka {

    private Map<String, String> sanalista = new HashMap<>();

    public Sovelluslogiikka(Tulokset tulos) {
    }

    public Map annaSanalista() {
        return sanalista;
    }

    /**
     * lisää sanaparin sanalistaan
     *
     * @param suomi
     * @param englanti
     */
    public void lisaaSanapari(String suomi, String englanti) {
        if (onkoJoListalla(suomi) == false) {
            sanalista.put(suomi, englanti);
        }
    }

    public void lisaaTiedostosta(String suomi, String englanti) {
        sanalista.put(suomi, englanti);
    }

    /**
     * palauttaa pelille suomeksi sanan
     *
     * @param i
     * @return
     */
    public String suomeksiSana(int i) {
        ArrayList<String> lista = new ArrayList<>();

        for (String di : sanalista.keySet()) {
            lista.add(di);
        }
        return lista.get(i);
    }

    /**
     * palauttaa englanniksi parametrina saadun arvon
     *
     * @param i parametri :D
     * @return englanniksi sana
     */
    public String englanniksiSana(String i) {        
        return sanalista.get(i);
    }

    /**
     * jos listalta löytyy jo annettu arvo, sitä ei lisätä uudestaan
     *
     * @param suomi
     * @return true, jos listalla on jo arvo
     */
    public boolean onkoJoListalla(String suomi) {
        for (String di : sanalista.keySet()) {
            if (suomi.equals(di)) {
                return true;
            }
        }

        return false;
    }

    /**
     * pakko kirjoittaa jotakin, eikä voi jättää sanoja "tyhjiksi"
     *
     * @param fin
     * @param eng
     * @return true jos ei ole listalla
     */
    public boolean eiTyhjiaSanoja(String fin, String eng) {
        if (fin.equals("") || eng.equals("")) {
            return false;
        }
        return true;
    }

    /**
     * tarkistaa annetun merkin, että lisätäänkö sanapari listaan
     *
     * @param merkki
     * @return true jos y ja lisätään listaan
     */
    public boolean mikaMerkki(String merkki) {
        if (merkki.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tarkistaa onko vastaus oikein vai väärin
     *
     * @param suomi
     * @param enkku
     * @param vastaus
     * @return true jos oikein
     * @throws IOException eipäs
     */
    public boolean vastauksenHoito(String suomi, String enkku, String vastaus) {
        if (enkku.equals(vastaus)) {
            return true;
        } else {
            return false;
        }
    }

    public int kuinkaMontaListassa() {
        return sanalista.size();
    }

    public void poistaSanaListalta(String sana) {
        sanalista.remove(sana);
    }
}