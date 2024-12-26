package no.sanderolin.day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class IWasToldThereWouldBeNoMath02 {

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
        int bow = length * width * height;
        int smallest = Math.min(length, Math.min(width, height));
        int secondSmallest = length + width + height - smallest - (Math.max(length, Math.max(width, height)));
        return 2*smallest + 2*secondSmallest + bow;
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
