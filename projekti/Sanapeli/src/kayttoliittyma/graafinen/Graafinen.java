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
        kayttoliittyma.tiedostojenHaku();
    }

    @Override
    public void run() {
        try {

            frame = new JFrame("Sanapeli");
            frame.setPreferredSize(new Dimension(1000, 200));

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
        JPanel panel = new JPanel(new GridLayout(1, 5));
        panel.add(lisaa);
        panel.add(pelaa);
        panel.add(tulokset);
        panel.add(vanhatTulokset);
        panel.add(sanat);
        return panel;

    }

    private void luoKomponentit(Container container) throws IOException {
        JButton lisaa = new JButton("LISÄÄ");
        JButton pelaa = new JButton("PELAA");
        JButton tulokset = new JButton("TULOKSET");
        JButton vanhatTulokset = new JButton("VANHAT TULOKSET");
        JButton sanat = new JButton("SANAT");

        container.add(valikkoPaneeli(lisaa, pelaa, tulokset, vanhatTulokset, sanat), BorderLayout.NORTH);
        container.add(new JLabel());

        
        JLabel teksti = new JLabel("");
        pelaa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Random arvonta = new Random();
                int monesko = arvonta.nextInt(logiikka.kuinkaMontaListassa());

                JLabel teksti = new JLabel("Suomeksi sana: " + logiikka.suomeksiSana(monesko) + ". Kirjoita englanniksi");
                
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        
        container.add(teksti);



    }
}
