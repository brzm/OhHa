package sanapeli;

import java.io.FileNotFoundException;
import java.io.IOException;
import kayttoliittyma.Kayttoliittyma;


/**
 * main saatana
 * @author brzm
 */
public class Sanapeli {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        // TODO code application logic here 
        
        kayttoliittyma.graafinen.Graafinen di = new kayttoliittyma.graafinen.Graafinen();
        di.run();    
        
       
        
//        kayttoliittyma.Kayttoliittyma dinz=new Kayttoliittyma();
//        dinz.kaynnista();
    }
}
