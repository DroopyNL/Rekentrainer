import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class ChoiceFrame extends JFrame {
    public ChoiceFrame(String name) {
        setSize(440, 265);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new ChoicePanel(name, this));
        setTitle("Rekentrainer - keuze scherm");
        setVisible(true);
    }
}

class ChoicePanel extends JPanel implements ActionListener {
    private JFrame keuzeFrame;
    private JLabel groepLabel, somLabel, ranLabel;
    private JRadioButton groep3, groep4, groep5, groep6, groep7, groep8, ranJa, ranNee;
    private JTextField somField;
    private JButton startButton;
    private int group, aantal, random, mislukt;
    private String name;

    public ChoicePanel(String name, ChoiceFrame choiceFrame) {
        setLayout(null);
        setBorder(new EmptyBorder(30, 20, 30, 20));

        this.keuzeFrame = choiceFrame;
        this.name = name;

        // Groep label en knoppen
        groepLabel = new JLabel("Kies je group:");

        groep3 = new JRadioButton("Groep 3");
        groep4 = new JRadioButton("Groep 4");
        groep5 = new JRadioButton("Groep 5");
        groep6 = new JRadioButton("Groep 6");
        groep7 = new JRadioButton("Groep 7");
        groep8 = new JRadioButton("Groep 8");

        ButtonGroup groepRadio = new ButtonGroup();
        groepRadio.add(groep3);
        groepRadio.add(groep4);
        groepRadio.add(groep5);
        groepRadio.add(groep6);
        groepRadio.add(groep7);
        groepRadio.add(groep8);

        JPanel westGroep = new JPanel();
        westGroep.setLayout(new GridLayout(7, 1));
        westGroep.setBounds(30, 30, 100, 180);
        add(westGroep, BorderLayout.WEST);

        westGroep.add(groepLabel);
        westGroep.add(groep3);
        westGroep.add(groep4);
        westGroep.add(groep5);
        westGroep.add(groep6);
        westGroep.add(groep7);
        westGroep.add(groep8);

        // Groep aantal en random
        JPanel oostGroep = new JPanel();
        oostGroep.setLayout(new GridLayout(5, 1));
        oostGroep.setBounds(150, 30, 270, 180);
        add(oostGroep, BorderLayout.EAST);

        // eerste paneel in de gridLayout oostGroep
        JPanel hoeveelGroep = new JPanel();
        hoeveelGroep.setLayout(null);
        oostGroep.add(hoeveelGroep);

        somLabel = new JLabel("Hoeveel sommen wil je maken?");
        somLabel.setBounds(0, 0, 200, 24);
        hoeveelGroep.add(somLabel);
        somField = new JTextField(2);
        somField.setBounds(210, 0, 55, 26);
        hoeveelGroep.add(somField);

        ranLabel = new JLabel("Wil je de sommen door elkaar heen?");
        oostGroep.add(ranLabel);
        ranJa = new JRadioButton("Ja");
        oostGroep.add(ranJa);
        ranNee = new JRadioButton("Nee");
        oostGroep.add(ranNee);
        ButtonGroup ranRadio = new ButtonGroup();
        ranRadio.add(ranJa);
        ranRadio.add(ranNee);

        startButton = new JButton("Start rekentrainer");
        startButton.addActionListener(this);
        oostGroep.add(startButton);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton) {
            // Controlleer voor welke group de sommen bestemd moeten zijn
            if(groep3.isSelected()) { group = 3; }
            if(groep4.isSelected()) { group = 4; }
            if(groep5.isSelected()) { group = 5; }
            if(groep6.isSelected()) { group = 6; }
            if(groep7.isSelected()) { group = 7; }
            if(groep8.isSelected()) { group = 8; }

            try {
                aantal = Integer.parseInt(somField.getText());

                if(ExcerciseGenerator.getFileLength(group) < aantal)
                    mislukt = 1;
                else
                    mislukt = 0;
            }
            catch(NumberFormatException nfe) {
                nfe.printStackTrace();
                JOptionPane.showMessageDialog(this, "U heeft geen getal ingevoerd.", "Geen getal", JOptionPane.ERROR_MESSAGE);
                mislukt = 1;
            }
            catch(Exception exc) {
                exc.printStackTrace();
                mislukt = 1;
            }

            // Controlleer of willekeurige sommen gegenereerd moeten worden
            if(ranJa.isSelected()) { random = 1; }
            if(ranNee.isSelected()) { random = 0; }

            if(mislukt == 0) {
                new QuestionFrame(name, group, aantal, random);
                keuzeFrame.dispose();
            }
        }
    }
}