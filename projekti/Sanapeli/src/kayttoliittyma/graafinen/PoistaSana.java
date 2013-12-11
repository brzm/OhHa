/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.graafinen;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author BRZM
 */
public class PoistaSana extends JPanel {

    private kayttoliittyma.Kayttoliittyma kayttoliittyma;
    private JTextField sana;

    public PoistaSana(kayttoliittyma.Kayttoliittyma liittyma) {
        kayttoliittyma = liittyma;
    }

    public JPanel luoPoistaSana() {
        sana = new JTextField();
        JButton nappi = new JButton("Poista");
        nappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    kayttoliittyma.poistaSana(sana.getText());
                    kayttoliittyma.kaynnista("tulosta");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        JPanel paneeli = new JPanel(new GridLayout(2, 2));
        paneeli.add(new JLabel("Anna suomeksi sana"));
        paneeli.add(sana);
        paneeli.add(nappi);
        paneeli.add(new JLabel());
        return paneeli;
    }
}
