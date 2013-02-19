package kayttoliittyma;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import sovelluslogiikka.Sovelluslogiikka;

/**
 * Hoitaa printtaukset ja komennot
 *
 * @author BRZM
 */
public class Kayttoliittyma {

    private Scanner lukija = new Scanner(System.in);
    private Sovelluslogiikka logiikka;
    private Tiedostot tiedostot;

    public Kayttoliittyma() throws IOException {
        logiikka = new Sovelluslogiikka();
        tiedostot = new Tiedostot(logiikka);
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
                tulokset(logiikka.getYhteensa(), logiikka.getOikein(), logiikka.getVaarin(),logiikka.getVastaukset());
            } else if (komento.equals("vanhat")) {
                vanhatTulokset(tiedostot.getVanhatTulokset());
            }
        }
    }

    private void annetutSanat() {
        System.out.println("Anna suomeksi: ");
        String suomi = lukija.nextLine();

        System.out.println("Anna englanniksi: ");
        String enkku = lukija.nextLine();

        logiikka.sanojenAntaminen(suomi, enkku);

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

            logiikka.vastauksenHoito(suomeksiSana, enkuksiSana, vastaus);
        }

    }

    private void tulostaSanat(Map<String,String> map) {
        for(String di:map.keySet()){
            System.out.println(di+" = "+map.get(di));
        }
    }

    private void tulokset(String yht,String oik,String vaar,ArrayList<String> lista) throws FileNotFoundException {
        System.out.println(yht);
        System.out.println(oik);
        System.out.println(vaar);
        
        for(String di:lista){
            System.out.println(di);
        }
    }

    private void vanhatTulokset(ArrayList<String> lista) throws FileNotFoundException {
        for(String di:lista){
            System.out.println(di);
        }
    }
}
