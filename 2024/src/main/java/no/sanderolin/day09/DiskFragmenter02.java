package no.sanderolin.day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DiskFragmenter02 {

    public static void solve() {
        // Convert the files to class containing ID, size and free space after the file
        ArrayList<FileFragment> listOfFiles = readFromFile();

        for (int i = listOfFiles.size() - 1; i > 0; i--) {
            FileFragment rightFile = listOfFiles.get(i); // Get the file from the right side (one to move)
            for (int j = 0; j < i; j++) {
                FileFragment leftFile = listOfFiles.get(j); // Get the file from the left side (to see if free space)
                // If no following free space, go to the next file from the left side.
                if (leftFile.getFreeSpaceAfter() < rightFile.getSize()) continue;

                int newFreeSpace = leftFile.getFreeSpaceAfter() - rightFile.getSize();
                int freeSpaceCreatedByMovingFile = rightFile.getFreeSpaceAfter() + rightFile.getSize();
                FileFragment nextFile = listOfFiles.get(i-1);
                // If the left file (the file we are moving the right file behind) is right in front of it,
                // then the original free space after the left file + free space after the right file
                // should be added behind the right file, as it is now just "pushed" to the left, not moved across other files
                if (j == i - 1) {
                    nextFile.setFreeSpaceAfter(0);
                    rightFile.setFreeSpaceAfter(newFreeSpace + freeSpaceCreatedByMovingFile);
                } else {
                    // Else, the file in front of the right file will inherit the free space behind the right file
                    // as this file will be moved somewhere in front of it.
                    nextFile.setFreeSpaceAfter(nextFile.getFreeSpaceAfter() + freeSpaceCreatedByMovingFile);
                    rightFile.setFreeSpaceAfter(newFreeSpace);
                    listOfFiles.set(j, leftFile.setFreeSpaceAfter(0));
                }
                listOfFiles.set(i - 1, nextFile); // Update the file in front of the moved file
                listOfFiles.remove(i); // Removed the moved file from its original index
                listOfFiles.add(j+1, rightFile); // Move the file to the new index
                i++; // Make sure we don't skip a file, as the next to last file now has incremented in index.
                break;
            }
        }

        long realIndex = 0;
        long checkSum = 0;
        for (FileFragment fileFragment : listOfFiles) {
            for (int i = 0; i < fileFragment.getSize(); i++) {
                checkSum += realIndex * fileFragment.getId();
                realIndex++;
            }
            realIndex += fileFragment.getFreeSpaceAfter();
        }
        System.out.println(checkSum);
    }

    private static ArrayList<FileFragment> readFromFile() {
        String line = "";
        try {
            line = new BufferedReader(new FileReader("src/main/resources/day09.txt")).readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<FileFragment> listOfFiles = new ArrayList<>();
        for (int i = 0; i < line.length(); i+=2) {
            if (i == line.length() - 1) {
                listOfFiles.add(new FileFragment(i/2,
                        Integer.parseInt(String.valueOf(line.charAt(i))), 0));
            } else {
                listOfFiles.add(new FileFragment(i / 2,
                        Integer.parseInt(String.valueOf(line.charAt(i))),
                        Integer.parseInt(String.valueOf(line.charAt(i + 1)))));
            }
        }
        return listOfFiles;
    }

    private static class FileFragment{
        private final int id;
        private final int size;
        private int freeSpaceAfter;

        public FileFragment(int id, int size, int freeSpaceAfter) {
            this.id = id;
            this.size = size;
            this.freeSpaceAfter = freeSpaceAfter;
        }

        public FileFragment setFreeSpaceAfter(int freeSpaceAfter) {
            this.freeSpaceAfter = freeSpaceAfter;
            return this;
        }

        public int getId() {
            return id;
        }

        public int getSize() {
            return size;
        }

        public int getFreeSpaceAfter() {
            return freeSpaceAfter;
        }
    }
}
