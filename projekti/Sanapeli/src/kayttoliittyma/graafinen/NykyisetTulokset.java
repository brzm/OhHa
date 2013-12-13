/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.graafinen;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author BRZM
 */
public class NykyisetTulokset {

    private String nimi;
    private kayttoliittyma.Kayttoliittyma kayttoliittyma;
    private JTextArea textArea;

    public NykyisetTulokset(String nimi, kayttoliittyma.Kayttoliittyma liittyma) {
        this.nimi = nimi;
        kayttoliittyma = liittyma;
        
    }

    public JPanel luoNykyisetTulokset() {
        JPanel paneeli = new JPanel(new GridLayout(2, 1));
        JButton nappi = new JButton("Tulosta");
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);


        nappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea.append(nimi + "\n");
                    textArea.append(kayttoliittyma.getTuloksetYhteensa() + "\n");
                    textArea.append(kayttoliittyma.getTuloksetOikein() + "\n");
                    textArea.append(kayttoliittyma.getTuloksetVaarin() + "\n");
                    System.out.println(nimi);
                } catch (Exception xc) {
                    System.out.println(xc);
                }
            }
        });


        paneeli.add(nappi);
        paneeli.add(scrollPane);
        return paneeli;
    }
}
