import java.util.Scanner;

public class Main{
    public static void main(String arr[]){

        Scanner scanner = new Scanner(System.in);

        // Prompt user for the number of students
        System.out.print("Please insert the number of Students grades :");
        int N = scanner.nextInt();
        
        int scores [] = new int [N];
        
        // Prompt user to input grades
        System.out.println("Enter the grades for " + N + " students, separated by spaces:");
        
        for (int i = 0 ; i < N ; i++){
            scores[i]=scanner.nextInt();
        }

        // Create GradeStatistics object
        GradeStatistics gradeStats = new GradeStatistics(scores);

        // Output values: max, min, and average grade
        System.out.println("Values:");
        System.out.println("The maximum grade is "+" "+ gradeStats.getMaximumGrade());
        System.out.println("The minimum grade is "+" " + gradeStats.getMinimumGrade());
        System.out.println("The average grade is " + String.format("%.6f", gradeStats.getAverageGrade()));

        System.out.println("\n");
        System.out.println("Graph:");
        System.out.println("\n");

        // Get grade distributions
        int [] stats = gradeStats.getGradeDistributions();

        // Print bar graph for grade distributions
        gradeStats.barGraph(stats);

        scanner.close();
    }
}

class GradeStatistics {
    private int [] arr;  // Array to store student grades

    public GradeStatistics(int [] arr){
        this.arr = arr;
    }

    // Get the maximum grade
    public int getMaximumGrade(){
        int max = arr[0];

        for(int i = 1 ; i < arr.length; i++){
            if (arr[i] >= max){
                max = arr[i];
            }
        }
        return max;
    }

    // Get the minimum grade
    public int getMinimumGrade(){
        int min = arr[0];

        for(int i = 1 ; i < arr.length; i++){
            if (arr[i] <= min){
                min = arr[i];
            }
        }
        return min;
    }

    // Get the average grade
    public float getAverageGrade(){
        int totalSum = 0;

        for (int i = 0 ; i < arr.length ; i++) {
            totalSum += arr[i];
        }

        return  (float)  totalSum / arr.length;
    }

    // Get the grade distribution (ranges: 0-20, 21-40, 41-60, 61-80, 81-100)
    public int [] getGradeDistributions(){
        int [] stats = new int[5];

        for (int i = 0 ; i < arr.length ; i++){
            if (arr[i] >= 0 && arr[i] <= 20) stats[0] += 1;
            if (arr[i] >= 21 && arr[i] <= 40) stats[1] += 1;
            if (arr[i] >= 41 && arr[i] <= 60) stats[2] += 1;
            if (arr[i] >= 61 && arr[i] <= 80) stats[3] += 1;
            if (arr[i] > 80) stats[4] += 1;
        }

        return stats;
    }

    // Print the bar graph for grade distributions
    public void barGraph(int[] stats) {
        // Find the highest number of students in any range for graph scaling
        int max = 0;
        for (int count : stats) {
            if (count > max) {
                max = count;
            }
        }

        // Print the graph from the highest to lowest count
        for (int row = max; row > 0; row--) {
            System.out.print("   " + row + " > ");
            for (int i = 0; i < stats.length; i++) {
                if (stats[i] >= row) {
                    System.out.print("#######   ");
                } else {
                    System.out.print("          ");
                }
            }
            System.out.println();
        }

        // Print the bottom labels with grade ranges
        System.out.print("      +-----------+---------+---------+---------+---------+\n");
        System.out.print("      I  0-20   I  21-40  I  41-60  I  61-80  I  81-100 I\n");
    }
}
