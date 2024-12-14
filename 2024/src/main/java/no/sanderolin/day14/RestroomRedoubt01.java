package no.sanderolin.day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class RestroomRedoubt01 {

    public static void solve() {
        ArrayList<String> lines = readFromFile();
        ArrayList<RobotGuard> robotGuards = convertLinesToRobotGuards(lines);
        int limitX = 101, limitY = 103;
        long upperLeftQuadrant = 0, upperRightQuadrant = 0, lowerLeftQuadrant = 0, lowerRightQuadrant = 0;
        for (RobotGuard robotGuard : robotGuards) {
            robotGuard.move(100, limitX, limitY); // Moves the guard
            // We leave out the middle column and middle row by doing less than and greater than.
            if (robotGuard.getPositionX() < (limitX - 1) / 2 ) {
                if (robotGuard.getPositionY() < (limitY - 1) / 2) {
                    upperLeftQuadrant++;
                } else if (robotGuard.getPositionY() > (limitY - 1) / 2) {
                    lowerLeftQuadrant++;
                }
            } else if (robotGuard.getPositionX() > (limitX - 1) / 2) {
                if (robotGuard.getPositionY() < (limitY - 1) / 2) {
                    upperRightQuadrant++;
                } else if (robotGuard.getPositionY() > (limitY - 1) / 2) {
                    lowerRightQuadrant++;
                }
            }
        }
        long answer = upperLeftQuadrant * upperRightQuadrant * lowerLeftQuadrant * lowerRightQuadrant;
        System.out.println(answer);
    }

    private static ArrayList<RobotGuard> convertLinesToRobotGuards(ArrayList<String> lines) {
        ArrayList<RobotGuard> robotGuards = new ArrayList<>();
        for (String line : lines) {
            int startX, startY, velX, velY;
            startX = Integer.parseInt(line.substring(2, line.indexOf(",")));
            startY = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(" ")));
            velX = Integer.parseInt(line.substring(line.indexOf("v") + 2, line.lastIndexOf(",")));
            velY = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1));
            robotGuards.add(new RobotGuard(startX, startY, velX, velY));
        }
        return robotGuards;
    }

    private static ArrayList<String> readFromFile() {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day14.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static class RobotGuard {
        private int positionX;
        private int positionY;
        private final int velX;
        private final int velY;

        public RobotGuard(int startX, int startY, int velX, int velY) {
            this.positionX = startX;
            this.positionY = startY;
            this.velX = velX;
            this.velY = velY;
        }

        public void move(int seconds, int limitX, int limitY) {
            for (int i = 0; i < seconds; i++) {
                int newX = positionX + velX;
                if (newX < 0) positionX = newX + limitX;
                else positionX = newX % limitX;

                int newY = positionY + velY;
                if (newY < 0) positionY = newY + limitY;
                else positionY = newY % limitY;
            }
        }

        public int getPositionX() {
            return positionX;
        }

        public int getPositionY() {
            return positionY;
        }
    }
}
