import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calctrainer extends JFrame {
    public static void main(String args[]) {
        JFrame frame = new calctrainer();
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new NaamPaneel(frame));
        frame.setTitle("Excercise");
        frame.setVisible(true);
    }
}

/*
 * Het NaamPaneel waarin de gebruiker om een naam wordt gevraagd.
 */
class NaamPaneel extends JPanel implements ActionListener {
    private JFrame calcTrainer;

    private JButton knop;
    private JLabel label;
    private JTextField tekstvak;

    /*
     * @param calcTrainer neemt de frame in als object om het te kunnen beïndigen
     */
    public NaamPaneel(JFrame calcTrainer) {
        label = new JLabel("Voer uw naam in: ");
        add(label);

        tekstvak = new JTextField(10);
        add(tekstvak);

        knop = new JButton("Start");
        knop.addActionListener(this);
        add(knop);

        // Geef het calcTrainer door als object
        this.calcTrainer = calcTrainer;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == knop) {
            // initialise the choiceframe passing through the filled in name at nameField
            new KeuzeFrame(tekstvak.getText());
            calcTrainer.dispose();
        }
    }
}