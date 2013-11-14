/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.io.IOException;
import java.util.ArrayList;

/**listaa pelin aikana tulleet tulokset
 *
 * @author BRZM
 */
public class Tulokset {
    
    private ArrayList<String> vastaukset=new ArrayList<>();
    private int kuinkamontaoikein=0;
    private String kaikkiYht;
    private String kaikkiOik;
    private String kaikkiVaa;
    
  
    /**
     * listaa oikein olevian tulosten lkm
     * @param suomi
     * @throws IOException 
     */
    public void tuloksienListaaminenOikein(String suomi) throws IOException {
        vastaukset.add(suomi + " oikein");
        kuinkamontaoikein++;
    }

    /**
     * listaa väärin olevien tulosten lkm
     * @param suomi
     * @throws IOException 
     */
    public void tuloksienListaaminenVaarin(String suomi) throws IOException {
        vastaukset.add(suomi + " väärin");
    }

    private int kuinkaMontaVaarin() {
        return vastaukset.size() - kuinkamontaoikein;
    }
    
    public String getOikein() {
        return "Oikein " + kuinkamontaoikein;
    }

    public String getVaarin() {
        return "Väärin " + kuinkaMontaVaarin();
    }

    public String getYhteensa() {
        return "Yhteensä " + vastaukset.size();
    }
    
    public ArrayList<String> getVastaukset(){
        return vastaukset;
    }
    
    public void yhteensaVastauksia(int i){
        kaikkiYht= "Yhteensä "+ i+getYhteensa();        
    }
    
    public String yhteensaOikein(int i ){
        return "Oikein "+i+getOikein();
    }
    
    public String yhteensaVaarin(int i){
        return "Väärin "+ i+getVaarin();
    }
    
    public String palautaKaikkiYht(){
        return kaikkiYht;
    }
    
    public String palautaKaikkiOik(){
        return kaikkiOik;
    }
    
    public String palautaKaikkiVaa(){
        return kaikkiVaa;
    }
    
}
