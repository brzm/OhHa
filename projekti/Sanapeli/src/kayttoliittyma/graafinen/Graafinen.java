/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.graafinen;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import kayttoliittyma.Kayttoliittyma;
import sovelluslogiikka.Sovelluslogiikka;

/**
 * Graafinen käyttöliittymä, jossa ohjeet ja tekstikäyttöliittmän käynnistys
 *
 * @author brzm
 */
public class Graafinen extends JPanel {

    private JFrame frame;
    private Kayttoliittyma kayttoliittyma;
    private JLabel jlabel;
    private Sovelluslogiikka logiikka;
    FlowLayout flowLayoutti = new FlowLayout();

    public Graafinen() throws IOException {
        super(new GridLayout(1, 1));
        kayttoliittyma = new Kayttoliittyma();
    }

    public void run() {
        try {

            jlabel = new JLabel("PRKL");
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
        ImageIcon kuva = kuva("eikuvaaprkl");

        JComponent peli = vaihdaPaneelinTeksti("et tiedä mittää");
        paneeli.addTab("Peli", kuva, peli, "Pelaa peliä poeka");

        JComponent sanat = vaihdaPaneelinTeksti("pippeli");
        paneeli.addTab("Sanat",kuva, sanat,"Tarkastele, poista, lisää sanoja");
        
        JComponent tulokset = vaihdaPaneelinTeksti("kaikki oikein");
        paneeli.addTab("Tulokset",kuva, tulokset,"Tarkastele nykyisiä tai vanhoja tuloksia");



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

    protected static ImageIcon kuva(String path) {
        java.net.URL imgURL = Graafinen.class.getResource(path);
        if (imgURL != null) {
            return null;
        } else {
            return null;
        }
    }
}
