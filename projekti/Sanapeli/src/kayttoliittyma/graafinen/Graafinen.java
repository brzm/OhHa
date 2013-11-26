/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.graafinen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Graafinen implements Runnable {

    private JFrame frame;
    private Kayttoliittyma kayttoliittyma;
    private JLabel jlabel;
    private Sovelluslogiikka logiikka;

    public Graafinen() throws IOException {
        kayttoliittyma = new Kayttoliittyma();
    }

    @Override
    public void run() {
        try {

            jlabel = new JLabel("PRKL");
            kayttoliittyma.tiedostojenHaku();
//            kayttoliittyma.kaynnista();

            frame = new JFrame("Sanapeli");
            frame.setPreferredSize(new Dimension(1000, 600));

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            luoKomponentit(frame.getContentPane());

            frame.pack();
            frame.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Graafinen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private JPanel valikkoPaneeli(JButton lisaa, JButton pelaa, JButton tulokset, JButton vanhatTulokset, JButton sanat) {
        JPanel paneeli = new JPanel(new GridLayout(1, 5));
        paneeli.add(lisaa);
        paneeli.add(pelaa);
        paneeli.add(tulokset);
        paneeli.add(vanhatTulokset);
        paneeli.add(sanat);
        return paneeli;

    }

    private void luoKomponentit(final Container container) throws IOException {
        JButton lisaa = new JButton("LISÄÄ");
        JButton pelaa = new JButton("PELI");
        JButton tulokset = new JButton("TULOKSET");
        JButton vanhatTulokset = new JButton("VANHAT TULOKSET");
        JButton sanat = new JButton("SANAT");

        container.add(valikkoPaneeli(lisaa, pelaa, tulokset, vanhatTulokset, sanat), BorderLayout.NORTH);




        pelaa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JPanel peli = new JPanel();
                    peli.setLayout(new BoxLayout(peli, BoxLayout.Y_AXIS));

                    JLabel suomeksi = new JLabel("suomeksi");
                    peli.add(suomeksi);
                    JTextField vastaus = new JTextField("");
                    peli.add(vastaus);

                    container.add(peli);
                } catch (Exception ex) {
                    System.out.println(ex);
                }


            }
        });

        sanat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jlabel = kayttoliittyma.tulostaSanat(logiikka.annaSanalista());

                } catch (Exception xx) {
                    xx.getCause();
                }

            }
        });

    }
}
