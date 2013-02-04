/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikkaTest;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sovelluslogiikka.Sovelluslogiikka;

/**
 *
 * @author BRZM
 */
public class SovelluslogiikkaTest {

    private Sovelluslogiikka di;

    public SovelluslogiikkaTest() throws IOException {
        di = new Sovelluslogiikka();
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
    public void englanninPalautus() throws IOException {

        di.lisaaSanapari("di", "du");

        assertEquals("du", di.englanniksiSana("di"));
    }

    @Test
    public void suomenPalautus() throws IOException {

        di.lisaaSanapari("di", "du");
        di.lisaaSanapari("es", "cs");


        assertEquals("di", di.suomeksiSana(0));
    }

    @Test
    public void suomenPalautus2() {
        di.lisaaSanapari("di", "du");
        di.lisaaSanapari("es", "cs");
        di.lisaaSanapari("juuh", "elikkäs");

        assertEquals("juuh", di.suomeksiSana(2));

    }

    @Test
    public void listanKoko() throws IOException {

        di.lisaaSanapari("di", "du");
        di.lisaaSanapari("es", "cs");

        assertEquals(2, di.kuinkaMontaListassa());
    }

    @Test
    public void merkkiY() {

        assertEquals(true, di.mikaMerkki("y"));
    }

    @Test
    public void merkkiN() {
        assertEquals(false, di.mikaMerkki("n"));
    }

    @Test
    public void onListalla() {
        di.lisaaSanapari("di", "du");
        di.lisaaSanapari("es", "cs");

        assertEquals(true, di.onkoJoListalla("es"));
    }
    
    @Test
    public void eiListalla(){
        di.lisaaSanapari("di", "du");
        di.lisaaSanapari("es", "cs");
        assertEquals(false, di.onkoJoListalla("fdsadfdfas"));
    }
}
