/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KayttoliittymaTest;

import java.io.IOException;
import kayttoliittyma.Kayttoliittyma;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author BRZM
 */
public class KayttoliittymaTest {

    private kayttoliittyma.Kayttoliittyma kayttoliittyma;

    public KayttoliittymaTest() throws IOException {
        kayttoliittyma = new kayttoliittyma.Kayttoliittyma();
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
    public void tyhjaSanaKaks1(){
        String eka = "";
        String toka= "";
        
        assertEquals(true, kayttoliittyma.tyhjaSanaKaks(eka, toka));
    }
    
    @Test
    public void tyhjaSanaKaks2(){
        String eka = "asd";
        String toka= "";
        
        assertEquals(true, kayttoliittyma.tyhjaSanaKaks(eka, toka));
    }
    
    @Test
    public void tyhjaSanaKaks3(){
        String eka = "asd";
        String toka= "asd";
        
        assertEquals(false, kayttoliittyma.tyhjaSanaKaks(eka, toka));
    }
    
    @Test
    public void tyhjaSanaKaks4(){
        String eka = "";
        String toka= "asd";
        
        assertEquals(true, kayttoliittyma.tyhjaSanaKaks(eka, toka));
    }
    
    @Test
    public void tyhjaSana(){
        String di= "";
        assertEquals(true, kayttoliittyma.tyhjaSana(di));
    }
    
    @Test
    public void tyhjaSana2(){
        String di= "dsasad";
        assertEquals(false, kayttoliittyma.tyhjaSana(di));
    }
}

