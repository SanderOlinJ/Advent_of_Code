package no.sanderolin;

import no.sanderolin.day01.HistorianHysteria01;
import no.sanderolin.day01.HistorianHysteria02;
import no.sanderolin.day02.RedNosedReports01;
import no.sanderolin.day02.RedNosedReports02;
import no.sanderolin.day03.MullItOver01;
import no.sanderolin.day03.MullItOver02;
import no.sanderolin.day04.CeresSearch01;
import no.sanderolin.day04.CeresSearch02;
import no.sanderolin.day05.PrintQueue01;
import no.sanderolin.day05.PrintQueue02;
import no.sanderolin.day06.GuardGallivant01;
import no.sanderolin.day06.GuardGallivant02;
import no.sanderolin.day07.BridgeRepair01;
import no.sanderolin.day07.BridgeRepair02;

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
                } else if (day == 2 && part == 1) {
                    RedNosedReports01.solve();
                } else if (day == 2 && part == 2) {
                    RedNosedReports02.solve();
                } else if (day == 3 && part == 1) {
                    MullItOver01.solve();
                } else if (day == 3 && part == 2) {
                    MullItOver02.solve();
                } else if (day == 4 && part == 1) {
                    CeresSearch01.solve();
                } else if (day == 4 && part == 2) {
                    CeresSearch02.solve();
                } else if (day == 5 && part == 1) {
                    PrintQueue01.solve();
                } else if (day == 5 && part == 2) {
                    PrintQueue02.solve();
                } else if (day == 6 && part == 1) {
                    GuardGallivant01.solve();
                } else if (day == 6 && part == 2) {
                    GuardGallivant02.solve();
                } else if (day == 7 && part == 1) {
                    BridgeRepair01.solve();
                } else if (day == 7 && part == 2) {
                    BridgeRepair02.solve();
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