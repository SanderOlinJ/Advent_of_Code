package no.sanderolin.day02;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class RedNosedReports02 {

    public static void solve() {
        File file = new File("src/main/resources/day02.txt");
        int safeLevels = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                List<Integer> integers = convertLineToIntegerList(scanner.nextLine());
                if (areLevelsSafe(integers)) safeLevels++;
                else {
                    if (areModifiedLevelsSafe(integers)) safeLevels++;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(safeLevels);
    }

    private static boolean areModifiedLevelsSafe(List<Integer> values) {
        for (int i = 0; i < values.size(); i++) {
            List<Integer> modifiedLevels = new ArrayList<>(values);
            modifiedLevels.remove(i);

            if (areLevelsSafe(modifiedLevels)) {
                return true;
            }
        }
        return false;
    }

    private static boolean areLevelsSafe(List<Integer> values) {
        boolean isIncreasing = values.get(0) < values.get(1);
        for (int i = 0; i < values.size() - 1; i++) {
            int diff = values.get(i) - values.get(i + 1);
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