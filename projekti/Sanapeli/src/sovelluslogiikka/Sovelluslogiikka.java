package sovelluslogiikka;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import sanapeli.Sanat;

public class Sovelluslogiikka {

    private Map<String, String> sanalista = new HashMap<>();
    private File tulokset;
    private File sanatTiedosto;

    public Sovelluslogiikka() throws IOException {

        tulokset = new File("Tulokset.txt");

        sanatTiedosto= new File("Sanat.txt");
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

    public void lisaaSanapari(String suomi, String englanti) {
        if (onkoJoListalla(suomi) == false) {
            sanalista.put(suomi, englanti);
        }

    }

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

    public boolean eiTyhjiaSanoja(String fin, String eng) {
        if (fin.equals("") || eng.equals("")) {
            System.out.println("Pitää kirjoittaa jotakin");
            return false;
        }
        return true;
    }

    public boolean tarkistus(String fin, String eng) {
        String teksti = "";
        if (eiTyhjiaSanoja(fin, eng)) {
            Scanner lukija = new Scanner(System.in);
            System.out.println("Onko oikein: suomeksi " + fin + ", englanniksi " + eng + " (y/n)");
            teksti = lukija.nextLine();
        }


        return mikaMerkki(teksti);
    }

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
        BufferedWriter bw = new BufferedWriter(new FileWriter(tulokset));
        bw.write(suomi+" oikein");
        bw.close();

    }

    public void tuloksienListaaminenVaarin(String suomi) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(tulokset));
        bw.write(suomi+" väärin");
        bw.close();

    }

    public void tuloksienLukeminen() throws FileNotFoundException {
        Scanner lukija = new Scanner(tulokset);

        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            System.out.println(rivi);
        }
    }

    public void sanatTiedostosta() throws FileNotFoundException, IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream(sanat));

        for (String key : properties.stringPropertyNames()) {
            sanalista.put(key, properties.get(key).toString());
        }

    }

    public void sanatTiedostoon() throws FileNotFoundException, IOException {

        Properties properties = new Properties();

        for (Map.Entry<String, String> entry : sanalista.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }

        properties.store(new FileOutputStream("Sanat"), null);

    }
}