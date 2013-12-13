/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma.graafinen;

import java.io.FileNotFoundException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author BRZM
 */
public class VanhatTulokset {
    
    private kayttoliittyma.Kayttoliittyma kayttoliittyma;
    
    public VanhatTulokset(kayttoliittyma.Kayttoliittyma liittyma){
        kayttoliittyma=liittyma;
    }
    
    public JPanel luoVanhatTulokset() throws FileNotFoundException{
        JPanel paneeli = new JPanel();
//        JTextArea textArea = kayttoliittyma.getVanhatTulokset();
//        textArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(textArea);
//        
//        paneeli.add(scrollPane);
        return paneeli;
    }
    
}
