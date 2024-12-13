package no.sanderolin.day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ClawContraption01 {

    private static final int BUTTON_A_COST = 3;
    private static final int BUTTON_B_COST = 1;

    public static void solve() {
        ArrayList<String> file = readFromFile();
        int totalTokens = 0;
        for (int i = 0; i < file.size() - 2; i+= 4) {
            totalTokens += findTokensRequiredToWin(file.get(i), file.get(i + 1), file.get(i + 2));
        }
        System.out.println(totalTokens);
    }

    private static int findTokensRequiredToWin(String buttonA, String buttonB, String prize) {
        // System of equations
        int a1 = Integer.parseInt(buttonA.substring(12, buttonA.indexOf(",")));
        int a2 = Integer.parseInt(buttonA.substring(buttonA.indexOf("Y") + 2));
        int b1 = Integer.parseInt(buttonB.substring(12, buttonB.indexOf(",")));
        int b2 = Integer.parseInt(buttonB.substring(buttonB.indexOf("Y") + 2));
        int c1 = Integer.parseInt(prize.substring(9, prize.indexOf(",")));
        int c2 = Integer.parseInt(prize.substring(prize.indexOf("Y") + 2));

        int det = a1 * b2 - a2 * b1;
        if (det == 0) return 0;

        double detX = c1 * b2 - c2 * b1;
        double detY = a1 * c2 - a2 * c1;
        if (detX % det != 0 || detY % det != 0) return 0;

        int x = (int) (detX / det);
        int y = (int) (detY / det);
        if (x >= 100 || y >= 100) return 0;
        return x * BUTTON_A_COST + y * BUTTON_B_COST;
    }

    private static ArrayList<String> readFromFile() {
        ArrayList<String> file = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day13.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                file.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
