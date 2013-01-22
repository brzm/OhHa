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
    public void lisaaSanapariSanat(Sanat sanat) {
        sanalista.put(sanat.getSuomi(), sanat.getEnglanti());
    }

    public void tulostaKaikki() {
        for (String di : sanalista.keySet()) {
            System.out.println(di + " : " + sanalista.get(di));
        }
    }

    public int kuinkaMontaListassa() {
        int i = 0;
        for (String di : sanalista.keySet()) {
            i++;
        }
        System.out.println("PERKELEESTI SANOJA ELI " + i);
        return i;
    }

    public String suomeksiSana(int i) {
        String suomi = "";
        int j = 0;
        while (j < i) {
            for (String di : sanalista.keySet()) {
                suomi = "";
                suomi = di + "";
                j++;
                break;
            }
        }

        return suomi;
    }

    public String englanniksiSana(String i) {

        return sanalista.get(i);
    }
}
