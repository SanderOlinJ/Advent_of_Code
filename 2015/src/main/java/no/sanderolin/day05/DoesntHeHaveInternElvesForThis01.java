package no.sanderolin.day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class DoesntHeHaveInternElvesForThis01 {

    public static void solve() {
        ArrayList<String> file = readFromFile();
        System.out.println(findNiceStrings(file));
    }

    private static int findNiceStrings(ArrayList<String> file) {
        int niceStrings = 0;
        for (String line : file) {
            if (compliesWithRule1(line) && compliesWithRule2(line) && compliesWithRule3(line)) niceStrings++;
        }
        return niceStrings;
    }

    private static boolean compliesWithRule1(String line) {
        Pattern pattern = Pattern.compile("[aeiou]");
        long count = pattern.matcher(line).results().count();
        return count >= 3;
    }

    private static boolean compliesWithRule2(String line) {
        Pattern pattern = Pattern.compile("(\\w)\\1+");
        long count = pattern.matcher(line).results().count();
        return count >= 1;
    }

    private static boolean compliesWithRule3(String line) {
        Pattern pattern = Pattern.compile("(?=(ab|cd|pq|xy))");
        long count = pattern.matcher(line).results().count();
        return count == 0;
    }

    private static ArrayList<String> readFromFile() {
        ArrayList<String> fileLines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/day05.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       return fileLines;
    }
}
