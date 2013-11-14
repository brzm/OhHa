/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

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

/**
 * Hoitaa tiedoston lukemisen käynnistyessä ja tiedostoon kirjoittamiseen pelin
 * loputtua
 *
 * @author BRZM
 */
public class Tiedostot {

    private File tuloksetTiedosto;
    private File sanatTiedosto;
    private Map<String, String> sanalista;
    private Sovelluslogiikka logiikka;
    private ArrayList vanhatTulokset = new ArrayList();
    private Tulokset tulokset;
    private ArrayList<String> lista = new ArrayList<>();

    public Tiedostot(Sovelluslogiikka logiikka, Tulokset tulos) throws IOException {
        this.logiikka = logiikka;
        tuloksetTiedosto = new File("Tulokset.txt");
        sanatTiedosto = new File("Sanat.txt");
        sanalista = logiikka.annaSanalista();
        tulokset = tulos;
    }

    /**
     * lukee vanhat sanat tiedostosta ja heittää sanat sovelluslogiikan
     * sanalistaan
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void sanatTiedostosta() throws FileNotFoundException, IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream(sanatTiedosto));

        for (String key : properties.stringPropertyNames()) {
            logiikka.lisaaTiedostosta(key, properties.get(key).toString());
        }
    }

    /**
     * tallentaa sanat tiedostoon
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void sanatTiedostoon() throws FileNotFoundException, IOException {

        Properties properties = new Properties();

        for (Map.Entry<String, String> entry : sanalista.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }

        properties.store(new FileOutputStream(sanatTiedosto), null);

    }

    /**
     * tallentaa tulokset
     *
     * @param pelaaja
     * @throws IOException
     */
    public void tallennaTulokset(String pelaaja) throws IOException {
        FileWriter kirjoittaja = new FileWriter(tuloksetTiedosto);

        if (onkoSamaHenkilo(pelaaja)) {
            for (Object di : lista) {
            kirjoittaja.append(di.toString() + "\n");
        }

        kirjoittaja.append(pelaaja + "\n");
        kirjoittaja.append(tulokset.palautaKaikkiYht() + "\n");
        kirjoittaja.append(tulokset.palautaKaikkiOik() + "\n");
        kirjoittaja.append(tulokset.palautaKaikkiVaa() + "\n");
        kirjoittaja.append("-----------\n");
        kirjoittaja.close();
        } else {
             for (Object di : lista) {
            kirjoittaja.append(di.toString() + "\n");
        }

        kirjoittaja.append(pelaaja + "\n");
        kirjoittaja.append(tulokset.getYhteensa() + "\n");
        kirjoittaja.append(tulokset.getOikein() + "\n");
        kirjoittaja.append(tulokset.getVaarin() + "\n");
        kirjoittaja.append("-----------\n");
        kirjoittaja.close();
        }


       
    }

    @SuppressWarnings("empty-statement")
    public void lueVanhatTulokset() throws FileNotFoundException {

        Scanner skanneri = new Scanner(tuloksetTiedosto);
        while (skanneri.hasNextLine()) {
            String di = skanneri.nextLine();
            vanhatTulokset.add(di);
            lista.add(di);
        }
        skanneri.close();

    }

    public ArrayList<String> getVanhatTulokset() {
        return lista;
    }

    public boolean onkoSamaHenkilo(String nimi) throws FileNotFoundException {
        int i = 0;
        for (String di : lista) {
            if (nimi.equals(di)) {
                int yhteensa = lista.get(i+1).charAt(9);
                int oikein = lista.get(i+2).charAt(7);
                int vaarin = lista.get(i+3).charAt(7);
                
                tulokset.yhteensaVastauksia(yhteensa);
                tulokset.yhteensaOikein(oikein);
                tulokset.yhteensaVaarin(vaarin);
                poistaTiedostostaVanhaTulos(i);
                i++;
                return true;
            }
        }
        return false;
    }
    
    
    
    public void poistaTiedostostaVanhaTulos(int i){
        for (int j = 0; j < 5; j++) {
            lista.remove(i);
        }
    }
    
    
}
