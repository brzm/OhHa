/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikkaTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sovelluslogiikka.Tulokset;

/**
 *
 * @author BRZM
 */
public class TuloksetTest {

    
    private Tulokset tulokset;

    public TuloksetTest() {
        tulokset = new Tulokset();

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
    public void yhteensaOikein() {

        tulokset.yhteensaTest(1);        

        assertEquals("Oikein   " + 1, tulokset.getOikein());
    }
}
