package no.sanderolin.day07;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class BridgeRepair02 {
    private static boolean isValid;
    private static long[] calibrationValues;
    private static long testValue;

    public static void solve() {
        File file = new File("src/main/resources/day07.txt");
        try (Scanner scanner = new Scanner(file)) {
            long validCalibrations = 0;
            while (scanner.hasNextLine()) {
                isValid = false;
                String line = scanner.nextLine();
                testValue = Long.parseLong(line.substring(0, line.indexOf(":")));
                calibrationValues = Arrays.stream(
                        line.substring(line.indexOf(":") + 2).split(" "))
                        .mapToLong(Long::parseLong)
                        .toArray();

                if (checkIfCalibrationIsValid()) validCalibrations += testValue;
            }
            System.out.println(validCalibrations);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean checkIfCalibrationIsValid() {
        long operatorValue = 0;
        char[] operators = {'*', '+', '|'};
        int k = calibrationValues.length - 1;
        // Same recursive method as part 1, we just add a '|' to signify concatenation.
        checkIfCalibrationIsValid(operators, "", operators.length, k, operatorValue);
        return isValid;
    }

    private static void checkIfCalibrationIsValid(char[] operators, String prefix, int n, int k, long operatorValue) {
        if (isValid) return;
        if (k == 0) {
            operatorValue = calibrationValues[0];
            for (int i = 0; i < prefix.length(); i++) {
                if (prefix.charAt(i) == '*') operatorValue *= calibrationValues[i+1];
                else if (prefix.charAt(i) == '+') operatorValue += calibrationValues[i+1];
                else {
                    operatorValue = Long.parseLong(operatorValue + "" + calibrationValues[i+1]);
                }
            }
            if (operatorValue == testValue) isValid = true;
            return;
        }
        for (int i = 0; i < n; ++i) {
            String newPrefix = prefix + operators[i];
            checkIfCalibrationIsValid(operators, newPrefix, n, k - 1, operatorValue);
        }
    }
}
