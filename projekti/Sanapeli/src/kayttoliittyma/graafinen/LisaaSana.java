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
import kayttoliittyma.Kayttoliittyma;

/**
 *
 * @author BRZM
 */
public class LisaaSana extends JPanel  {

    private JTextField suomiTeksti;
    private JTextField enkkuteksti;   
    private kayttoliittyma.Kayttoliittyma kayttoliittyma;

    public LisaaSana(Kayttoliittyma liittyma) throws IOException {
        kayttoliittyma = liittyma;
    }


    public JPanel luoLisaaSana() {
        JPanel paneeli = new JPanel(new GridLayout(3, 2));

        suomiTeksti = new JTextField();
        enkkuteksti = new JTextField();

        JButton lisaaNappi = new JButton("Lisää sana");

        lisaaNappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   
                    kayttoliittyma.lisaaSanat(suomiTeksti.getText(), enkkuteksti.getText());
                   
                    kayttoliittyma.kaynnista("tulosta");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        paneeli.add(new JLabel("Suomeksi "));
        paneeli.add(suomiTeksti);
        paneeli.add(new JLabel("englanniksi"));
        paneeli.add(enkkuteksti);
        paneeli.add(lisaaNappi);
        paneeli.add(new JLabel(""));


        return paneeli;
    }
}
