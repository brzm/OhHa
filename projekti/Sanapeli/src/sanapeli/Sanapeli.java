package sanapeli;

import java.io.IOException;
import java.util.Scanner;

public class Sanapeli {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here    

        Sanat di = new Sanat("koira", "dog");
        Sanat du = new Sanat("kissa", "cat");
        Sanantutkiminen es = new Sanantutkiminen();
        System.out.println(di);
        System.out.println(du);
        
        es.lisaaSanapariSanat(du);
        es.lisaaSanapariSanat(di);
       
        
        Scanner skanneri = new Scanner(System.in);
        Kayttoliittyma seppo = new Kayttoliittyma(skanneri, es);
        seppo.kaynnista();
    }
}
