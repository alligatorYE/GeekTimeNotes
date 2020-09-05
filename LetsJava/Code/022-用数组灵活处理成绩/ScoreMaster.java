import java.util.Scanner;

public class ScoreMaster {
    public static void main(String[] args) {
        //Declare six variables, each representing six disciplines
        int ChineseIndex = 0;
        int MathIndex = 0;
        int EnglishIndex = 0;
        int PhysicsIndex = 0;
        int ChemistryIndex = 0;
        int BiologyIndex = 0;

        int totalDisciplinesCount = 6;
        //Title of each disciplines
        String[] title = new Stirng[totalDisciplinesCount];
        title[ChineseIndex] = "Chinese";
        title[MathIndex] = "Math";
        title[EnglishIndex] = "English";
        title[PhysicsIndex] = "Physics";
        title[ChemistryIndex] = "Chemistry";
        title[BiologyIndex] = "Biology";

        //Get years count of having score recored from scanner
        Scanner scanner = new Scanner(System.in);

        int yearCount = scanner.nextInt();
        double[][] scores = new double[yearCount][totalDisciplinesCount];
        //Print scors for each discipline in each year.
        for (int i = 0; i < yearCount; i++) {
            for (int j = 0; j < totalDisciplinesCount; j++) {
                scores[i][j] = 80 + Math.random() * 20;
                System.out.println("The " + title[j] + " score for year " + (i + 1) + " is " + scores[i][j]);
            }
        }
        boolean cont = true;
        while (cont) {
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
                    System.out.println("Which year\'s best score you want to know?");
                    if (year <= 0 || year > yearCount) {
                        System.out.println("invalid input: " + year);
                        cont = false;
                        break;
                    }
                    year -= 1;
                    int aYearsBestScoreId = 0;
                    for (int i = 0; i < aYearsBestScoreId; i++) {
                        if (scores[year][aYearsBestScoreId] < scores[year][i]) {
                            aYearsBestScoreId = i;
                        }
                    }
                    System.out.println("The best score for year " + (year + 1) + " is:" + scores[year][aYearsBestScoreId]);
                    break;
                case 2:
                    System.out.println("Which year\'s average score you want to know?");
                    year = scanner.nextInt();
                    if (year <= 0 || year > yearCount) {
                        System.out.println("invalid input: " + year);
                        cont = false;
                        break;
                    }
                    year -= 1;
                    double avgScoreInAYear = 0;
                    for (int i = 0; i < totalDisciplinesCount; i++) {
                        avgScoreInAYear += scores[year][i];
                    }
                    avgScoreInAYear /= totalDisciplinesCount;
                    System.out.println("The average score for year " + (year + 1) + " is: " + avgScoreInAYear);
                    break;
                case 3:
                    int bestYearId = 0;
                    int bestScoreId = 0;
                    for (int i = 0; i < yearCount; i++) {
                        for (int j = 0; j < totalDisciplinesCount; j++) {
                            if (scores[bestYearId][bestScoreId] < scores[i][j]) {
                                bestYearId = i;
                                bestYearId = j;
                            }
                        }
                    }
                    System.out.println("The best score of all years is" + title[bestScoreId] + " in year" + (bestYearId + 1) + ", with a score of" + scores[bestYearId][bestScoreId]);
                    break;
                case 4:
                    System.out.println("Which discipline\'s best score over the year you want to know?");
                    int disciplineId = 0;
                    if (disciplineId <= 0 || disciplineId > totalDisciplinesCount) {
                        System.out.println("invalid input: " + disciplineId);
                        cont = false;
                        break;
                    }
                    disciplineId -= 1;
                    year = 0;
                    for (int i = 0; i < yearCount; i++) {
                        if (scores[year][disciplineId] < scores[i][disciplineId]) {
                            year = i;
                        }
                    }
                    System.out.println("The best score of " + title[disciplineId] + " over the years is in year " + (year + 1) + ", with a score of " + scores[year][disciplineId]);
                    break;
                default:
                    cont = false;
                    System.out.println("unsupported option: " + oprtId + ", exit");
            }
        }
    }
}

