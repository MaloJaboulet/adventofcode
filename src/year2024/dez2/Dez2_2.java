package year2024.dez2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The engineers are surprised by the low number of safe reports until they realize they forgot to tell you about the Problem Dampener.
 * <p>
 * The Problem Dampener is a reactor-mounted module that lets the reactor safety systems tolerate a single bad level in what would otherwise be a safe report. It's like the bad level never happened!
 * <p>
 * Now, the same rules apply as before, except if removing a single level from an unsafe report would make it safe, the report instead counts as safe.
 * <p>
 * More of the above example's reports are now safe:
 * <p>
 * 7 6 4 2 1: Safe without removing any level.
 * 1 2 7 8 9: Unsafe regardless of which level is removed.
 * 9 7 6 2 1: Unsafe regardless of which level is removed.
 * 1 3 2 4 5: Safe by removing the second level, 3.
 * 8 6 4 4 1: Safe by removing the third level, 4.
 * 1 3 6 7 9: Safe without removing any level.
 * Thanks to the Problem Dampener, 4 reports are actually safe!
 * <p>
 * Update your analysis by handling situations where the Problem Dampener can remove a single level from unsafe reports. How many reports are now safe?
 */
public class Dez2_2 {

    public boolean isSafe(String report) {
        String[] levels = report.split(" ");

        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 0; i < levels.length - 1; i++) {
            int diff = Integer.parseInt(levels[i + 1]) - Integer.parseInt(levels[i]);

            // Check if the difference is out of range
            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false;
            }

            // Check monotonicity
            if (diff < 0) {
                isIncreasing = false;
            } else if (diff > 0) {
                isDecreasing = false;
            }
        }

        return isIncreasing || isDecreasing;
    }

    public boolean canBeMadeSafe(String report) {
        String[] levels = report.split(" ");
        for (int i = 0; i < levels.length; i++) {
            List<String> modifiedLevels = new ArrayList<>(Arrays.asList(levels));
            modifiedLevels.remove(i);

            String formattedString = modifiedLevels.toString()
                    .replace(",", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")  //remove the left bracket
                    .trim();

            if (isSafe(formattedString)) {
                return true;
            }
        }
        return false;
    }

    public List<String> readFile() {
        List<String> rows = new ArrayList<>();
        try {
            File competitorFile = new File("src/year2024/dez2/input.txt");
            Scanner competitorScanner = new Scanner(competitorFile); // creates a new scanner for the file

            while (competitorScanner.hasNextLine()) {
                rows.add(competitorScanner.nextLine());
            }
            competitorScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return rows;
    }

    public static void main(String[] args) {
        Dez2_2 dez2_2 = new Dez2_2();
        List<String> list = dez2_2.readFile();

        /*List<String> list = List.of(
                "7 6 4 2 1",
                "1 2 7 8 9",
                "9 7 6 2 1",
                "1 3 2 4 5",
                "8 6 4 4 1",
                "1 3 6 7 9");*/

        int validCounter = 0;
        // Analyze each report
        for (String report : list) {
            if (dez2_2.isSafe(report) || dez2_2.canBeMadeSafe(report)) {
                validCounter++;
            }
        }

        System.out.println("Valid levels: " + validCounter);
    }
}