import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calctrainer extends JFrame {
    public static void main(String args[]) {
        JFrame frame = new calctrainer();
        frame.setSize(560, 190);
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
        setLayout(null);

        label = new JLabel("Vul hieronder je naam in om de rekentrainer te starten.");
        label.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        label.setBounds(80, 20, 400, 20);
        add(label);

        tekstvak = new JTextField(20);
        tekstvak.setBounds(180, 60, 195, 30);
        add(tekstvak);

        knop = new JButton("Start");
        knop.setBounds(200, 110, 160, 35);
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