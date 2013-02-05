/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import sovelluslogiikka.Sovelluslogiikka;

/**
 *Hoitaa tiedoston lukemisen käynnistyessä ja tiedostoon kirjoittamiseen pelin loputtua
 * @author BRZM
 */
public class Tiedostot {
    
    private File tulokset;
    private File sanatTiedosto;    
    private Map<String,String> sanalista;
    
    public Tiedostot() throws IOException{
        Sovelluslogiikka logiikka=new Sovelluslogiikka();
        tulokset = new File("Tulokset.txt");
        sanatTiedosto = new File("Sanat.txt");
        sanalista=logiikka.annaSanalista();
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
}
    

