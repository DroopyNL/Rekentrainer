import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OefenFrame extends JFrame {
    public OefenFrame(String naam, int groep, int aantal, int random) {
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new OefenPaneel(this, naam, groep, aantal, random));
        setTitle("Rekentrainer - Groep: " + groep);
        setVisible(true);
    }
}

class OefenPaneel extends JPanel {

    public OefenPaneel(JFrame oefenFrame, String naam, int groep, int aantal, int random) {
        JLabel label = new JLabel("Naam: " + naam + "; Groep: " + groep + "; Aantal: " + aantal + "; Random: " + random);
        add(label);
    }
}