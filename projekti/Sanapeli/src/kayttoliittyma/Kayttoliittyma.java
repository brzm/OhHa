package kayttoliittyma;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import sovelluslogiikka.Sovelluslogiikka;
import sovelluslogiikka.Tiedostot;
import sovelluslogiikka.Tulokset;

/**
 * Hoitaa printtaukset ja komennot
 *
 * @author BRZM
 */
public class Kayttoliittyma {
    
    private Scanner lukija = new Scanner(System.in);
    private Sovelluslogiikka logiikka;
    private Tiedostot tiedostot;
    private Tulokset tulokset;
    private JTextArea areaSanat;
    private JTextArea areaVtulokset;
    
    public Kayttoliittyma() throws IOException {
        tulokset = new Tulokset();
        logiikka = new Sovelluslogiikka(tulokset);
        
        tiedostot = new Tiedostot(logiikka, tulokset);
    }
    
    public void tiedostojenHaku() throws FileNotFoundException, IOException {
        tiedostot.sanatTiedostosta();
        tiedostot.lueVanhatTulokset();
    }

    /**
     * Käynnistää pelin, kysyy komennot ja siirtää vastuun eteenpäin
     * tekstiversio
     *
     * @throws IOException
     */
    public void kaynnista(String komentoads) throws IOException {
        
        tiedostojenHaku();

//        System.out.println("Anna nimi: ");
        String pelaaja = "asd";


//        while (true) {
        System.out.println("Komennot: lisaa, pelaa, lopeta, tulosta, tulokset, vanhat, poista");
        String komento = komentoads;
        
        if (komento.equals("lisaa")) {
            annetutSanat();
        } else if (komento.equals("pelaa")) {
            pelaaPelia();
        } else if (komento.equals("lopeta")) {
            tiedostot.sanatTiedostoon();
            tiedostot.tallennaTulokset(pelaaja);
            System.out.println("Kiitos näkemiin.");
//                break;
        } else if (komento.equals("tulosta")) {
            tulostaSanat(logiikka.annaSanalista());
        } else if (komento.equals("tulokset")) {
            tulokset(tulokset.getYhteensa(), tulokset.getOikein(), tulokset.getVaarin(), tulokset.getVastaukset());
        } else if (komento.equals("vanhat")) {
            vanhatTulokset(tiedostot.getVanhatTulokset());
        } else if (komento.equals("poista")) {
            poistaSana("das");
        } else if (komento.equals("arvonta")) {
        }
//        }
    }
    
    public int sananArvonta() {
        Random arvonta = new Random();
        int monesko = arvonta.nextInt(logiikka.kuinkaMontaListassa());
        return monesko;
    }
    
    public String suomiSanaPeli(int i) {
        String suomisana = logiikka.suomeksiSana(i);
        System.out.println("suomi sana peli " + suomisana);
        return suomisana;
    }
    
    public String englantiSanaPeli(String sana) {
        System.out.println(logiikka.englanniksiSana(sana));
        return logiikka.englanniksiSana(sana);
    }
    
    public boolean onkoListallaSananLisays(String sana) {
        if (logiikka.onkoJoListalla(sana)) {
            return true;
        }
        return false;
    }

    /**
     * Kysyy sanat, js tarkistaa ettei tyhjiä tai samoja sanoja lisätä
     * tekstiversio
     */
    public void annetutSanat() {
        System.out.println("Anna suomeksi: ");
        String suomi = lukija.nextLine();
        
        if (logiikka.onkoJoListalla(suomi)) {
            System.out.println("Sana on jo listalla");
        }
        
        onkoTyhjaSana(suomi);
        
        if (logiikka.onkoJoListalla(suomi) == false && onkoTyhjaSana(suomi)) {
            
            System.out.println("Anna englanniksi: ");
            String enkku = lukija.nextLine();
            
            
            if (onkoTyhjaSana(enkku)) {
                System.out.println("Onko oikein, suomeksi: " + suomi + ", englanniksi: " + enkku + " (y/n)?");
                String tarkistus = lukija.nextLine();
                
                if (logiikka.mikaMerkki(tarkistus)) {
                    logiikka.lisaaSanapari(suomi, enkku);
                }
            }
        }
    }

    /**
     * varmistaa ettei ole tyhjä sana
     *
     * @param sana
     * @return
     */
    private boolean onkoTyhjaSana(String sana) {
        if (logiikka.eiTyhjiaSanoja(sana, sana) == false) {
            System.out.println("Pitää kirjoittaa jotakin");
        }
        return logiikka.eiTyhjiaSanoja(sana, sana);
        
    }

    /**
     * pelin pelaaminen, kysyy sanat ja antaa tulokset muualle
     *
     * @throws IOException
     */
    public void pelaaPelia() throws IOException {
        
        System.out.println("'lopeta' lopettaa pelin");
        Random arvonta = new Random();
        
        
        while (true) {
            int monesko = arvonta.nextInt(logiikka.kuinkaMontaListassa());
            
            String suomeksiSana = logiikka.suomeksiSana(monesko);
            String enkuksiSana = logiikka.englanniksiSana(suomeksiSana);
            
            System.out.println("Anna englanniksi: " + suomeksiSana);
            String vastaus = lukija.nextLine();
            
            if (vastaus.equals("lopeta")) {
                break;
            }
            
            if (logiikka.vastauksenHoito(suomeksiSana, enkuksiSana, vastaus)) {
                tulokset.tuloksienListaaminenOikein(suomeksiSana);
                System.out.println("Oikein");
            } else {
                System.out.println("Väärin, oikea vastaus on " + enkuksiSana);
                tulokset.tuloksienListaaminenVaarin(suomeksiSana);
            }
        }
    }
    
    public JLabel suomeksiSanaPeli() {
        Random arvonta = new Random();
        int monesko = arvonta.nextInt(logiikka.kuinkaMontaListassa());
        
        String suomeksiSana = logiikka.suomeksiSana(monesko);
        JLabel sana = new JLabel("Suomeksi: " + suomeksiSana);
        return sana;
    }

    /**
     * alempaan liittyy
     *
     * @return
     */
    public JTextArea getSanat() {
        tulostaSanat(logiikka.annaSanalista());
        return areaSanat;
    }

    /**
     * tulostaa sanalistan sanat
     *
     * @param map
     */
    private void tulostaSanat(Map<String, String> map) {
        areaSanat = new JTextArea();
        areaSanat.append("Sanoja yhteensä: " + logiikka.kuinkaMontaListassa() + "\n");
        System.out.println("Sanoja yhteensä: " + logiikka.kuinkaMontaListassa());
        for (String di : map.keySet()) {
            areaSanat.append(di + " = " + map.get(di) + "\n");
            System.out.println(di + " = " + map.get(di));
        }
        
    }

    /**
     * tulostaa tämänhetkiset tulokset
     *
     * @param yht
     * @param oik
     * @param vaar
     * @param lista
     * @throws FileNotFoundException
     */
    private void tulokset(String yht, String oik, String vaar, ArrayList<String> lista) throws FileNotFoundException {
        System.out.println(yht);
        System.out.println(oik);
        System.out.println(vaar);
        
        for (String di : lista) {
            System.out.println(di);
        }
    }

    /**
     * tulostaa tiedostossa olevat vanhat tulokset
     *
     * @param lista
     * @throws FileNotFoundException
     */
    public void vanhatTulokset(ArrayList<String> lista) throws FileNotFoundException {
        
        for (String di : lista) {
            areaVtulokset.append(di + "\n");
            System.out.println(di);
        }
        
    }
    
    public JTextArea getVanhatTulokset() throws FileNotFoundException {
        vanhatTulokset(tiedostot.getVanhatTulokset());
        return areaVtulokset;
    }
    
    public void poistaSana(String sana) {
        logiikka.poistaSanaListalta(sana);
    }
    
    public void lisaaSanat(String suomi, String eng) {
        logiikka.lisaaSanapari(suomi, eng);
    }
    
    public boolean tyhjaSana(String sana) {
        if (sana.equals("")) {
            return true;
        }
        return false;
    }
    
    public boolean tyhjaSanaKaks(String eka, String toka) {
        if (eka.equals("") || toka.equals("")) {
            return true;
        }
        return false;
    }
}
