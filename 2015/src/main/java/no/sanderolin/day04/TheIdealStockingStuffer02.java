package no.sanderolin.day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TheIdealStockingStuffer02 {

    public static void solve() throws NoSuchAlgorithmException {
        String line = readFromFile();
        System.out.println(findLowestNumber(line));
    }

    private static int findLowestNumber(String line) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        int i = 0;
        while (true) {
            String newLine = line.concat(String.valueOf(i));
            String hashedString = createHash(newLine, md);
            if (hashedString.startsWith("000000")) break;
            i++;
        }
        return i;
    }

    private static String createHash(String input, MessageDigest md)  {
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        StringBuilder hashedString = new StringBuilder(no.toString(16));
        while (hashedString.length() < 32) hashedString.insert(0, "0");
        return hashedString.toString();
    }

    private static String readFromFile() {
        String line = "";
        try {
            line = new BufferedReader(new FileReader("src/main/resources/day04.txt")).readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return line;
    }
}
