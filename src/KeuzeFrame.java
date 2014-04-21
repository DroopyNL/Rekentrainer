import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class KeuzeFrame extends JFrame {
    public KeuzeFrame(String name) {
        setSize(440, 265);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new KeuzePaneel(name, this));
        setTitle("Rekentrainer - keuze scherm");
        setVisible(true);
    }
}

class KeuzePaneel extends JPanel implements ActionListener {
    private JFrame keuzeFrame;

    private JLabel groepLabel, somLabel, ranLabel;
    private JRadioButton groep3, groep4, groep5, groep6, groep7, groep8, ranJa, ranNee;
    private JTextField somField;
    private JButton startKnop;
    private int groep, aantal, random, mislukt;
    private String naam;

    public KeuzePaneel(String naam, KeuzeFrame keuzeFrame) {
        setLayout(null);
        setBorder(new EmptyBorder(30, 20, 30, 20));

        this.keuzeFrame = keuzeFrame;
        this.naam = naam;

        // Groep label en knoppen
        groepLabel = new JLabel("Kies je groep:");

        groep3 = new JRadioButton("Groep 3");
        groep4 = new JRadioButton("Groep 4");
        groep5 = new JRadioButton("Groep 5");
        groep6 = new JRadioButton("Groep 6");
        groep7 = new JRadioButton("Groep 7");
        groep8 = new JRadioButton("Groep 8");

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

        startKnop = new JButton("Start rekentrainer");
        startKnop.addActionListener(this);
        oostGroep.add(startKnop);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startKnop) {
            // Controlleer voor welke groep de sommen bestemd moeten zijn
            if(groep3.isSelected()) { groep = 3; }
            if(groep4.isSelected()) { groep = 4; }
            if(groep5.isSelected()) { groep = 5; }
            if(groep6.isSelected()) { groep = 6; }
            if(groep7.isSelected()) { groep = 7; }
            if(groep8.isSelected()) { groep = 8; }

            try {
                aantal = Integer.parseInt(somField.getText());
            }
            catch(NumberFormatException nfe) {
                nfe.printStackTrace();
                mislukt = 1;
            }

            // Controlleer of willekeurige sommen gegenereerd moeten worden
            if(ranJa.isSelected()) { random = 1; }
            if(ranNee.isSelected()) { random = 0; }

            if(mislukt == 0) {
                new OefenFrame(naam, groep, aantal, random);
                keuzeFrame.dispose();
            }
        }
    }
}