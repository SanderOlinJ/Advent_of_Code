package no.sanderolin.day03;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOver01 {

    public static void solve() {
        File file = new File("src/main/resources/day03.txt");
        try (Scanner scanner = new Scanner(file)) {
            int sum = 0;
            Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
            while (scanner.hasNextLine()) {
                Matcher matcher = pattern.matcher(scanner.nextLine());
                while (matcher.find()) {
                    int left = Integer.parseInt(matcher.group(1));
                    int right = Integer.parseInt(matcher.group(2));
                    sum += left * right;
                }
            }
            System.out.println(sum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
