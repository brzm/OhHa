package sovelluslogiikka;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import sanapeli.Sanat;

/**
 * hoitaa Kayttoliittyman metodeja
 *
 * @author BRZM
 */
public class Sovelluslogiikka {

    private Map<String, String> sanalista = new HashMap<>();
//    private File tulokset;
//    private File sanatTiedosto;
    private ArrayList<String> vastaukset = new ArrayList<>();
    private int kuinkamontaoikein = 0;

    public Sovelluslogiikka() throws IOException {

//        tulokset = new File("Tulokset.txt");
//        sanatTiedosto = new File("Sanat.txt");

    }
    
    public Map annaSanalista(){
        return sanalista;
    }

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

    /**
     * jos listalta löytyy jo annettu arvo, sitä ei lisätä uudestaan
     *
     * @param suomi
     * @return true, jos listalla on jo arvo
     */
    public boolean onkoJoListalla(String suomi) {
        for (String di : sanalista.keySet()) {
            if (suomi.equals(di)) {
                System.out.println("Sana on jo listalla");
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param sanat
     */
    public void lisaaSanapariSanat(Sanat sanat) {
        sanalista.put(sanat.getSuomi(), sanat.getEnglanti());
    }

    public void tulostaKaikki() {
        System.out.println("Sanoja yhteensä: " + sanalista.size());
        for (String di : sanalista.keySet()) {
            System.out.println(di + " : " + sanalista.get(di));
        }

    }

    public void sanojenAntaminen(String suomi, String enkku) {

        if (tarkistus(suomi, enkku)) {
            lisaaSanapari(suomi, enkku);
        }
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
            System.out.println("Pitää kirjoittaa jotakin");
            return false;
        }
        return true;
    }

    /**
     * tarkistaa onko annetut sanat oikein
     *
     * @param fin
     * @param eng
     * @return true jos on oikein
     */
    public boolean tarkistus(String fin, String eng) {

        String teksti = "";
        if (eiTyhjiaSanoja(fin, eng)) {
            Scanner lukija = new Scanner(System.in);
            System.out.println("Onko oikein: suomeksi " + fin + ", englanniksi " + eng + " (y/n)");
            teksti = lukija.nextLine();
        }


        return mikaMerkki(teksti);
    }

    /**
     * tarkistaa annetun merkin, ja lisätäänkö sanapari listaan
     *
     * @param merkki
     * @return true jos y
     */
    public boolean mikaMerkki(String merkki) {
        if (merkki.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    public void vastauksenHoito(String suomi, String enkku, String vastaus) throws IOException {
        if (enkku.equals(vastaus)) {
            System.out.println("Oikein");
            tuloksienListaaminenOikein(suomi);
        } else {
            System.out.println("Väärin, oikea vastaus on " + enkku);
            tuloksienListaaminenVaarin(suomi);
        }
    }

    public int kuinkaMontaListassa() {
        return sanalista.size();
    }

    public void tuloksienListaaminenOikein(String suomi) throws IOException {
        vastaukset.add(suomi + " oikein");
        kuinkamontaoikein++;
    }

    public void tuloksienListaaminenVaarin(String suomi) throws IOException {
        vastaukset.add(suomi + " väärin");
    }

    private int kuinkaMontaVaarin() {
        return vastaukset.size() - kuinkamontaoikein;
    }

    public void tuloksienLukeminen() throws FileNotFoundException {
        System.out.println("Yhteensä: " + vastaukset.size());
        System.out.println("Oikein: " + kuinkamontaoikein);
        System.out.println("Väärin: " + kuinkaMontaVaarin());

        for (String di : vastaukset) {
            System.out.println(di);
        }
    }

//    public void sanatTiedostosta() throws FileNotFoundException, IOException {
//
//        Properties properties = new Properties();
//        properties.load(new FileInputStream(sanatTiedosto));
//
//        for (String key : properties.stringPropertyNames()) {
//            sanalista.put(key, properties.get(key).toString());
//        }
//    }
//
//    public void sanatTiedostoon() throws FileNotFoundException, IOException {
//
//        Properties properties = new Properties();
//
//        for (Map.Entry<String, String> entry : sanalista.entrySet()) {
//            properties.put(entry.getKey(), entry.getValue());
//        }
//
//        properties.store(new FileOutputStream(sanatTiedosto), null);
//
//    }
}