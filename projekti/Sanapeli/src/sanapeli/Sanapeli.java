package sanapeli;

import java.io.FileNotFoundException;
import java.io.IOException;
import kayttoliittyma.Kayttoliittyma;

public class Sanapeli {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        // TODO code application logic here    
        
        Kayttoliittyma seppo = new Kayttoliittyma();
        seppo.kaynnista();
    }
}
