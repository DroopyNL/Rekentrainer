import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class OefenFrame extends JFrame {
    public OefenFrame(String naam, int groep, int aantal, int random) {
        setSize(440, 310);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new OefenPaneel(this, naam, groep, aantal, random));
        setTitle("Rekentrainer - Groep: " + groep);
        setVisible(true);
    }
}

class OefenPaneel extends JPanel implements ActionListener {
    private JFrame oefenFrame;
    private JLabel welkomLabel, opgaveLabel, aantalGoedLabel, aantalFoutLabel, aantalTeGaanLabel;
    private JButton volgendeKnop;
    private JTextField invulVak;
    private JPanel statusGroep;
    private String naam;
    private int input, groep, aantal, random, getalA, getalB, antwoordGetal, aantalGoed, aantalFout, aantalTeGaan;
    private OefeningGenerator oefeningGenerator;

    public OefenPaneel(JFrame oefenFrame, String naam, int groep, int aantal, int random) {
        this.oefenFrame = oefenFrame;
        this.naam = naam;
        this.groep = groep;
        this.aantal = aantal;
        this.random = random;
        setLayout(null);

        aantalGoed = 0;
        aantalFout = 0;
        aantalTeGaan = aantal;

        oefeningGenerator = new OefeningGenerator(groep, aantal, random);

        welkomLabel = new JLabel("Welkom " + naam + ", vul het antwoord van de volgende som in?");
        welkomLabel.setFont(new Font("Arial", Font.BOLD, 13));
        welkomLabel.setBounds(40, 20, 450, 20);
        add(welkomLabel);

        opgaveLabel = new JLabel(oefeningGenerator.getGetalA() + "  " + oefeningGenerator.getOperator() + "  " + oefeningGenerator.getGetalB() + "   = ");
        opgaveLabel.setFont(new Font("Arial", Font.BOLD, 48));
        opgaveLabel.setBounds(80, 80, 260, 45);
        add(opgaveLabel);

        invulVak = new JTextField(2);
        invulVak.setBounds(320, 80, 65, 45);
        add(invulVak);

        volgendeKnop = new JButton("Volgende som");
        volgendeKnop.addActionListener(this);
        volgendeKnop.setBounds(100, 150, 270, 30);
        add(volgendeKnop);

        statusGroep = new JPanel();
        statusGroep.setBounds(5, 200, 430, 85);
        statusGroep.setLayout(new GridLayout(3, 1));
        statusGroep.setBorder(new EmptyBorder(5, 5, 5, 5));
        statusGroep.setBackground(Color.LIGHT_GRAY);
        add(statusGroep);

        aantalGoedLabel = new JLabel("Aantal sommen tot nu toe goed: " + aantalGoed);
        statusGroep.add(aantalGoedLabel);
        aantalFoutLabel = new JLabel("Aantal sommen tot nu toe fout: " + aantalFout);
        statusGroep.add(aantalFoutLabel);
        aantalTeGaanLabel = new JLabel("Nog " + aantalTeGaan + " sommen te maken");
        statusGroep.add(aantalTeGaanLabel);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volgendeKnop) {
                try {
                    input = Integer.parseInt(invulVak.getText());
                } catch(NumberFormatException nfe) {
                    nfe.printStackTrace();
                    System.out.println("NumberFormatException in invulVak");
                }

                if(input == oefeningGenerator.getAntwoordGetal())
                    aantalGoed++;
                else
                    aantalFout++;

                aantalTeGaan--;

                if(aantalTeGaan == 1)
                    volgendeKnop.setText("Naar resultaat");
                if(aantalTeGaan != 0)
                    refresh();
                if(aantalTeGaan == 0)
                    showResult();
            }
    }

    public void refresh() {
        oefeningGenerator.genereerOpgave();
        opgaveLabel.setText(oefeningGenerator.getGetalA() + "  " + oefeningGenerator.getOperator() + "  " + oefeningGenerator.getGetalB() + "   = ");
        invulVak.setText("");
        aantalGoedLabel.setText("Aantal sommen tot nu toe goed: " + aantalGoed);
        aantalFoutLabel.setText("Aantal sommen tot nu toe fout: " + aantalFout);
        aantalTeGaanLabel.setText("Nog " + aantalTeGaan + " sommen te maken");
        revalidate();
    }

    public void showResult() {
        oefenFrame.dispose();
        new ResultaatFrame(naam, groep, aantal, random, aantalGoed, aantalFout);
    }
}