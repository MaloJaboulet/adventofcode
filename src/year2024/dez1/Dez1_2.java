package year2024.dez1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Your analysis only confirmed what everyone feared: the two lists of location IDs are indeed very different.
 * <p>
 * Or are they?
 * <p>
 * The Historians can't agree on which group made the mistakes or how to read most of the Chief's handwriting, but in the commotion you notice an interesting detail: a lot of location IDs appear in both lists! Maybe the other numbers aren't location IDs at all but rather misinterpreted handwriting.
 * <p>
 * This time, you'll need to figure out exactly how often each number from the left list appears in the right list. Calculate a total similarity score by adding up each number in the left list after multiplying it by the number of times that number appears in the right list.
 * <p>
 * Here are the same example lists again:
 * <p>
 * 3   4
 * 4   3
 * 2   5
 * 1   3
 * 3   9
 * 3   3
 * For these example lists, here is the process of finding the similarity score:
 * <p>
 * The first number in the left list is 3. It appears in the right list three times, so the similarity score increases by 3 * 3 = 9.
 * The second number in the left list is 4. It appears in the right list once, so the similarity score increases by 4 * 1 = 4.
 * The third number in the left list is 2. It does not appear in the right list, so the similarity score does not increase (2 * 0 = 0).
 * The fourth number, 1, also does not appear in the right list.
 * The fifth number, 3, appears in the right list three times; the similarity score increases by 9.
 * The last number, 3, appears in the right list three times; the similarity score again increases by 9.
 * So, for these example lists, the similarity score at the end of this process is 31 (9 + 4 + 0 + 0 + 9 + 9).
 * <p>
 * Once again consider your left and right lists. What is their similarity score?
 */
public class Dez1_2 {

    public int calculateSimilarityScore(List<Integer> leftList, List<Integer> rightList) {
        int totalSimilarityScore = 0;

        leftList.sort(null);

        Map<Integer, Integer> rightMap = new HashMap<>();

        for (Integer i : rightList) {
            if (rightMap.containsKey(i)) {
                rightMap.put(i, rightMap.get(i) + 1);
            } else {
                rightMap.put(i, 1);
            }
        }

        for (int position : leftList) {
            int tempScore = 0;
            if (rightMap.get(position) != null) {
                tempScore = position * rightMap.get(position);
            }

            totalSimilarityScore = totalSimilarityScore + (tempScore);
        }

        return totalSimilarityScore;
    }

    public List<List<Integer>> readFile() {
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        List<List<Integer>> returnList = new ArrayList<>();
        returnList.add(leftList);
        returnList.add(rightList);
        try {
            File competitorFile = new File("src/year2024/dez1/input.txt");
            Scanner competitorScanner = new Scanner(competitorFile); // creates a new scanner for the file


            while (competitorScanner.hasNextLine()) {
                List<Integer> row = getRecordFromLine(competitorScanner.nextLine());

                leftList.add(row.getFirst());
                rightList.add(row.getLast());
            }
            competitorScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return returnList;
    }

    private List<Integer> getRecordFromLine(String line) {
        List<Integer> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(Integer.valueOf(rowScanner.next()));
            }
        }
        return values;
    }

    public static void main(String[] args) {
        Dez1_2 dez1_2 = new Dez1_2();
        List<List<Integer>> lists = dez1_2.readFile();

        System.out.println(dez1_2.calculateSimilarityScore(lists.getFirst(), lists.getLast()));
    }
}
