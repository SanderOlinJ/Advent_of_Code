package no.sanderolin.day17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ChronospatialComputer01 {

    private static int registerA;
    private static int registerB;
    private static int registerC;
    private static final StringBuilder output = new StringBuilder();

    public static void solve() {
        String[] program = readFromFile();
        parseProgram(program);
        System.out.println(output.deleteCharAt(output.length() - 1));
    }

    private static void parseProgram(String[] program) {
        for (int i = 0; i < program.length; i+=2) {
            int operand = Integer.parseInt(program[i+1]);
            int comboOperand = operand;
            if (operand == 4) comboOperand = registerA;
            else if (operand == 5) comboOperand = registerB;
            else if (operand == 6) comboOperand = registerC;

            switch (program[i]) {
                case "0" -> registerA = opcode0And6And7(comboOperand);
                case "1" -> opcode1(operand);
                case "2" -> opcode2(comboOperand);
                case "3" -> i = (opcode3(operand, i));
                case "4" -> opcode4();
                case "5" -> opcode5(comboOperand);
                case "6" -> registerB = opcode0And6And7(comboOperand);
                case "7" -> registerC = opcode0And6And7(comboOperand);
            }
        }
    }

    private static int opcode0And6And7(int operand) {
        int numerator = registerA;
        int divisor = (int) Math.pow(2, operand);
        return numerator / divisor;
    }

    private static void opcode1(int operand) {
        registerB ^= operand;
    }

    private static void opcode2(int operand) {
        registerB = operand % 8;
    }

    private static int opcode3(int operand, int currentInstruction) {
        if (registerA == 0) return currentInstruction;
        return operand - 2;
    }

    private static void opcode4() {
        registerB ^= registerC;
    }

    private static void opcode5(int operand) {
        output.append(operand % 8).append(",");
    }

    private static String[] readFromFile() {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day17.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        registerA = Integer.parseInt(lines.get(0).substring(11).trim());
        registerB = Integer.parseInt(lines.get(1).substring(11).trim());
        registerC = Integer.parseInt(lines.get(2).substring(11).trim());

        String programString = lines.get(4);
        return programString.substring(8).trim().split(",");
    }
}
