package sanapeli;

import java.util.HashMap;
import java.util.Map;

public class Sanantutkiminen {
    
    private Map<String, String> sanalista = new HashMap<>();
        
    public Sanantutkiminen() {
        
    }
    
    public void lisaaSanapari(String suomi, String englanti) {
        sanalista.put(suomi, englanti);
    }
    
    /**
     *
     * @param sanat
     */
    public void lisaaSanapariSanat(Sanat sanat){
        sanalista.put(sanat.getSuomi(), sanat.getEnglanti());
    }
    
    public void tulostaKaikki() {
        for (String di : sanalista.keySet()) {
            System.out.println(di + " : " + sanalista.get(di));
        }
    }
}
