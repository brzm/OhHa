/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.graafinen;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author BRZM
 */
public class PelaaPeli {

    private JLabel suomi;
    private kayttoliittyma.Kayttoliittyma kayttoliittyma;
    private int sananNumero;
    private JTextField englantiSana;
    private String suomiSana;
    private String vastaus;

    public PelaaPeli(kayttoliittyma.Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
        suomi = new JLabel();
    }

    public JPanel luoPeliPaneeli() throws IOException {

        JPanel paneeli = new JPanel(new GridLayout(4, 1));
        englantiSana = new JTextField("KIRJOITA TÄNNE PRKL");
        JButton pelaaNappi = new JButton("Pelaa");
        JButton tarkistaTulosNappi = new JButton("Tarkista");
        final Graafinen graafinen = new Graafinen();

        pelaaNappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sananNumero = kayttoliittyma.sananArvonta();
                    suomiSana = kayttoliittyma.suomiSanaPeli(sananNumero);
                    suomi = new JLabel("Suomeksi sana: " + suomiSana);
                    graafinen.peliSanaPopup(suomiSana);
                    System.out.println(suomiSana);
                    vastaus = kayttoliittyma.englantiSanaPeli(suomiSana);
                    System.out.println(vastaus);

                } catch (Exception xc) {
                    System.out.println(xc);
                }
            }
        });

        tarkistaTulosNappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    graafinen.peliPopup(suomiSana, englantiSana.getText(),vastaus);
                } catch (Exception xc) {
                    System.out.println(xc);
                }
            }
        });



        paneeli.add(pelaaNappi);
        paneeli.add(suomi);
        paneeli.add(englantiSana);
        paneeli.add(tarkistaTulosNappi);
        return paneeli;
    }
}
