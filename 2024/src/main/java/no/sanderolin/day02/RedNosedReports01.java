package no.sanderolin.day02;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RedNosedReports01 {

    public static void solve() {
        File file = new File("src/main/resources/day02.txt");
        int safeLevels = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                List<Integer> integers = convertLineToIntegerList(scanner.nextLine());
                if (areLevelsSafe(integers)) safeLevels++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(safeLevels);
    }

    private static boolean areLevelsSafe(List<Integer> integers) {
        boolean isIncreasing = integers.get(0) < integers.get(1);
        for (int i = 0; i < integers.size() - 1; i++) {
            int diff = integers.get(i) - integers.get(i + 1);
            if ((isIncreasing && ((diff < -3) || (diff > -1)))
                    || (!isIncreasing && ((diff > 3) || (diff < 1)))) {
                return false;
            }
        }
        return true;
    }

    private static List<Integer> convertLineToIntegerList(String line) {
        String[] values = line.split(" ");
        List<Integer> integers = new ArrayList<>();
        for (String value : values) {
            integers.add(Integer.parseInt(value));
        }
        return integers;
    }
}
