/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanapeliTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sanapeli.Sanantutkiminen;
import sanapeli.Sanat;

/**
 *
 * @author BRZM
 */
public class SanantutkiminenTest {

    public SanantutkiminenTest() {
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
//     @Test
//     public void hello() {}
    @Test
    public void kuinkaMontaListassa(){
        
        Sanantutkiminen di = new Sanantutkiminen();
        di.lisaaSanapari("di", "du");
        di.lisaaSanapari("du", "di");
        
        assertEquals(2, di.kuinkaMontaListassa());
        
    }
    
    public void englanninPalautus(){
        Sanantutkiminen di = new Sanantutkiminen();
        
        di.lisaaSanapari("di", "du");
        
        assertEquals("du", di.englanniksiSana("di"));
    }
    
    public void lisaaSanat(){
        Sanat di = new Sanat("di", "du");
        Sanantutkiminen es = new Sanantutkiminen();
        
        es.lisaaSanapariSanat(di);
        
    }
    
    public void suomenPalautus(int i){
        Sanantutkiminen di = new Sanantutkiminen();
        Sanat eka = new Sanat("di", "du");
        Sanat toka = new Sanat("es","cs");
        Sanat kolmas = new Sanat("mäfä", "bäfä");
        di.lisaaSanapariSanat(eka);
        di.lisaaSanapariSanat(toka);
        di.lisaaSanapariSanat(kolmas);
        
        assertEquals("es", di.suomeksiSana(i));
    }
}
