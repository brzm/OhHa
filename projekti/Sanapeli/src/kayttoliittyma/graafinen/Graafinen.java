/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.graafinen;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import kayttoliittyma.Kayttoliittyma;

/**
 * Graafinen käyttöliittymä, jossa ohjeet ja tekstikäyttöliittmän käynnistys
 *
 * @author brzm
 */
public class Graafinen implements Runnable {

    private JFrame frame;
    private JButton ok;

    @Override
    public void run() {
        try {

            frame = new JFrame("Sanapeli");
            frame.setPreferredSize(new Dimension(300, 300));

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            luoKomponentit(frame.getContentPane());

            frame.pack();
            frame.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Graafinen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void luoKomponentit(Container container) throws IOException {
        final Kayttoliittyma di = new Kayttoliittyma();
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        JLabel eka = new JLabel("OHJEET:");
        JLabel lisaa = new JLabel("'lisaa' - Lisää sanoja listaan");
        JLabel pelaa = new JLabel("'pelaa' - Pelaa sanapeliä");
        JLabel tuloksetNyt = new JLabel("'tulokset' - Näät tämänhetkiset omat tuloksesi");
        JLabel vanhatTulokset = new JLabel("'vanhat' - Näät kaikkien pelaajien vanhat tulokset");
        JLabel sanat = new JLabel("'tulosta' - Näät kaikki pelin sanat listattuna");

        container.add(eka);
        container.add(lisaa);
        container.add(pelaa);
        container.add(tuloksetNyt);
        container.add(vanhatTulokset);
        container.add(sanat);

        ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    frame.dispose();
                    di.kaynnista();
                } catch (IOException ex) {
                    Logger.getLogger(Graafinen.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        JButton idiootti = new JButton("ÄLÄ PAINA TÄSTÄ!");
        idiootti.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                int i = 0;
                while (true) {
                    System.out.println("lol painoit nappia senkin pahaperse");
                    if (i % 50 == 0) {
                        System.out.println("pippeli");
                    }
                    i++;
                }
            }
        });


        container.add(ok);

        container.add(idiootti);
    }
}
