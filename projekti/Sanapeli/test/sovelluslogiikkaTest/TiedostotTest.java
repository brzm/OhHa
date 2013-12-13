/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikkaTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sovelluslogiikka.Sovelluslogiikka;
import sovelluslogiikka.Tiedostot;
import sovelluslogiikka.Tulokset;

/**
 *
 * @author BRZM
 */
public class TiedostotTest {

    private Tiedostot tiedostot;

    public TiedostotTest() throws IOException {
        Tulokset tulos = new Tulokset();
        Sovelluslogiikka logiikka = new sovelluslogiikka.Sovelluslogiikka(tulos);

        tiedostot = new Tiedostot(logiikka, tulos);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void samaHenkilo() throws FileNotFoundException { 
        ArrayList<String> lista = new ArrayList();
        lista.add("seppo");
        lista.add("0123456789");
        lista.add("0123456789");  //sisällä olevat metodit sekoittaa tämän, ilman niitä toimii
        lista.add("--");
        tiedostot.annaListaTest(lista);
        String seppo = "seppo";

        assertEquals(true, tiedostot.onkoSamaHenkilo(seppo));
    }

    @Test
    public void samaHenkilo2() throws FileNotFoundException {
     ArrayList<String> lista = new ArrayList();
        lista.add("eiainakaanseppo");
        lista.add("make");
        lista.add("sepi");  //sama täällä
        lista.add("--");
        tiedostot.annaListaTest(lista);
        String seppo = "seppo";

        assertEquals(false, tiedostot.onkoSamaHenkilo(seppo));
    }
    
    @Test
    public void charIntiksi(){
        String di = "2";
        char charparametri= di.charAt(0);
        assertEquals(2, tiedostot.getNumericValue(charparametri));
    }
}
