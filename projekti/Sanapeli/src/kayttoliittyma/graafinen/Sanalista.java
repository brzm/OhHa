/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.graafinen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author BRZM
 */
public class Sanalista {

    private kayttoliittyma.Kayttoliittyma kayttoliittyma;
    private JScrollPane scrollPane;

    public Sanalista(kayttoliittyma.Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
    }

    public JPanel luoSanalista() throws IOException {
        JPanel paneeli = new JPanel();
        JButton nappi = new JButton("Tulosta sanat");
        nappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    scrollPane.setVisible(true);
                } catch (Exception zx) {
                    System.out.println(zx);
                }
            }
        });


        paneeli.add(nappi, BorderLayout.NORTH);

        JTextArea textArea = kayttoliittyma.getSanat();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVisible(false);

        paneeli.add(scrollPane, BorderLayout.SOUTH);
        return paneeli;
    }
}
