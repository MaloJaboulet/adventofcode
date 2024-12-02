package year2024.dez2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Fortunately, the first location The Historians want to search isn't a long walk from the Chief Historian's office.
 * <p>
 * While the Red-Nosed Reindeer nuclear fusion/fission plant appears to contain no sign of the Chief Historian, the engineers there run up to you as soon as they see you. Apparently, they still talk about the time Rudolph was saved through molecular synthesis from a single electron.
 * <p>
 * They're quick to add that - since you're already here - they'd really appreciate your help analyzing some unusual data from the Red-Nosed reactor. You turn to check if The Historians are waiting for you, but they seem to have already divided into groups that are currently searching every corner of the facility. You offer to help with the unusual data.
 * <p>
 * The unusual data (your puzzle input) consists of many reports, one report per line. Each report is a list of numbers called levels that are separated by spaces. For example:
 * <p>
 * 7 6 4 2 1
 * 1 2 7 8 9
 * 9 7 6 2 1
 * 1 3 2 4 5
 * 8 6 4 4 1
 * 1 3 6 7 9
 * This example data contains six reports each containing five levels.
 * <p>
 * The engineers are trying to figure out which reports are safe. The Red-Nosed reactor safety systems can only tolerate levels that are either gradually increasing or gradually decreasing. So, a report only counts as safe if both of the following are true:
 * <p>
 * The levels are either all increasing or all decreasing.
 * Any two adjacent levels differ by at least one and at most three.
 * In the example above, the reports can be found safe or unsafe by checking those rules:
 * <p>
 * 7 6 4 2 1: Safe because the levels are all decreasing by 1 or 2.
 * 1 2 7 8 9: Unsafe because 2 7 is an increase of 5.
 * 9 7 6 2 1: Unsafe because 6 2 is a decrease of 4.
 * 1 3 2 4 5: Unsafe because 1 3 is increasing but 3 2 is decreasing.
 * 8 6 4 4 1: Unsafe because 4 4 is neither an increase or a decrease.
 * 1 3 6 7 9: Safe because the levels are all increasing by 1, 2, or 3.
 * So, in this example, 2 reports are safe.
 * <p>
 * Analyze the unusual data from the engineers. How many reports are safe?
 */
public class Dez2 {

    public boolean isReportSafe(String report) {
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
       /* List<String> list = List.of(
                "7 6 4 2 1",
                "1 2 7 8 9",
                "9 7 6 2 1",
                "1 3 2 4 5",
                "8 6 4 4 1",
                "1 3 6 7 9");*/

        Dez2 dez2 = new Dez2();
        List<String> list = dez2.readFile();

        int validCounter = 0;
        for (String report : list) {
            if (dez2.isReportSafe(report)) {
                validCounter++;
            }
        }

        System.out.println("Valid levels: " + validCounter);
    }
}
