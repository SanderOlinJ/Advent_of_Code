package no.sanderolin.day04;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CeresSearch01 {

    private static final List<String> list = new ArrayList<>();
    private static final Pattern pattern = Pattern.compile("(?=(XMAS|SAMX))");
    private static int matrixSize;

    public static void solve() {
        readHorizontalAndVertical();
        handleDiagonals();
        int totalInstances = findInstances();
        System.out.println(totalInstances);
    }

    private static int findInstances() {
        int numberOfInstances = 0;
        for (String str : list) {
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()) {
                numberOfInstances++;
            }
        }
        return numberOfInstances;
    }

    private static void readHorizontalAndVertical() {
        File file = new File("src/main/resources/day04.txt");
        try (Scanner scanner = new Scanner(file)) {
            List<StringBuilder> verticalBuilders = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.add(line);

                while (verticalBuilders.size() < line.length()) {
                    verticalBuilders.add(new StringBuilder());
                }
                for (int i = 0; i < line.length(); i++) {
                    verticalBuilders.get(i).append(line.charAt(i));
                }
            }
            matrixSize = list.size();
            for (StringBuilder sb : verticalBuilders) {
                list.add(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleDiagonals() {
        for (int col = 0; col < matrixSize; col++) {
            StringBuilder diagonal = new StringBuilder();
            for (int row = 0, c = col; row < matrixSize && c < matrixSize; row++, c++) {
                diagonal.append(list.get(row).charAt(c));
            }
            list.add(diagonal.toString());
        }
        for (int row = 1; row < matrixSize; row++) {
            StringBuilder diagonal = new StringBuilder();
            for (int r = row, c = 0; r < matrixSize && c < matrixSize; r++, c++) {
                diagonal.append(list.get(r).charAt(c));
            }
            list.add(diagonal.toString());
        }

        for (int col = 0; col < matrixSize; col++) {
            StringBuilder diagonal = new StringBuilder();
            for (int row = 0, c = col; row < matrixSize && c >= 0; row++, c--) {
                diagonal.append(list.get(row).charAt(c));
            }
            list.add(diagonal.toString());
        }
        for (int row = 1; row < matrixSize; row++) {
            StringBuilder diagonal = new StringBuilder();
            for (int r = row, c = matrixSize - 1; r < matrixSize && c >= 0; r++, c--) {
                diagonal.append(list.get(r).charAt(c));
            }
            list.add(diagonal.toString());
        }
    }
}
