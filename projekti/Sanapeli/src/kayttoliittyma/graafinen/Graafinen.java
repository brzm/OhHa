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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    private String nimi;

    public Graafinen() throws IOException {
        super(new GridLayout(1, 1));
        kayttoliittyma = new Kayttoliittyma();
        logiikka = new Sovelluslogiikka(null);
        tiedostot = new Tiedostot(logiikka, null);
    }

    public void run() {
        try {
            nimenKyselyPopup();
            kayttoliittyma.tiedostojenHaku();
//            kayttoliittyma.kaynnista();

            frame = new JFrame("Sanapeli");
            frame.setPreferredSize(new Dimension(650, 400));

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

        final JButton pelaa = new JButton("Pelaa");
        peli.add(pelaa);
        paneeli.addTab("Peli", null, peli, "Pelaa peliä poeka");

        pelaa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pelaa.add(luoPeliPaneeli(), BorderLayout.NORTH);
                } catch (Exception a) {
                    System.out.println(a);
                }
            }
        });






        JComponent sanat = vaihdaPaneelinTeksti("Poista, lisää tai katsele sanoja");

        JTabbedPane vaihtoehdotSanoille = new JTabbedPane();
        JComponent poista = luoPoistaSana();
        JComponent lisaa = luoLisaaSana();
        JComponent sanalista = luoSanalista();

        vaihtoehdotSanoille.addTab("Poista", poista);
        vaihtoehdotSanoille.addTab("Lisää", lisaa);
        vaihtoehdotSanoille.addTab("Sanalista", sanalista);
        sanat.add(vaihtoehdotSanoille);
        paneeli.addTab("Sanat", null, sanat, "Tarkastele, poista, lisää sanoja");



        JComponent tulokset = vaihdaPaneelinTeksti("Katsele nykyisiä tai vanhoja vanhoja");
        JTabbedPane vaihtoehdotTuloksille = new JTabbedPane();

        JComponent vanhatTulokset = new JPanel();
        String vanhatTuloksetdas = kayttoliittyma.vanhatTulokset(tiedostot.getVanhatTulokset());


        JTextPane tekstiPaneeli = new JTextPane();
        JScrollPane paneScrollPane = new JScrollPane(tekstiPaneeli);
        paneScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        paneScrollPane.setPreferredSize(new Dimension(250, 155));
        paneScrollPane.setMinimumSize(new Dimension(10, 10));

        vanhatTulokset.add(tekstiPaneeli);


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

    private JPanel luoPeliPaneeli() {
        JPanel paneeli = new JPanel(new GridLayout(2, 1));
        paneeli.add(new JLabel("suomi"));
        paneeli.add(new JTextField("kirjoita tänne"));
        return paneeli;
    }

    private JPanel luoLisaaSana() {
        JPanel paneeli = new JPanel(new GridLayout(3, 2));

        JLabel suomi = new JLabel("Suomeksi ");
        JTextField suomiTeksti = new JTextField("");
        JLabel enkku = new JLabel("Englanniksi ");
        JTextField enkkuteksti = new JTextField();

        paneeli.add(new JLabel("Suomeksi "));
        paneeli.add(new JTextField(""));
        paneeli.add(new JLabel("englanniksi"));
        paneeli.add(new JTextField());
        paneeli.add(new JButton("Lisää sana"));
        paneeli.add(new JLabel(""));
        return paneeli;
    }

    private JPanel luoPoistaSana() {
        JPanel paneeli = new JPanel(new GridLayout(2, 2));
        paneeli.add(new JLabel("Anna suomeksi sana"));
        paneeli.add(new JTextField());
        paneeli.add(new JButton("Poista"));
        paneeli.add(new JLabel());
        return paneeli;
    }

    private JPanel luoSanalista() {
        JPanel paneeli = new JPanel(new GridLayout(1, 2));
        paneeli.add(new JButton("Tulosta sanat"));
        paneeli.add(new JLabel("prkleesti sanoja"));
        return paneeli;
    }

    private void nimenKyselyPopup() {
        final JFrame parent = new JFrame();

        parent.pack();
        parent.setVisible(true);

        nimi = JOptionPane.showInputDialog(parent, "Anna nimi", null);
        
        String teksti = "asd";
        int di = teksti.charAt(0)+teksti.charAt(1);
        System.out.println(di);

    }
}

