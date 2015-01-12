import sun.util.calendar.BaseCalendar;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by hktaylan on 06-10-14.
 */
public class ExcerciseSaver {

    private String name;
    private int group, random;
    private ArrayList<String> excerciseList;

    /**
     * Initialize an excercise saving class
     * @param name      Takes the name filled in at the name frame
     * @param group     Takes the group from the choice panel
     * @param random    Takes the random parameter from the choice panel
     */
    public ExcerciseSaver(String name, int group, int random) {
        this.name = name;
        this.group = group;
        this.random = random;

        excerciseList = new ArrayList<String>();
    }

    public void saveQuestion(String question) {
        excerciseList.add(question);
    }

    public void exportExcercise() {
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(String.format(name + "-" + group + "-"))));
            for(int i = 0; i < excerciseList.size(); i++) {
                writer.println(excerciseList.get(i));
            }
            writer.close();
            // TO-DO: write the file with a loop through all saved questions
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
