import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class KeuzeFrame extends JFrame {
    public KeuzeFrame(String name) {
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new KeuzePaneel(name, this));
        setTitle("Rekentrainer - keuze scherm");
        setVisible(true);
    }
}

class KeuzePaneel extends JPanel implements ActionListener {
    private JFrame keuzeFrame;

    private JLabel welkomLabel, groepLabel, somLabel, ranLabel;
    private JRadioButton groep3, groep4, groep5, groep6, groep7, groep8, ranJa, ranNee;
    private JTextField somField;
    private JButton startKnop;
    private int groep, aantal, random;
    private String naam;

    public KeuzePaneel(String naam, KeuzeFrame keuzeFrame) {
        setLayout(new BorderLayout());

        this.keuzeFrame = keuzeFrame;
        this.naam = naam;

        // Welkom naam
        welkomLabel = new JLabel("Welkom " + naam);
        add(welkomLabel, BorderLayout.NORTH);


        // Groep label en knoppen
        groepLabel = new JLabel("Kies je groep:");

        groep3 = new JRadioButton("Groep 3");
        groep4 = new JRadioButton("Groep 4");
        groep5 = new JRadioButton("Groep 5");
        groep6 = new JRadioButton("Groep 6");
        groep7 = new JRadioButton("Groep 7");
        groep8 = new JRadioButton("Groep 8");

        JPanel radioGroep = new JPanel();
        radioGroep.setLayout(new GridLayout(7, 1));
        add(radioGroep, BorderLayout.WEST);

        radioGroep.add(groepLabel);
        radioGroep.add(groep3);
        radioGroep.add(groep4);
        radioGroep.add(groep5);
        radioGroep.add(groep6);
        radioGroep.add(groep7);
        radioGroep.add(groep8);

        // Groep aantal en random
        somLabel = new JLabel("Hoeveel sommen wil je maken?");
        somField = new JTextField(3);

        ranLabel = new JLabel("Wil je de sommen door elkaar heen?");
        ranJa = new JRadioButton("Ja");
        ranNee = new JRadioButton("Nee");

        startKnop = new JButton("Start Rekentrainer");
        startKnop.addActionListener(this);

        JPanel oostGroep = new JPanel();
        oostGroep.setLayout(new BorderLayout());
        add(oostGroep, BorderLayout.EAST);

        oostGroep.add(somLabel, BorderLayout.NORTH);
        oostGroep.add(somField, BorderLayout.NORTH);
        oostGroep.add(ranLabel, BorderLayout.CENTER);
        oostGroep.add(ranJa, BorderLayout.CENTER);
        oostGroep.add(ranNee, BorderLayout.CENTER);
        oostGroep.add(startKnop, BorderLayout.SOUTH);
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

            aantal = Integer.parseInt(somField.getText());

            // Controlleer of willekeurige sommen gegenereerd moeten worden
            if(ranJa.isSelected()) { random = 1; }
            if(ranNee.isSelected()) { random = 0; }

            new OefenFrame(naam, groep, aantal, random);
            keuzeFrame.dispose();
        }
    }
}