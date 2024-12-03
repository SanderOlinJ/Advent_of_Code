package no.sanderolin.day03;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOver02 {

    public static void solve() {
        File file = new File("src/main/resources/day03.txt");
        try (Scanner scanner = new Scanner(file)) {
            List<String> matches = findMatches(scanner);
            System.out.println(getSum(matches));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<String> findMatches(Scanner scanner) {
        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)");
        while (scanner.hasNextLine()) {
            Matcher matcher = pattern.matcher(scanner.nextLine());
            while (matcher.find()) {
                matches.add(matcher.group());
            }
        }
        return matches;
    }

    private static int getSum(List<String> matches) {
        int sum = 0;
        boolean isEnabled = true;
        for (String match : matches) {
            if (match.equals("do()")) isEnabled = true;
            else if (match.equals("don't()")) isEnabled = false;
            else {
                if (!isEnabled) continue;
                int left = Integer.parseInt(match.substring(match.indexOf('(') + 1, match.indexOf(',')));
                int right = Integer.parseInt(match.substring(match.indexOf(',') + 1, match.indexOf(')')));
                sum += left * right;
            }
        }
        return sum;
    }
}
