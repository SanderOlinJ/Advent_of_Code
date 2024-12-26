package no.sanderolin.day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class IWasToldThereWouldBeNoMath01 {

    public static void solve() {
        ArrayList<String> file = readFromFile();
        long totalAmountOfWrappingPaper = 0;
        for (String line : file) {
            totalAmountOfWrappingPaper += calculateWrappingPaper(line);
        }
        System.out.println(totalAmountOfWrappingPaper);
    }

    private static int calculateWrappingPaper(String line) {
        String[] values = line.split("x");
        int length = Integer.parseInt(values[0]);
        int width = Integer.parseInt(values[1]);
        int height = Integer.parseInt(values[2]);
        int side1 = length * width;
        int side2 = width * height;
        int side3 = height * length;
        return 2 * side1 + 2 * side2 + 2 * side3 + Math.min(side1, Math.min(side2, side3));
    }

    private static ArrayList<String> readFromFile() {
        ArrayList<String> array = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day02.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                array.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
}
