package no.sanderolin;

import no.sanderolin.puzzle.day01.HistorianHysteria01;
import no.sanderolin.puzzle.day01.HistorianHysteria02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;

        System.out.println("Welcome to Advent of Code Solutions!");

        while (continueProgram) {
            System.out.print("Enter the day you want to see the solution for (e.g., 1, 2, 3): ");
            int day = scanner.nextInt();
            System.out.print("Enter the part you want to see the solution for (1 or 2): ");
            int part = scanner.nextInt();
            try {
                if (day == 1 && part == 1) {
                    HistorianHysteria01.solve();
                } else if (day == 1 && part == 2) {
                    HistorianHysteria02.solve();
                } else {
                    System.out.println("No solution available for Day " + day + ", Part " + part);
                }
            } catch (Exception e) {
                System.out.println("An error occurred while trying to solve the problem: " + e.getMessage());
            }
            System.out.print("Do you want to see another solution? (y/n): ");
            scanner.nextLine();
            String response = scanner.nextLine().trim().toLowerCase();

            if (!response.equals("y")) {
                continueProgram = false;
                System.out.println("Thank you for using Advent of Code Solutions. Goodbye!");
            }
        }

        scanner.close();
    }
}