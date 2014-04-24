import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultaatFrame extends JFrame {
    public ResultaatFrame(String naam, int groep, int aantal, int random, int aantalGoed, int aantalFout) {
        setSize(440, 310);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new ResultaatPaneel(this, naam, groep, aantal, random, aantalGoed, aantalFout));
        setTitle("Rekentrainer - Resultaat " + naam);
        setVisible(true);
    }
}

class ResultaatPaneel extends JPanel implements ActionListener {
    private JFrame resultaatFrame;
    private JButton opnieuwKnop, stopKnop;
    private JLabel afgerondPart1, afgerondPart2, aantalGoedLabel, resultaatGoed, aantalFoutLabel, resultaatFout, scoreLabel, resultaatScore;
    private String naam;
    private int groep, aantal, random, aantalGoed, aantalFout;

    public ResultaatPaneel(JFrame resultaatFrame, String naam, int groep, int aantal, int random, int aantalGoed, int aantalFout) {
        setLayout(null);
        this.naam = naam;
        this.groep = groep;
        this.aantal = aantal;
        this.random = random;
        this.aantalGoed = aantalGoed;
        this.aantalFout = aantalFout;
        this.resultaatFrame = resultaatFrame;

        afgerondPart1 = new JLabel("Je hebt de opdracht afgerond " + naam + ",");
        afgerondPart1.setBounds(60, 15, 300, 24);
        add(afgerondPart1);
        afgerondPart2 = new JLabel("hieronder zie je wat je resultaat is:");
        afgerondPart2.setBounds(80, 35, 300, 24);
        add(afgerondPart2);

        JPanel resultaatBox = new JPanel();
        resultaatBox.setBounds(10, 80, 420, 120);
        resultaatBox.setLayout(null);
        add(resultaatBox);

        aantalGoedLabel = new JLabel("Aantal sommen goed:");
        aantalGoedLabel.setBounds(15, 15, 200, 24);
        resultaatGoed = new JLabel(aantalGoed + "");
        resultaatGoed.setBounds(215, 15, 50, 24);
        resultaatBox.add(aantalGoedLabel);
        resultaatBox.add(resultaatGoed);


        aantalFoutLabel = new JLabel("Aantal sommen fout:");
        aantalFoutLabel.setBounds(15, 40, 200, 24);
        resultaatFout = new JLabel(aantalFout + "");
        resultaatFout.setBounds(215, 40, 50, 24);
        resultaatBox.add(aantalFoutLabel);
        resultaatBox.add(resultaatFout);

        scoreLabel = new JLabel("Score:");
        scoreLabel.setBounds(15, 65, 200, 24);
        resultaatScore = new JLabel((int) (((aantal - aantalFout) / (double) aantal) * 100) + "%");
        resultaatScore.setBounds(215, 65, 50, 24);
        resultaatBox.add(scoreLabel);
        resultaatBox.add(resultaatScore);

        opnieuwKnop = new JButton("Nog een keer");
        opnieuwKnop.setBounds(15, 215, 80, 30);
        opnieuwKnop.addActionListener(this);
        add(opnieuwKnop);
        stopKnop = new JButton("Stoppen");
        stopKnop.setBounds(resultaatFrame.getWidth() - (15 + 80), 215, 80, 30);
        stopKnop.addActionListener(this);
        add(stopKnop);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == opnieuwKnop) {
            new OefenFrame(naam, groep, aantal, random);
            resultaatFrame.dispose();
        }

        if(e.getSource() == stopKnop) {
            resultaatFrame.dispose();
        }
    }
}
