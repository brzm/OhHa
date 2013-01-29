package SanapeliTest;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sanapeli.Sanat;

/**
 *
 * @author BRZM
 */
public class SanapeliTest {
    
    public SanapeliTest() {
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
    public void tulostaakoOikein(){
        Sanat sanat = new Sanat("suomi","enkku");
        
        assertEquals("Suomeksi suomi, englanniksi enkku", sanat.toString());
    }
    
    @Test
    public void getSuomi(){
        Sanat di = new Sanat("di", "du");
        assertEquals("di", di.getSuomi());
    }
    
    @Test
    public void getEnkku(){
        Sanat di = new Sanat("di", "es");
        assertEquals("es", di.getEnglanti());
    }
}
