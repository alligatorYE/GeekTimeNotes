import java.util.Scanner;
public class ScoreMaster{
    public static void main(String[] args) {
        //Declare six variables, each representing six disciplines
        int ChineseIndex = 0;
        int MathIndex  = 0;
        int EnglishIndex = 0;
        int PhysicsIndex = 0;
        int ChemistryIndex = 0;
        int BiologyIndex = 0;

        int totalScoreCount = 6;
        //Title of each disciplines
        String[] title =new Stirng[totalScoreCount];
        title[ChineseIndex] = "Chinese";
        title[MathIndex] = "Math";
        title[EnglishIndex] = "English";
        title[PhysicsIndex] = "Physics";
        title[ChemistryIndex] = "Chemistry";
        title[BiologyIndex] = "Biology";

        //Get years count of having score recored from scanner
        Scanner scanner = new  Scanner(System.in);

        int yearCount =scanner.nextInt();
        double[][] score = new double[yearCount][totalScoreCount];
        //Print scors for each discipline in each year.
        for (int i = 0; i < yearCount; i++) {
            for (int j = 0; j < totalScoreCount; j++) {
                score[i][j] = 80 + Math.random() * 20;
                System.out.println("The " + title[j] + " score for year " + (i + 1) + " is " + score[i][j]);
            }
        }

        while (cont){
            System.out.println("Please select your operation below");
            System.out.println(
                    "1: Seeking the best score of a year\n" +
                    "2: Find the average score for a year\n" +
                    "3: Seeking the best score of all year\n" +
                    "4: Seeking the best score in a discipline over the years"
            );
            int oprtId = scanner.nextInt();
            int year = 0;
            switch (oprtId){
                case 1:
                    System.out.println("Which year\'s best score you want to know?");
                    break;
                case 2:
                    System.out.println("Which year\'s average score you want to know?");
                    break;
                case 3:
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("Which discipline\'s best score over the year you want to know?");
                    break;
                default:
                    break;
            }
        }
    }
}