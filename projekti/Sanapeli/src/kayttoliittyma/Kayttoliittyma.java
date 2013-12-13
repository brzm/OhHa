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
//        String pelaaja = lukija.nextLine();


//        while (true) {
        System.out.println("Komennot: lisaa, pelaa, lopeta, tulosta, tulokset, vanhat, poista");
        String komento = komentoads;

        if (komento.equals("lisaa")) {
            annetutSanat();
        } else if (komento.equals("pelaa")) {
            pelaaPelia();
        } else if (komento.equals("lopeta")) {
            tiedostot.sanatTiedostoon();
//                tiedostot.tallennaTulokset(pelaaja);
            System.out.println("Kiitos näkemiin.");
//                break;
        } else if (komento.equals("tulosta")) {
            tulostaSanat(logiikka.annaSanalista());
        } else if (komento.equals("tulokset")) {
            tulokset(tulokset.getYhteensa(), tulokset.getOikein(), tulokset.getVaarin(), tulokset.getVastaukset());
        } else if (komento.equals("vanhat")) {
            vanhatTulokset(tiedostot.getVanhatTulokset());
        } else if (komento.equals("poista")) {
            poistaSanaTeksti();
        }
//        }
    }

    /**
     * graafinen arpoo sanan listalta
     *
     * @return
     */
    public int sananArvonta() {
        Random arvonta = new Random();
        int monesko = arvonta.nextInt(logiikka.kuinkaMontaListassa());
        return monesko;
    }

    /**
     * graafinen antaa arvotun sanan suomeksi
     *
     * @param i
     * @return
     */
    public String suomiSanaPeli(int i) {
        String suomisana = logiikka.suomeksiSana(i);
        return suomisana;
    }

    /**
     * graafinen palauttaa oikean vastauksen englanniksi
     *
     * @param sana
     * @return
     */
    public String englantiSanaPeli(String sana) {
        return logiikka.englanniksiSana(sana);
    }

    /**
     * graafinen ei anna lisätä samaa sanaa
     *
     * @param sana
     * @return
     */
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
     * varmistaa ettei ole tyhjä sana tekstiversio
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
     * pelin pelaaminen, kysyy sanat ja antaa tulokset muualle tekstiversio
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

    /**
     * graafinen antaa suomenkielisen sanan
     *
     * @return
     */
    public JLabel suomeksiSanaPeli() {
        Random arvonta = new Random();
        int monesko = arvonta.nextInt(logiikka.kuinkaMontaListassa());

        String suomeksiSana = logiikka.suomeksiSana(monesko);
        JLabel sana = new JLabel("Suomeksi: " + suomeksiSana);
        return sana;
    }

    /**
     * graafinen alempaan liittyy palauttaa sanat
     *
     * @return
     */
    public JTextArea getSanat() {
        tulostaSanat(logiikka.annaSanalista());
        return areaSanat;
    }

    /**
     * graafinen/teksti tulostaa sanalistan sanat
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
     * teksti tulostaa tämänhetkiset tulokset
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

    /**
     * graafinen
     *
     * @return
     * @throws FileNotFoundException
     */
    public JTextArea getVanhatTulokset() throws FileNotFoundException {
        vanhatTulokset(tiedostot.getVanhatTulokset());
        return areaVtulokset;
    }

    public void poistaSana(String sana) {
        logiikka.poistaSanaListalta(sana);
    }

    /**
     * graafinen lisää annetut sanat
     *
     * @param suomi
     * @param eng
     */
    public void lisaaSanat(String suomi, String eng) {
        logiikka.lisaaSanapari(suomi, eng);
    }

    /**
     * graafinen tutkii sanan poiston ettei tyhjä
     *
     * @param sana
     * @return
     */
    public boolean tyhjaSana(String sana) {
        if (sana.equals("")) {
            return true;
        }
        return false;
    }

    /**
     * graafinen tutkii sanan lisäyksen sanat
     *
     * @param eka
     * @param toka
     * @return
     */
    public boolean tyhjaSanaKaks(String eka, String toka) {
        if (eka.equals("") || toka.equals("")) {
            return true;
        }
        return false;
    }

    /**
     * graafinen palauttaa pelin oikein olevien lkm
     *
     * @return
     */
    public String getTuloksetOikein() {
        return tulokset.getOikein();
    }

    /**
     * graafinen pelin väärin lkm
     *
     * @return
     */
    public String getTuloksetVaarin() {
        return tulokset.getVaarin();
    }

    /**
     * graafinen pelin käyttökerrat lkm
     *
     * @return
     */
    public String getTuloksetYhteensa() {
        return tulokset.getYhteensa();
    }

    /**
     * graafinen pelin oikein lkm listaus
     *
     * @param sana
     * @throws IOException
     */
    public void lisaaTuloksetOikein(String sana) throws IOException {
        tulokset.tuloksienListaaminenOikein(sana);
    }

    /**
     * graafinen pelin väärin lkm listaus
     *
     * @param sana
     * @throws IOException
     */
    public void lisaaTuloksetVaarin(String sana) throws IOException {
        tulokset.tuloksienListaaminenVaarin(sana);
    }

    /**
     * tekstiversion poisto
     */
    private void poistaSanaTeksti() {
        System.out.println("Kirjoita poistettava sana: ");
        String sana = lukija.nextLine();
        logiikka.poistaSanaListalta(sana);
    }

    /**
     * graafinen ruksia painaessa ottaa tulokset talteen
     *
     * @param nimi
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void ohjelmanLopetus(String nimi) throws FileNotFoundException, IOException {
        tiedostot.sanatTiedostoon();
        tiedostot.tallennaTulokset(nimi);
    }

    public void lisaaOikeinLkm() {
        tulokset.lisaaOikein();
    }

    public int getOikeinLkm() {
        System.out.println("oik" + tulokset.grOikeinLkm());
        return tulokset.grOikeinLkm();
    }

    public void lisaaVaarinLkm() {
        tulokset.lisaaVaarin();
    }

    public int getVaarLkm() {
        return tulokset.grVaarLkm();
    }

    public void lisaaYhtLkm() {
        tulokset.lisaaYht();
    }

    public int getYhtLkm() {
        return tulokset.grYhtLkm();
    }
}
