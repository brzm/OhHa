package sanapeli;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import kayttoliittyma.Kayttoliittyma;
import sovelluslogiikka.Sovelluslogiikka;

public class Sanapeli {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        // TODO code application logic here    
        Scanner skanneri = new Scanner(System.in);
        Sanat di = new Sanat("koira", "dog");
        Sanat du = new Sanat("kissa", "cat");
        

        System.out.println(di);
        System.out.println(du);



        Sovelluslogiikka pom = new Sovelluslogiikka();
        pom.lisaaSanapariSanat(du);
        pom.lisaaSanapariSanat(di);
        pom.sanatTiedostosta();

        Kayttoliittyma seppo = new Kayttoliittyma(skanneri);
        seppo.kaynnista();
    }
}
