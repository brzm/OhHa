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
    private Kayttoliittyma kayttoliittyma;
    private JLabel jlabel;

    public Graafinen() throws IOException {
        kayttoliittyma = new Kayttoliittyma();
    }

    @Override
    public void run() {
        try {
            kayttoliittyma.kaynnista();
            frame = new JFrame("Sanapeli");
            frame.setPreferredSize(new Dimension(600, 600));

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            luoKomponentit(frame.getContentPane());

            frame.pack();
            frame.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Graafinen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private JPanel valikkoPaneeli() {
        JPanel panel = new JPanel(new GridLayout(1, 5));
        panel.add(new JButton("LISÄÄ"));
        panel.add(new JButton("PELAA"));
        panel.add(new JButton("TULOKSET"));
        panel.add(new JButton("VANHAT TULOKSET"));
        panel.add(new JButton("SANAT"));
        return panel;

    }

    private void luoKomponentit(Container container) throws IOException {
        
        container.add(valikkoPaneeli(),BorderLayout.NORTH);
        container.add(new JLabel("asd"));
        container.add(new JTextField());
        
        
//        JButton ok = new JButton("OK");ok.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    frame.dispose();
//                    di.kaynnista();
//                } catch (IOException ex) {
//                    Logger.getLogger(Graafinen.class.getName()).log(Level.SEVERE, null, ex);
//                }}});container.add(ok);
    }
}
