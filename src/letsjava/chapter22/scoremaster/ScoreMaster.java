package letsjava.chapter22.scoremaster;

import java.util.Scanner;

/**
 * @Description
 * @Version v1.0
 * @Author yange1
 * @Date 2020/7/24
 * @Time 15:04
 */
@SuppressWarnings("ALL" )
public class ScoreMaster {
    private static String invalidInput = "invalid input: ";

    public static void main(String[] args) {
        //Declare six variables, each representing six disciplines
        int chineseIndex = 0;
        int mathIndex = 1;
        int englishIndex = 2;
        int physicsIndex = 3;
        int chemistryIndex = 4;
        int biologyIndex = 5;

        int totalDisciplinesCount = 6;
        //Title of each disciplines
        String[] title = new String[totalDisciplinesCount];
        title[chineseIndex] = "Chinese";
        title[mathIndex] = "Math";
        title[englishIndex] = "English";
        title[physicsIndex] = "Physics";
        title[chemistryIndex] = "Chemistry";
        title[biologyIndex] = "Biology";

        //Get years count of having score recored from scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many years have a scores record. ");
        int yearCount = scanner.nextInt();
        double[][] scores = new double[yearCount][totalDisciplinesCount];
        //Print scors for each discipline in each year.
        for (int i = 0; i < yearCount; i++) {
            System.out.println("----------------------------------year " + (i + 1) + "----------------------------------");
            for (int j = 0; j < totalDisciplinesCount; j++) {
                scores[i][j] = 80 + Math.random() * 20;
                System.out.println("The " + title[j] + " score for year " + (i + 1) + " is " + scores[i][j]);
            }
        }

        boolean cont = true;
        while (cont) {
            System.out.println();
            System.out.println("Please select your operation below");
            System.out.println(
                    "1: Seeking the best score of a year\n" +
                            "2: Find the average score for a year\n" +
                            "3: Seeking the best score of all year\n" +
                            "4: Seeking the best score in a discipline over the years"
            );
            int oprtId = scanner.nextInt();
            int year = 0;
            switch (oprtId) {
                case 1:
                    cont = seekingTheBestScoreOfYear(scanner, yearCount, scores, cont);
                    break;
                case 2:
                    cont = findTheAverageScoreForYear(totalDisciplinesCount, scanner, yearCount, scores, cont);
                    break;
                case 3:
                    findBestScoreOfAllYears(totalDisciplinesCount, title, yearCount, scores);
                    break;
                case 4:
                    cont = seekingTheBestScoreInDisciplinesOverTheYears(totalDisciplinesCount, title, scanner, yearCount, scores, cont);
                    break;
                default:
                    cont = false;
                    System.out.println("unsupported option: " + oprtId + ", exit");
            }
        }
    }

    private static boolean seekingTheBestScoreOfYear(Scanner scanner, int yearCount, double[][] scores, boolean cont) {
        int year;
        System.out.println("Which year's best score you want to know?");
        year = scanner.nextInt();
        if (year <= 0 || year > yearCount) {
            System.out.println(invalidInput + year);
            cont = false;
            return cont;
        }
        year -= 1;
        int aYearsBestScoreId = 0;
        for (int i = 0; i < year; i++) {
            if (scores[year][aYearsBestScoreId] < scores[year][i]) {
                aYearsBestScoreId = i;
            }
        }
        System.out.println("The best score for year " + (year + 1) + " is " + scores[year][aYearsBestScoreId]);
        return cont;
    }

    private static boolean findTheAverageScoreForYear(int totalDisciplinesCount, Scanner scanner, int yearCount, double[][] scores, boolean cont) {
        int year;
        System.out.println("Which year's average score you want to know?");
        year = scanner.nextInt();
        if (year <= 0 || year > yearCount) {
            System.out.println(invalidInput + year);
            cont = false;
            return cont;
        }
        year -= 1;
        double avgScoreInYear = 0;
        for (int i = 0; i < totalDisciplinesCount; i++) {
            avgScoreInYear += scores[year][i];
        }
        avgScoreInYear /= totalDisciplinesCount;
        System.out.println("The average score for year " + (year + 1) + " is " + avgScoreInYear);
        return cont;
    }

    private static boolean seekingTheBestScoreInDisciplinesOverTheYears(int totalDisciplinesCount, String[] title, Scanner scanner, int yearCount, double[][] scores, boolean cont) {
        int year;
        System.out.println("Which discipline's best score over the year you want to know?");
        for (int i = 0; i < totalDisciplinesCount; i++) {
            System.out.println((i + 1) + " for -> " + title[i]);
        }
        int disciplineId = scanner.nextInt();
        if (disciplineId <= 0 || disciplineId > totalDisciplinesCount) {
            System.out.println(invalidInput + disciplineId);
            cont = false;
            return cont;
        }
        disciplineId -= 1;
        year = 0;
        for (int i = 0; i < yearCount; i++) {
            if (scores[year][disciplineId] < scores[i][disciplineId]) {
                year = i;
            }
        }
        System.out.println("The best score of " + title[disciplineId] + " over the years is in year " + (year + 1) + ", with a score of " + scores[year][disciplineId]);
        return cont;
    }

    private static void findBestScoreOfAllYears(int totalDisciplinesCount, String[] title, int yearCount, double[][] scores) {
        int bestYearId = 0;
        int bestScoreId = 0;
        for (int i = 0; i < yearCount; i++) {
            for (int j = 0; j < totalDisciplinesCount; j++) {
                if (scores[bestYearId][bestScoreId] < scores[i][j]) {
                    bestYearId = i;
                    bestScoreId = j;
                }
            }
        }
        System.out.println("The best score of all years is " + title[bestScoreId] + " in year " + (bestYearId + 1) + ", with a score of " + scores[bestYearId][bestScoreId]);
    }
}