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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
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
        nykyisetTulokset = new NykyisetTulokset(nimi, kayttoliittyma);
        vanhatTulokset = new VanhatTulokset(kayttoliittyma);
    }

    public void run() {
        try {
            
            kayttoliittyma.tiedostojenHaku();
//            kayttoliittyma.kaynnista();

            frame = new JFrame("Sanapeli");
            frame.setPreferredSize(new Dimension(650, 400));

            frame.add(new Graafinen(), BorderLayout.CENTER);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        kayttoliittyma.ohjelmanLopetus(nimi);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Graafinen.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Graafinen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.exit(0);
                }
            });

            luoKomponentit(frame.getContentPane());

            frame.pack();
            frame.setVisible(true);

        } catch (IOException ex) {
            Logger.getLogger(Graafinen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        nimenKyselyPopup();
    }

    private void luoKomponentit(Container container) throws IOException {
        
        

        JTabbedPane paneeli = new JTabbedPane();

        JComponent peliPaneeli = pelaaPeli.luoPeliPaneeli();
        paneeli.addTab("Peli", null, peliPaneeli, "Pelaa peliä poeka");

        JComponent sanatTab = vaihdaPaneelinTeksti("Poista, lisää tai katsele sanoja");

        JTabbedPane vaihtoehdotSanoilleTab = new JTabbedPane();
        JComponent poistaPaneeli = poistaSana.luoPoistaSana();
        JComponent lisaaPaneeli = lisaaSana.luoLisaaSana();
        JComponent sanalistaPaneeli = sanalista.luoSanalista();

        vaihtoehdotSanoilleTab.addTab("Poista", poistaPaneeli);
        vaihtoehdotSanoilleTab.addTab("Lisää", lisaaPaneeli);
        vaihtoehdotSanoilleTab.addTab("Sanalista", sanalistaPaneeli);

        sanatTab.add(vaihtoehdotSanoilleTab);
        paneeli.addTab("Sanat", null, sanatTab, "Tarkastele, poista, lisää sanoja");

        JComponent tuloksetTab = vaihdaPaneelinTeksti("Katsele nykyisiä tai vanhoja vanhoja");
        JTabbedPane vaihtoehdotTuloksilleTab = new JTabbedPane();
        JComponent vanhatTuloksetPaneeli = vanhatTulokset.luoVanhatTulokset();
        JComponent nykyisetTuloksetPaneeli = nykyisetTulokset.luoNykyisetTulokset();


        vaihtoehdotTuloksilleTab.addTab("Vanhat tulokset", vanhatTuloksetPaneeli);
        vaihtoehdotTuloksilleTab.addTab("Tämänhetkiset tulokset", nykyisetTuloksetPaneeli);
        tuloksetTab.add(vaihtoehdotTuloksilleTab);
        paneeli.addTab("Tulokset", null, tuloksetTab, "Tarkastele nykyisiä tai vanhoja tuloksia");



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

    public void peliPopup(String fin, String eng, String vastaus) throws IOException {
        if (eng.equals(vastaus)) {
            JOptionPane.showMessageDialog(frame, "Oikein!");
            kayttoliittyma.lisaaTuloksetOikein(fin);
        } else {
            JOptionPane.showMessageDialog(frame, "Väärin, " + fin + " on englanniksi " + vastaus);
            kayttoliittyma.lisaaTuloksetVaarin(fin);
        }
    }

    public void peliSanaPopup(String sana) {
        JOptionPane.showMessageDialog(frame, "Suomeksi: " + sana);
    }
}
