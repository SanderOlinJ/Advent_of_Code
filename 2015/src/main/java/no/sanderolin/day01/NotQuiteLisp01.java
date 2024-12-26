package no.sanderolin.day01;

import java.io.BufferedReader;
import java.io.FileReader;

public class NotQuiteLisp01 {

    public static void solve() {
        String line = readFromFile();
        int floor = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '(') floor++;
            else floor--;
        }
        System.out.println(floor);
    }

    private static String readFromFile() {
        String line = "";
        try {
            line = new BufferedReader(new FileReader("src/main/resources/day01.txt")).readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return line;
    }
}
