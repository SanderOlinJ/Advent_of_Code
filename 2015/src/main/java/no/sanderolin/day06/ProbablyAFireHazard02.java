package no.sanderolin.day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProbablyAFireHazard02 {

    public static void solve() {
        ArrayList<Instruction> instructions = readFromFile();
        System.out.println(findNumberOfLightsOn(instructions));
    }

    private static int findNumberOfLightsOn(ArrayList<Instruction> instructions) {
        int[][] lights = new int[1000][1000];
        int numberOfLightsOn = 0;
        for (Instruction instruction : instructions) {
            for (int y = instruction.fromPosition.y; y <= instruction.toPosition.y; y++) {
                for (int x = instruction.fromPosition.x; x <= instruction.toPosition.x; x++) {
                    switch (instruction.action) {
                        case ON -> {
                            lights[y][x] += 1;
                            numberOfLightsOn+=1;
                        }
                        case OFF -> {
                            if (lights[y][x] == 0) break;
                            lights[y][x] -= 1;
                            numberOfLightsOn-=1;
                        }
                        case TOGGLE -> {
                            lights[y][x] += 2;
                            numberOfLightsOn+=2;
                        }
                    }
                }
            }
        }
        return numberOfLightsOn;
    }

    private static ArrayList<Instruction> readFromFile() {
        ArrayList<Instruction> fileLines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/day06.txt"))) {
            String line;
            Pattern pattern = Pattern.compile("(turn on|turn off|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)");
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    Action action = switch (matcher.group(1)) {
                        case "turn on" -> Action.ON;
                        case "turn off" -> Action.OFF;
                        case "toggle" -> Action.TOGGLE;
                        default -> throw new IllegalStateException("Unexpected value: " + matcher.group(1));
                    };
                    Position from = new Position(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
                    Position to = new Position(Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)));
                    fileLines.add(new Instruction(action, from, to));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileLines;
    }

    private record Position(int x, int y) {}

    private enum Action {
        ON,
        OFF,
        TOGGLE
    }

    private record Instruction(Action action, Position fromPosition, Position toPosition) {}
}
