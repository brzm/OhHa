package kayttoliittyma;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
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

    public Kayttoliittyma() throws IOException {
        tulokset = new Tulokset();
        logiikka = new Sovelluslogiikka(tulokset);

        tiedostot = new Tiedostot(logiikka, tulokset);
    }

    public void kaynnista() throws IOException {

        tiedostot.sanatTiedostosta();
        tiedostot.lueVanhatTulokset();

        System.out.println("Anna nimi: ");
        String pelaaja = lukija.nextLine();


        while (true) {
            System.out.println("Komennot: lisaa, pelaa, lopeta, tulosta, tulokset, vanhat");
            String komento = lukija.nextLine();

            if (komento.equals("lisaa")) {
                annetutSanat();
            } else if (komento.equals("pelaa")) {
                pelaaPelia();
            } else if (komento.equals("lopeta")) {
                tiedostot.sanatTiedostoon();
                tiedostot.tallennaTulokset(pelaaja);
                System.out.println("Kiitos näkemiin.");
                break;
            } else if (komento.equals("tulosta")) {
                tulostaSanat(logiikka.annaSanalista());
            } else if (komento.equals("tulokset")) {
                tulokset(tulokset.getYhteensa(), tulokset.getOikein(), tulokset.getVaarin(), tulokset.getVastaukset());
            } else if (komento.equals("vanhat")) {
                vanhatTulokset(tiedostot.getVanhatTulokset());
            }
        }
    }

    private void annetutSanat() {
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

    private boolean onkoTyhjaSana(String sana) {
        if (logiikka.eiTyhjiaSanoja(sana, sana) == false) {
            System.out.println("Pitää kirjoittaa jotakin");
        }
        return logiikka.eiTyhjiaSanoja(sana, sana);

    }

    private void pelaaPelia() throws IOException {

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

    private void tulostaSanat(Map<String, String> map) {
        System.out.println("Sanoja yhteensä: " + logiikka.kuinkaMontaListassa());
        for (String di : map.keySet()) {
            System.out.println(di + " = " + map.get(di));
        }
    }

    private void tulokset(String yht, String oik, String vaar, ArrayList<String> lista) throws FileNotFoundException {
        System.out.println(yht);
        System.out.println(oik);
        System.out.println(vaar);

        for (String di : lista) {
            System.out.println(di);
        }
    }

    private void vanhatTulokset(ArrayList<String> lista) throws FileNotFoundException {
        for (String di : lista) {
            System.out.println(di);
        }
    }
}
