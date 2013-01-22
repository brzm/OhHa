package sanapeli;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Kayttoliittyma {

    private Scanner lukija;
    private Sanantutkiminen tutki;

    public Kayttoliittyma(Scanner scanner, Sanantutkiminen tutki) {
        lukija = scanner;
        this.tutki = tutki;
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
            } else {
                continue;
            }
        }
    }

    private void annetutSanat() {
        System.out.println("Anna suomeksi: ");
        String suomi = lukija.nextLine();

        if (suomi.length() <= 1) {
            System.out.println("Yritä uudestaan");
            annetutSanat();
        }


        System.out.println("Anna englanniksi: ");
        String enkku = lukija.nextLine();

        if (enkku.length() <= 1) {
            System.out.println("Yritä uudestaan");
            annetutSanat();
        }

        System.out.println("Onko oikein: suomeksi " + suomi + ", englanniksi " + enkku + " (y/n)");
        String tarkistus = lukija.nextLine();
        if (tarkistus.equals("y")) {
            tutki.lisaaSanapari(suomi, enkku);
        }

    }

    private void pelaaPelia() throws IOException {
        FileWriter tulokset= new FileWriter("Tulokset.txt");
        Random arvonta = new Random();
        while (true) {
            int monesko = arvonta.nextInt(tutki.kuinkaMontaListassa());
            String suomeksiSana = tutki.suomeksiSana(monesko);
            String enkuksiSana = tutki.englanniksiSana(suomeksiSana);

            System.out.println("Anna englanniksi: " + suomeksiSana);
            String vastaus = lukija.nextLine();
            
            if(vastaus.equals("lopeta")){
                break;
            }

            if (vastaus.equals(enkuksiSana)) {
                System.out.println("Oikein");
                tulokset.append(suomeksiSana+" Oikein\n");
            } else {
                System.out.println("Väärin. Oikea vastaus on " + enkuksiSana);
                tulokset.append(suomeksiSana+" Väärin\n");
            }

        }

    }

    private void tulostaSanat() {
        tutki.tulostaKaikki();
    }
}
