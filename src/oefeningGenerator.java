import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/*
    Reads excercises from opgaven.txt and stores numbers in their corresponding variable.
 */
public class OefeningGenerator {
    private int groep, random, huidigeOpgave, getalA, getalB, antwoordGetal;
    private String operator;
    private ArrayList<String[]> opgavenLijst;

    public OefeningGenerator(int groep, int aantal) {
        this.groep = groep;
        huidigeOpgave = 0;
        operator = " ";

        opgavenLijst = new ArrayList<String[]>();

        try {
            BufferedReader lezer = new BufferedReader(new FileReader("opgaven" + groep + ".txt"));
            for(int i = 0; i < aantal; i++) {
                String regel = lezer.readLine();
                String[] opgave = regel.split(" ");
                opgavenLijst.add(opgave);
            }
        }
        catch(IOException ioe) { ioe.printStackTrace(); }

        genereerOpgave();
    }

    public void genereerOpgave() {
        getalA = Integer.parseInt(opgavenLijst.get(huidigeOpgave)[0]);
        getalB = Integer.parseInt(opgavenLijst.get(huidigeOpgave)[1]);
        antwoordGetal = Integer.parseInt(opgavenLijst.get(huidigeOpgave)[2]);
        operator = opgavenLijst.get(huidigeOpgave)[3];

        huidigeOpgave++;
    }

    public int getGetalA() {
        return getalA;
    }

    public int getGetalB() {
        return getalB;
    }

    public int getAntwoordGetal() {
        return antwoordGetal;
    }

    public String getOperator() { return operator; }
}