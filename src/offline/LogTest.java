package offline;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LogTest {
    
    public static void main(String[] args) {
        ArrayList<String> file = read("logs/E_1-11-2022.txt");

        Main m = new Main();

        for (String s : file) {
            if (s.length() > 0)
                m.readLine(s);
            if (s.contains("turn"))
                m.printPlayers();
        }
    }
    
    public static ArrayList<String> read(String fileName) { // reads in file
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
