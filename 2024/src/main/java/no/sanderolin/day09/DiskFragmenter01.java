package no.sanderolin.day09;

import java.io.BufferedReader;
import java.io.FileReader;

public class DiskFragmenter01 {

    public static void solve() {
        int[] line = readFromFile();
        long checksum = 0;
        long realIndex = 0;
        int indexOfLastMoved = line.length - 1;
        int sizeOfFileToMove = line[indexOfLastMoved];

        for (int i = 0; i < indexOfLastMoved; i+=2) {

            // Calculate the checksum of file on left side that does not need to move
            int amountOfLeftFile = line[i];
            for (int j = 0; j < amountOfLeftFile; j++) {
                checksum += realIndex * i/2;
                realIndex++;
            }

            // Move file from right to free space in left side, and calculate check sum
            int amountOfFreeSpace = line[i+1];
            for(int j = 0; j < amountOfFreeSpace; j++) {
                // If more free space, but no more files to move from the current iterated file on right
                if (sizeOfFileToMove <= 0) {
                    indexOfLastMoved -= 2; // Then get new files 2 index down
                    if (indexOfLastMoved <= i) break;
                    sizeOfFileToMove = line[indexOfLastMoved];
                }
                // Calculated checksum of newly moved files
                checksum += realIndex * (indexOfLastMoved / 2);
                sizeOfFileToMove--;
                realIndex++;
            }
        }
        // If there were more files to move when last empty space on the left got filled
        // Then "append" the files at the end, and calculate check sum.
        for (int i = 0; i < sizeOfFileToMove; i++) {
            checksum += realIndex * (indexOfLastMoved / 2);
            realIndex++;
        }
        System.out.println(checksum);

    }

    private static int[] readFromFile() {
        String line = "";
        try {
            line = new BufferedReader(new FileReader("src/main/resources/day09.txt")).readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int[] fileAsArray = new int[line.length()];
        for (int i = 0; i < line.length(); i++) {
            fileAsArray[i] = Integer.parseInt(String.valueOf(line.charAt(i)));
        }
        return fileAsArray;
    }
}