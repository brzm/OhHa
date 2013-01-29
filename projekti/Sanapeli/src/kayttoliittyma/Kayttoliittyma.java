package kayttoliittyma;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import sanapeli.Sanantutkiminen;
import sovelluslogiikka.Sovelluslogiikka;

public class Kayttoliittyma {

    private Scanner lukija;
    private Sanantutkiminen tutki;
    private File tulokset ;
    private Sovelluslogiikka logiikka=new Sovelluslogiikka();

    public Kayttoliittyma(Scanner scanner, Sanantutkiminen tutki) throws IOException {
        lukija = scanner;
        this.tutki = tutki;
        tulokset = new File("Tulokset.txt");
    }

    public void kaynnista() throws IOException {


        while (true) {
            System.out.println("Komennot: lisaa, pelaa, lopeta, tulosta");
            String komento = lukija.nextLine();

            if (komento.equals("lisaa")) {
                annetutSanat();
            } else if (komento.equals("pelaa")) {
                pelaaPelia();
            } else if (komento.equals("lopeta")) {
                System.out.println("Kiitos näkemiin.");
                break;
            } else if (komento.equals("tulosta")) {
                tulostaSanat();
            } else if(komento.equals("tulokset")) {
                tulokset();
            } else {
                continue;
            }
        }
    }

    private void annetutSanat() {
        logiikka.sanojenAntaminen();
//        System.out.println("Anna suomeksi: ");
//        String suomi = lukija.nextLine();
//
//        if (suomi.length() <= 1) {
//            System.out.println("Yritä uudestaan");
//            annetutSanat();
//        }
//
//
//        System.out.println("Anna englanniksi: ");
//        String enkku = lukija.nextLine();
//
//        if (enkku.length() <= 1) {
//            System.out.println("Yritä uudestaan");
//            annetutSanat();
//        }
//
//        System.out.println("Onko oikein: suomeksi " + suomi + ", englanniksi " + enkku + " (y/n)");
//        String tarkistus = lukija.nextLine();
//        if (tarkistus.equals("y")) {
//            tutki.lisaaSanapari(suomi, enkku);
//        }

    }

    private void pelaaPelia() throws IOException {
        System.out.println("'lopeta' lopettaa pelin");
        StringBuilder builderi = new StringBuilder();
        Random arvonta = new Random();
        

        while (true) {
            int monesko = arvonta.nextInt(logiikka.kuinkaMontaListassa());
            System.out.println(monesko);
            String suomeksiSana = logiikka.suomeksiSana(monesko);
            String enkuksiSana = logiikka.englanniksiSana(suomeksiSana);

            System.out.println("Anna englanniksi: " + suomeksiSana);
            String vastaus = lukija.nextLine();

            if (vastaus.equals("lopeta")) {
                break;
            }

            if (vastaus.equals(enkuksiSana)) {
                System.out.println("Oikein");
                builderi.append(suomeksiSana +" Oikein\n");                
            } else {
                System.out.println("Väärin. Oikea vastaus on " + enkuksiSana);
                builderi.append(suomeksiSana + " Väärin\n");                
            }

        }

    }

    private void tulostaSanat() {
        logiikka.tulostaKaikki();
    }
    
    private void tulokset(){
        logiikka.tulostaTulokset(tulokset);
    }
}
