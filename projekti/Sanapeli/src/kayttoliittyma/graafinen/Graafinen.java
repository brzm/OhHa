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
import java.awt.event.WindowEvent;
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
public class Graafinen extends JPanel {

    private JFrame frame;
    private Kayttoliittyma kayttoliittyma;
    FlowLayout flowLayoutti = new FlowLayout();
    private String nimi;
    private LisaaSana lisaaSana;
    private PoistaSana poistaSana;
    private PelaaPeli pelaaPeli;
    private Sanalista sanalista;
    private NykyisetTulokset nykyisetTulokset;
    private VanhatTulokset vanhatTulokset;

    public Graafinen() throws IOException {
        super(new GridLayout(1, 1));
        kayttoliittyma = new Kayttoliittyma();
        lisaaSana = new LisaaSana(kayttoliittyma);
        poistaSana = new PoistaSana(kayttoliittyma);
        pelaaPeli = new PelaaPeli(kayttoliittyma);
        sanalista = new Sanalista(kayttoliittyma);
        nykyisetTulokset = new NykyisetTulokset();
        vanhatTulokset=new VanhatTulokset(kayttoliittyma);
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

        JComponent peliPaneeli = pelaaPeli.luoPeliPaneeli();
        paneeli.addTab("Peli", null, peliPaneeli, "Pelaa peliä poeka");

        JComponent sanat = vaihdaPaneelinTeksti("Poista, lisää tai katsele sanoja");

        JTabbedPane vaihtoehdotSanoilleTab = new JTabbedPane();
        JComponent poistaPaneeli = poistaSana.luoPoistaSana();
        JComponent lisaaPaneeli = lisaaSana.luoLisaaSana();
        JComponent sanalistaPaneeli = sanalista.luoSanalista();

        vaihtoehdotSanoilleTab.addTab("Poista", poistaPaneeli);
        vaihtoehdotSanoilleTab.addTab("Lisää", lisaaPaneeli);
        vaihtoehdotSanoilleTab.addTab("Sanalista", sanalistaPaneeli);

        sanat.add(vaihtoehdotSanoilleTab);
        paneeli.addTab("Sanat", null, sanat, "Tarkastele, poista, lisää sanoja");

        JComponent tulokset = vaihdaPaneelinTeksti("Katsele nykyisiä tai vanhoja vanhoja");
        JTabbedPane vaihtoehdotTuloksilleTab = new JTabbedPane();        
        JComponent vanhatTuloksetPaneeli = vanhatTulokset.luoVanhatTulokset();
        JComponent nykyisetTuloksetPaneeli = nykyisetTulokset.luoNykyisetTulokset();


        vaihtoehdotTuloksilleTab.addTab("Vanhat tulokset", vanhatTuloksetPaneeli);
        vaihtoehdotTuloksilleTab.addTab("Tämänhetkiset tulokset", nykyisetTuloksetPaneeli);
        tulokset.add(vaihtoehdotTuloksilleTab);
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

    private void nimenKyselyPopup() {
        final JFrame parent = new JFrame();

        nimi = JOptionPane.showInputDialog(parent, "Anna nimi", null);
        System.out.println(nimi);
    }

    public void windowClosing(WindowEvent e) {

        ActionListener task = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    kayttoliittyma.kaynnista("lopeta");
                } catch (Exception xc) {
                    System.out.println(xc);
                }
            }
        };

    }

    public void lisaysPopup(String fin, String eng) throws IOException {
        if (kayttoliittyma.tyhjaSanaKaks(fin, eng)) {
            JOptionPane.showMessageDialog(frame, "Kirjoita kumpaankin kenttään jotakin!");
        } else {
            int n = JOptionPane.showConfirmDialog(frame, "Lisätäänkö sana " + fin + " = " + eng + "?", "Sanan lisäys", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                if (kayttoliittyma.onkoListallaSananLisays(fin)) {
                    JOptionPane.showMessageDialog(frame, "Sana on jo listalla!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Sana lisättiin listalle!");
                    kayttoliittyma.lisaaSanat(fin, eng);
                    kayttoliittyma.kaynnista("tulosta");
                }

            }

        }
    }

    public void poistoPopup(String fin) throws IOException {
        if (kayttoliittyma.tyhjaSana(fin)) {
            JOptionPane.showMessageDialog(frame, "Kirjoita jotakin, tyhjää sanaa ei voi poistaa!");
        } else {
            int n = JOptionPane.showConfirmDialog(frame, "Poistetaanko sana " + fin + "?", "Sanan poisto", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                kayttoliittyma.poistaSana(fin);
                kayttoliittyma.kaynnista("tulosta");
            }
        }
    }

    public void peliPopup(String fin) {
        if (fin.equals(kayttoliittyma.englantiSanaPeli(fin))) {
            String asd = kayttoliittyma.englantiSanaPeli(fin);
            System.out.println(asd);
            JOptionPane.showMessageDialog(frame, "Oikein!");

        } else {
            JOptionPane.showMessageDialog(frame, "Väärin, " + fin + " on englanniksi " + kayttoliittyma.englantiSanaPeli(fin));
            System.out.println("suomisana" + fin);
            String asd = kayttoliittyma.englantiSanaPeli(fin);
            System.out.println("eng" + asd);
        }
    }
}
