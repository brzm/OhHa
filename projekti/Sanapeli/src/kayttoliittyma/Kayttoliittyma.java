package kayttoliittyma;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import sovelluslogiikka.Sovelluslogiikka;

public class Kayttoliittyma {

    private Scanner lukija;
    private Sovelluslogiikka logiikka;

    public Kayttoliittyma(Scanner scanner) throws IOException {
        lukija = scanner;
        logiikka = new Sovelluslogiikka();
    }

   
    public void kaynnista() throws IOException {


        while (true) {
            System.out.println("Komennot: lisaa, pelaa, lopeta, tulosta, tulokset");
            String komento = lukija.nextLine();

            if (komento.equals("lisaa")) {
                annetutSanat();
            } else if (komento.equals("pelaa")) {
                pelaaPelia();
            } else if (komento.equals("lopeta")) {
                logiikka.sanatTiedostoon();
                System.out.println("Kiitos näkemiin.");
                break;
            } else if (komento.equals("tulosta")) {
                tulostaSanat();
            } else if (komento.equals("tulokset")) {
                tulokset();
            } else {
                continue;
            }
        }
    }

    private void annetutSanat() {
        System.out.println("Anna suomeksi: ");
        String suomi = lukija.nextLine();
        
        System.out.println("Anna englanniksi: ");
        String enkku = lukija.nextLine();
                
        logiikka.sanojenAntaminen(suomi,enkku);

    }
    
    

    private void pelaaPelia() throws IOException {
        
        System.out.println("'lopeta' lopettaa pelin");
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

            logiikka.vastauksenHoito(suomeksiSana, enkuksiSana, vastaus);
        }

    }

    private void tulostaSanat() {
        logiikka.tulostaKaikki();
    }

    private void tulokset() throws FileNotFoundException {
        logiikka.tuloksienLukeminen();
    }
    
    
}
