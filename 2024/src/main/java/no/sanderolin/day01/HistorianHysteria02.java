package no.sanderolin.day01;

import java.io.File;
import java.util.*;

public class HistorianHysteria02 {

    public static void solve() {
        File file = new File("src/main/resources/day01.txt");
        List<Integer> leftList = new ArrayList<>();
        HashMap<Integer, Integer> rightMap = new HashMap<>();
        int numberOfInstances = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String leftString = line.substring(0, line.indexOf(" "));
                String rightString = line.substring(leftString.length()).trim();
                leftList.add(Integer.parseInt(leftString));
                rightMap.merge(Integer.parseInt(rightString), 1, Integer::sum);
            }

            for (int i : leftList) {
                numberOfInstances += (i * rightMap.getOrDefault(i, 0));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(numberOfInstances);
    }
}