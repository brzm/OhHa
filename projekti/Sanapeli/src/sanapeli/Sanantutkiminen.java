package sanapeli;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Sanantutkiminen {

    private Map<String, String> sanalista = new HashMap<>();
    private Scanner lukija;

    public Sanantutkiminen(Scanner lukija) {
        this.lukija = lukija;
    }

    public void lisaaSanapari(String suomi, String englanti) {
        sanalista.put(suomi, englanti);
    }

    /**
     *
     * @param sanat
     */
    public void lisaaSanapariSanat(Sanat sanat) {
        sanalista.put(sanat.getSuomi(), sanat.getEnglanti());
    }

    public void tulostaKaikki() {
        for (String di : sanalista.keySet()) {
            System.out.println(di + " : " + sanalista.get(di));
        }
    }

    public int kuinkaMontaListassa() {
        int i = 0;
        for (String di : sanalista.keySet()) {
            i++;
        }
        System.out.println("PERKELEESTI SANOJA ELI " + i);
        return i;
    }

    public String suomeksiSana(int i) {
        String suomi = "";
        int j = 0;
        while (j < i) {
            for (String di : sanalista.keySet()) {
                suomi = "";
                suomi = di + "";
                j++;

            }
        }

        return suomi;
    }

    public String englanniksiSana(String i) {

        return sanalista.get(i);
    }

    public void tulostaTulokset(FileWriter tiedosto) {

        while (lukija.hasNext()) {
        }
    }

    public void sanojenAntaminen() {
        System.out.println("Anna suomeksi: ");
        String suomi = lukija.nextLine();

        onkoLiianLyhyt(suomi);

        System.out.println("Anna englanniksi: ");
        String enkku = lukija.nextLine();

        onkoLiianLyhyt(enkku);
        
        if (tarkistus(suomi, enkku) == true) {
            lisaaSanapari(suomi, enkku);
        } 
    }

    public boolean tarkistus(String fin, String eng) {
        System.out.println("Onko oikein: suomeksi " + fin + ", englanniksi " + eng + " (y/n)");
        String teksti = lukija.nextLine();
        
        if(teksti.length()==0){
            tarkistus(fin, eng);
        }

        if (teksti.equals("y")) {
            return true;
        }

        return false;
    }
    
    public void onkoLiianLyhyt(String teksti){
        if(teksti.length()<=1){
            System.out.println("Yritä uudestaan");
            sanojenAntaminen();
        }
    }
}
