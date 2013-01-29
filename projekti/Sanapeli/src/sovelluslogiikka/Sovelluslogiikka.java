/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import sanapeli.Sanat;


public class Sovelluslogiikka {
    
    private Map<String, String> sanalista = new HashMap<>();
    private Scanner lukija;

    

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
        
        ArrayList<String> lista = new ArrayList<>();
        
        for(String di : sanalista.keySet()){
            lista.add(di);
        }
        
        return lista.get(i);
    }

    public String englanniksiSana(String i) {

        return sanalista.get(i);
    }

    public void tulostaTulokset(File tiedosto) {

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
        
        if (teksti.equals("y")) {
            return true;
        } else if(teksti.equals("n")){
            return false;
        } else {
            tarkistus(fin, eng);
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

    

