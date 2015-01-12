import java.io.*;
import java.util.*;
/*
    Reads excercises from opgaven.txt and stores numbers in their corresponding variable.
 */
public class ExcerciseGenerator {
    private int groep, random, huidigeOpgave, getalA, getalB, antwoordGetal;
    private String operator;
    private ArrayList<String[]> opgavenLijst;

    /**
     * Takes a few parameters to get the corresponding excercises
     * @param groep     required for the corresponding group txt file
     * @param aantal
     * @param random
     */
    public ExcerciseGenerator(int groep, int aantal, int random) {
        this.groep = groep;
        this.random = random;
        huidigeOpgave = 0;
        operator = " ";

        opgavenLijst = new ArrayList<String[]>();

        try {
            BufferedReader lezer = new BufferedReader(new FileReader("Resources/opgaven" + groep + ".txt"));
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
        if(random == 1) {
            huidigeOpgave = (int) (Math.random() * opgavenLijst.size());

            getalA = Integer.parseInt(opgavenLijst.get(huidigeOpgave)[0]);
            operator = opgavenLijst.get(huidigeOpgave)[1];
            getalB = Integer.parseInt(opgavenLijst.get(huidigeOpgave)[2]);
            antwoordGetal = Integer.parseInt(opgavenLijst.get(huidigeOpgave)[4]);
        }
        else if(random == 0) {
            getalA = Integer.parseInt(opgavenLijst.get(huidigeOpgave)[0]);
            operator = opgavenLijst.get(huidigeOpgave)[1];
            getalB = Integer.parseInt(opgavenLijst.get(huidigeOpgave)[2]);
            antwoordGetal = Integer.parseInt(opgavenLijst.get(huidigeOpgave)[4]);

            huidigeOpgave++;
        }
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

    public static int getFileLength(int groep) throws Exception {
        InputStream is = new BufferedInputStream(new FileInputStream("Resources/opgaven" + groep + ".txt"));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }
}
