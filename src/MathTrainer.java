import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MathTrainer extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new MathTrainer();
        frame.setSize(560, 190);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new NamePanel(frame));
        frame.setTitle("Excercise");
        frame.setVisible(true);
    }
}

/*
 * The JPanel which is used to get the user's name.
 */
class NamePanel extends JPanel implements ActionListener {
    private JFrame mathTrainer;

    private JButton button;
    private JLabel label;
    private JTextField textField;

    /*
     * @param calcTrainer takes the frame so it can dispose it later on
     */
    public NamePanel(JFrame mathTrainer) {
        setLayout(null);

        this.mathTrainer = mathTrainer;

        label = new JLabel("Vul hieronder je naam in om de rekentrainer te starten.");
        label.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        label.setBounds(80, 20, 400, 20);
        add(label);

        textField = new JTextField(20);
        textField.setBounds(180, 60, 195, 30);
        textField.addActionListener(this);
        add(textField);

        button = new JButton("Start");
        button.setBounds(200, 110, 160, 35);
        button.addActionListener(this);
        add(button);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button || e.getSource() == textField) {
            // initialise the ChoiceFrame, passing through the filled in name at nameField
            String textFromField = textField.getText();
            if(textFromField.equals(""))
                JOptionPane.showMessageDialog(this, "U heeft geen naam ingevuld.", "Geen naam", JOptionPane.ERROR_MESSAGE);
            else {
                new ChoiceFrame(textField.getText());
                mathTrainer.dispose();
            }
        }
    }
}