package no.sanderolin;

import no.sanderolin.day01.NotQuiteLisp01;
import no.sanderolin.day01.NotQuiteLisp02;
import no.sanderolin.day02.IWasToldThereWouldBeNoMath01;
import no.sanderolin.day02.IWasToldThereWouldBeNoMath02;
import no.sanderolin.day03.PerfectlySphericalHousesInAVacuum01;
import no.sanderolin.day03.PerfectlySphericalHousesInAVacuum02;
import no.sanderolin.day04.TheIdealStockingStuffer01;
import no.sanderolin.day04.TheIdealStockingStuffer02;
import no.sanderolin.day05.DoesntHeHaveInternElvesForThis01;
import no.sanderolin.day05.DoesntHeHaveInternElvesForThis02;
import no.sanderolin.day06.ProbablyAFireHazard01;
import no.sanderolin.day06.ProbablyAFireHazard02;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;

        System.out.println("Advent of Code 2015!");

        while (continueProgram) {
            System.out.print("Enter the day you want to see the solution for (e.g., 1, 2, 3): ");
            int day = scanner.nextInt();
            System.out.print("Enter the part you want to see the solution for (1 or 2): ");
            int part = scanner.nextInt();
            try {
                if (day == 1 && part == 1) {
                    NotQuiteLisp01.solve();
                } else if (day == 1 && part == 2) {
                    NotQuiteLisp02.solve();
                } else if (day == 2 && part == 1) {
                    IWasToldThereWouldBeNoMath01.solve();
                } else if (day == 2 && part == 2) {
                    IWasToldThereWouldBeNoMath02.solve();
                } else if (day == 3 && part == 1) {
                    PerfectlySphericalHousesInAVacuum01.solve();
                } else if (day == 3 && part == 2) {
                    PerfectlySphericalHousesInAVacuum02.solve();
                } else if (day == 4 && part == 1) {
                    TheIdealStockingStuffer01.solve();
                } else if (day == 4 && part == 2) {
                    TheIdealStockingStuffer02.solve();
                } else if (day == 5 && part == 1) {
                    DoesntHeHaveInternElvesForThis01.solve();
                } else if (day == 5 && part == 2) {
                    DoesntHeHaveInternElvesForThis02.solve();
                } else if (day == 6 && part == 1) {
                    ProbablyAFireHazard01.solve();
                } else if (day == 6 && part == 2) {
                    ProbablyAFireHazard02.solve();
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
                System.out.println("Goodbye!");
            }
        }
        scanner.close();
    }
}