package sanapeli;

import kayttoliittyma.Kayttoliittyma;
import java.io.IOException;
import java.util.Scanner;

public class Sanapeli {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here    
Scanner skanneri = new Scanner(System.in);
        Sanat di = new Sanat("koira", "dog");
        Sanat du = new Sanat("kissa", "cat");
        Sanantutkiminen es = new Sanantutkiminen(skanneri);
        System.out.println(di);
        System.out.println(du);
        
        es.lisaaSanapariSanat(du);
        es.lisaaSanapariSanat(di);
       
        
        
        Kayttoliittyma seppo = new Kayttoliittyma(skanneri, es);
        seppo.kaynnista();
    }
}
