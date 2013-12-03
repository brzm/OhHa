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
                
                String yhteensa = lista.get(i + 1); //yhteensa + arvo
                if (yhteensa.length() == 10) {
                    char paikka = yhteensa.charAt(9); //paikan määritys
                    int numeroYht = getNumericValue(paikka); //itse numero
                    tulokset.yhteensaVastauksia(numeroYht);
                } else {
                    String yhteensaYht =""+ yhteensa.charAt(9) + yhteensa.charAt(10);
                    tulokset.yhteensaVastauksia(kaksiNumeroa(yhteensaYht));
                }

                String oikein = lista.get(i + 2);
                if (oikein.length() == 10) {
                    char paikkaOikein = oikein.charAt(9);
                    int numeroOikein = getNumericValue(paikkaOikein);
                    tulokset.yhteensaOikein(numeroOikein);
                } else {
                    String yhtOik = "" + oikein.charAt(9) + oikein.charAt(10);
                    tulokset.yhteensaOikein(kaksiNumeroa(yhtOik));
                }


                String vaarin = lista.get(i + 3);
                if (vaarin.length() == 10) {
                    char paikkaVaarin = vaarin.charAt(9);
                    int numeroVaarin = getNumericValue(paikkaVaarin);
                    tulokset.yhteensaVaarin(numeroVaarin);
                } else {
                    String yhtVaar = "" + vaarin.charAt(9) + vaarin.charAt(10);
                    tulokset.yhteensaVaarin(kaksiNumeroa(yhtVaar));
                }

                poistaTiedostostaVanhaTulos(i);
                i++;
                return true;
            }
        }
        return false;
    }

    private int kaksiNumeroa(String numerot) {
        return Integer.parseInt(numerot);
    }

    public static int getNumericValue(char ch) {
        int numero = Character.getNumericValue(ch);
        return numero;
    }

    public void poistaTiedostostaVanhaTulos(int i) {
        for (int j = 0; j < 5; j++) {
            lista.remove(i);
        }
    }
}
