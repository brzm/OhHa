/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.graafinen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import kayttoliittyma.Kayttoliittyma;
import sovelluslogiikka.Sovelluslogiikka;
import sovelluslogiikka.Tiedostot;

/**
 * Graafinen käyttöliittymä, jossa ohjeet ja tekstikäyttöliittmän käynnistys
 *
 * @author brzm
 */
public class Graafinen extends JPanel {

    private JFrame frame;
    private Kayttoliittyma kayttoliittyma;
    private Sovelluslogiikka logiikka;
    private Tiedostot tiedostot;
    FlowLayout flowLayoutti = new FlowLayout();

    public Graafinen() throws IOException {
        super(new GridLayout(1, 1));
        kayttoliittyma = new Kayttoliittyma();
        logiikka = new Sovelluslogiikka(null);
        tiedostot = new Tiedostot(logiikka, null);
    }

    public void run() {
        try {

            kayttoliittyma.tiedostojenHaku();
//            kayttoliittyma.kaynnista();

            frame = new JFrame("Sanapeli");
            frame.setPreferredSize(new Dimension(650, 650));

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Graafinen(), BorderLayout.CENTER);

            luoKomponentit(frame.getContentPane());

            frame.pack();
            frame.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Graafinen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void luoKomponentit(Container container) throws IOException {

        JTabbedPane paneeli = new JTabbedPane();


        JComponent peli = new JPanel();
        peli.add(new JButton("Pelaa"));
        paneeli.addTab("Peli", null, peli, "Pelaa peliä poeka");

        JComponent sanat = vaihdaPaneelinTeksti("Poista, lisää tai katsele sanoja");

        JTabbedPane vaihtoehdotSanoille = new JTabbedPane();
        JComponent poista = new JPanel();
        JComponent lisaa = new JPanel();
        JComponent sanalista = new JPanel();

        vaihtoehdotSanoille.addTab("Poista", poista);
        vaihtoehdotSanoille.addTab("Lisää", lisaa);
        vaihtoehdotSanoille.addTab("Sanalista", sanalista);
        sanat.add(vaihtoehdotSanoille);
        paneeli.addTab("Sanat", null, sanat, "Tarkastele, poista, lisää sanoja");



        JComponent tulokset = vaihdaPaneelinTeksti("Katsele nykyisiä tai vanhoja vanhoja");
        JTabbedPane vaihtoehdotTuloksille = new JTabbedPane();
        
        JComponent vanhatTulokset = new JPanel();
        String vanhatTuloksetdas=kayttoliittyma.vanhatTulokset(tiedostot.getVanhatTulokset());
        vanhatTulokset.add(new JLabel(vanhatTuloksetdas));
        
        JComponent nykyisetTulokset = new JPanel();
        nykyisetTulokset.add(new JButton("Tulosta"));
        
        vaihtoehdotTuloksille.addTab("Vanhat tulokset", vanhatTulokset);
        vaihtoehdotTuloksille.addTab("Tämänhetkiset tulokset", nykyisetTulokset);
        tulokset.add(vaihtoehdotTuloksille);
        paneeli.addTab("Tulokset", null, tulokset, "Tarkastele nykyisiä tai vanhoja tuloksia");



        container.add(paneeli);

    }
    
    private JComponent vaihdaPaneelinTeksti(String teksti) {
        JPanel paneeli = new JPanel(false);
        JLabel uusiTeksti = new JLabel(teksti);
        uusiTeksti.setHorizontalAlignment(JLabel.CENTER);
        paneeli.setLayout(new GridLayout(1, 1));
        paneeli.add(uusiTeksti);
        return paneeli;
    }
}
