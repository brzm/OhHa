package sovelluslogiikka;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import sanapeli.Sanat;

public class Sovelluslogiikka {

    private Map<String, String> sanalista = new HashMap<>();
    private FileWriter kirjoittaja;

    public Sovelluslogiikka() throws IOException {
        kirjoittaja = new FileWriter("Tulokset.txt");
    }

    public String suomeksiSana(int i) {

        ArrayList<String> lista = new ArrayList<>();

        for (String di : sanalista.keySet()) {
            lista.add(di);
        }

        return lista.get(i);
    }

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


        if (tarkistus(suomi, enkku) == true) {
            lisaaSanapari(suomi, enkku);
        }
    }

    public boolean tarkistus(String fin, String eng) {
        Scanner lukija = new Scanner(System.in);
        System.out.println("Onko oikein: suomeksi " + fin + ", englanniksi " + eng + " (y/n)");
        String teksti = lukija.nextLine();

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
            System.out.println("Väärin, oikea vastaus on " +enkku);
            tuloksienListaaminenVaarin(suomi);
        }
    }

    public int kuinkaMontaListassa() {
        return sanalista.size();
    }

    public void tuloksienListaaminenOikein(String suomi) throws IOException {
        kirjoittaja.append(suomi + " oikein\n");

    }

    public void tuloksienListaaminenVaarin(String suomi) throws IOException {
        kirjoittaja.append(suomi + " väärin\n");

    }

    public void tuloksienLukeminen() throws FileNotFoundException {
        Scanner lukija = new Scanner("Tulokset.txt");

        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            System.out.println(rivi);
        }
    }

    public void sanatTiedostosta(File tiedosto) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        FileInputStream fis = new FileInputStream(tiedosto);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        ois.close();
        fis.close();
        
    }

    public void sanatTiedostoon() throws FileNotFoundException, IOException {
        File tiedosto = new File("Sanat.txt");
        FileOutputStream fos = new FileOutputStream(tiedosto);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(sanalista);
        oos.flush();
        oos.close();
        fos.close();

    }
}