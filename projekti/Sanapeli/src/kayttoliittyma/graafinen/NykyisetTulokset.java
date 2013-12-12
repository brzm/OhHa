/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.graafinen;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author BRZM
 */
public class NykyisetTulokset {

    public JPanel luoNykyisetTulokset() {
        JPanel paneeli = new JPanel(new GridLayout(2,1));
        JButton nappi = new JButton("Tulosta");
        JTextArea textArea = new JTextArea("asdsadsdaadsda");
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        
        paneeli.add(nappi);
        paneeli.add(scrollPane);
        return paneeli;
    }
}
