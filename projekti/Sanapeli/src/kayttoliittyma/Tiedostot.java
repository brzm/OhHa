/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import sovelluslogiikka.Sovelluslogiikka;

/**
 * Hoitaa tiedoston lukemisen käynnistyessä ja tiedostoon kirjoittamiseen pelin
 * loputtua
 *
 * @author BRZM
 */
public class Tiedostot {

    private File tulokset;
    private File sanatTiedosto;
    private Map<String, String> sanalista;
    private Sovelluslogiikka logiikka = new Sovelluslogiikka();

    public Tiedostot() throws IOException {
        
        tulokset = new File("Tulokset.txt");
        sanatTiedosto = new File("Sanat.txt");
        sanalista= logiikka.annaSanalista();
    }

    public void sanatTiedostosta() throws FileNotFoundException, IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream(sanatTiedosto));

        for (String key : properties.stringPropertyNames()) {
            sanalista.put(key, properties.get(key).toString());
        }
    }

    public void sanatTiedostoon() throws FileNotFoundException, IOException {

        Properties properties = new Properties();

        for (Map.Entry<String, String> entry : sanalista.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }

        properties.store(new FileOutputStream(sanatTiedosto), null);

    }
    
    public void tallennaTulokset(String pelaaja) throws IOException{
        FileWriter kirjoittaja = new FileWriter(tulokset);
        
        String yht=logiikka.getYhteensa();
        String oikein = logiikka.getOikein();
        String vaarin = logiikka.getVaarin();
        
        kirjoittaja.append(pelaaja+"\n");
        kirjoittaja.append(yht+"\n");
        kirjoittaja.append(oikein+"\n");
        kirjoittaja.append(vaarin+"\n");
        kirjoittaja.append("-----------\n");                
        kirjoittaja.close();
    }
    
    public void testi(){
        System.out.println(logiikka.getYhteensa());
    }
    
    
}
