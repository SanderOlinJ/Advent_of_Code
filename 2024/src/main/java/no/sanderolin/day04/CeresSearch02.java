package no.sanderolin.day04;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CeresSearch02 {

    private static List<String> lines;
    private static List<String> leftToRightDiagonalList;
    private static List<String> rightToLeftDiagonalList;
    private static final Pattern pattern = Pattern.compile("(?=(MAS|SAM))");
    private static int numberOfColumns;

    private record Pair(int col, int row) {}

    public static void solve() {
        lines = new ArrayList<>();
        leftToRightDiagonalList = new ArrayList<>();
        rightToLeftDiagonalList = new ArrayList<>();
        readFromFileToLists();
        readAllDiagonalsToList();
        List<Pair> listOfCentresLeftToRightDiagonal = findCentresInLeftToRightDiagonal(true);
        List<Pair> listOfCentresRightToLeftDiagonal = findCentresInLeftToRightDiagonal(false);
        int totalInstances = findNumberOfInstances(listOfCentresLeftToRightDiagonal, listOfCentresRightToLeftDiagonal);
        System.out.println(totalInstances);
    }

    private static int findNumberOfInstances(List<Pair> listOfCentresLeftToRightDiagonal, List<Pair> listOfCentresRightToLeftDiagonal) {
        int numberOfInstances = 0;
        for (Pair leftToRight : listOfCentresLeftToRightDiagonal) {
            for (Pair rightToLeft : listOfCentresRightToLeftDiagonal) {
                if (leftToRight.col == rightToLeft.col && leftToRight.row == rightToLeft.row) {
                    numberOfInstances++;
                }
            }
        }
       return numberOfInstances;
    }

    private static List<Pair> findCentresInLeftToRightDiagonal(boolean isLeftToRightDiagonal) {
        List<Pair> centres = new ArrayList<>();
        List<String> list = isLeftToRightDiagonal ? leftToRightDiagonalList : rightToLeftDiagonalList;
        for (int i = 0; i < list.size(); i++) {
            Matcher matcher = pattern.matcher(list.get(i));
            while (matcher.find()) {
                int column;
                int row = matcher.start() + 1;
                if (isLeftToRightDiagonal) {
                    column = (i + (matcher.start() + 1)) % numberOfColumns;
                    if (i >= numberOfColumns) {
                        row += 1 + (i % numberOfColumns);
                        column -= (i % numberOfColumns);
                    }
                } else {
                    column = (i - (matcher.start() + 1));
                    if (i >= numberOfColumns) {
                        row += 1 + (i % numberOfColumns);
                        column -= 1 + (i % numberOfColumns);
                    }
                }
                centres.add(new Pair(column, row));
            }
        }
        return centres;
    }

    private static void readFromFileToLists() {
        File file = new File("src/main/resources/day04.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            numberOfColumns = lines.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readAllDiagonalsToList() {

        for (int col = 0; col < numberOfColumns; col++) {
            StringBuilder diagonal = new StringBuilder();
            for (int row = 0, c = col; row < numberOfColumns && c < numberOfColumns; row++, c++) {
                diagonal.append(lines.get(row).charAt(c));
            }
            leftToRightDiagonalList.add(diagonal.toString());
        }
        for (int row = 1; row < numberOfColumns; row++) {
            StringBuilder diagonal = new StringBuilder();
            for (int r = row, c = 0; r < numberOfColumns && c < numberOfColumns; r++, c++) {
                diagonal.append(lines.get(r).charAt(c));
            }
            leftToRightDiagonalList.add(diagonal.toString());
        }

        for (int col = 0; col < numberOfColumns; col++) {
            StringBuilder diagonal = new StringBuilder();
            for (int row = 0, c = col; row < numberOfColumns && c >= 0; row++, c--) {
                diagonal.append(lines.get(row).charAt(c));
            }
            rightToLeftDiagonalList.add(diagonal.toString());
        }
        for (int row = 1; row < numberOfColumns; row++) {
            StringBuilder diagonal = new StringBuilder();
            for (int r = row, c = numberOfColumns - 1; r < numberOfColumns && c >= 0; r++, c--) {
                diagonal.append(lines.get(r).charAt(c));
            }
            rightToLeftDiagonalList.add(diagonal.toString());
        }

    }
}
