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
import no.sanderolin.day08.ResonantCollinearity01;
import no.sanderolin.day08.ResonantCollinearity02;
import no.sanderolin.day09.DiskFragmenter01;
import no.sanderolin.day09.DiskFragmenter02;
import no.sanderolin.day10.HoofIt01;
import no.sanderolin.day10.HoofIt02;
import no.sanderolin.day11.PlutonianPebbles01;
import no.sanderolin.day11.PlutonianPebbles02;
import no.sanderolin.day12.GardenGroups01;
import no.sanderolin.day12.GardenGroups02;
import no.sanderolin.day13.ClawContraption01;
import no.sanderolin.day13.ClawContraption02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;

        System.out.println("Advent of Code 2024!");

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
                } else if (day == 8 && part == 1) {
                    ResonantCollinearity01.solve();
                } else if (day == 8 && part == 2) {
                    ResonantCollinearity02.solve();
                } else if (day == 9 && part == 1) {
                    DiskFragmenter01.solve();
                } else if (day == 9 && part == 2) {
                    DiskFragmenter02.solve();
                } else if (day == 10 && part == 1) {
                    HoofIt01.solve();
                } else if (day == 10 && part == 2) {
                    HoofIt02.solve();
                } else if (day == 11 && part == 1) {
                    PlutonianPebbles01.solve();
                } else if (day == 11 && part == 2) {
                    PlutonianPebbles02.solve();
                } else if (day == 12 && part == 1) {
                    GardenGroups01.solve();
                } else if (day == 12 && part == 2) {
                    GardenGroups02.solve();
                } else if (day == 13 && part == 1) {
                    ClawContraption01.solve();
                } else if (day == 13 && part == 2) {
                    ClawContraption02.solve();
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