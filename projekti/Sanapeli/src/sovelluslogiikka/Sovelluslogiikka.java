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

    public Sovelluslogiikka(){
        
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

}