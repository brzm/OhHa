/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.graafinen;

import java.awt.BorderLayout;
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

    public Sanalista(kayttoliittyma.Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
    }

    public JPanel luoSanalista() throws IOException {
        JPanel paneeli = new JPanel();
        JButton nappi = new JButton("Tulosta sanat");
        
        JTextArea textArea = kayttoliittyma.getSanat();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        paneeli.add(nappi, BorderLayout.NORTH);
        paneeli.add(textArea, BorderLayout.CENTER);


        paneeli.add(scrollPane);
        return paneeli;
    }
}
