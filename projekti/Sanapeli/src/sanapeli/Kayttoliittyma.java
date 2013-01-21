package sanapeli;

import java.util.Scanner;

public class Kayttoliittyma {

    private Scanner lukija;
    private Sanantutkiminen tutki;

    public Kayttoliittyma(Scanner scanner, Sanantutkiminen tutki) {
        lukija = scanner;
        this.tutki = tutki;
    }

    public void kaynnista() {


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

    private void pelaaPelia() {
        System.out.println("es pärisee");
    }

    private void tulostaSanat() {
        tutki.tulostaKaikki();
    }
}
