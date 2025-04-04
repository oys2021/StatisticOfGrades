import java.util.Scanner;
public class Main{
    public static void main(String arr[]){

        Scanner scanner =new Scanner(System.in);

        System.out.println("Please can you provide the scores. Provide all in separate space :");

        // the inputs for grades
        String inputs =scanner.nextLine();

        String inputValues [] = inputs.split(" ");

        int scores [] = new int[inputValues.length];

        for ( int i=0; i < inputValues.length ; i++){
            scores[i] = Integer.parseInt(inputValues[i]);
        }

        GradeStatistics gradeStats = new GradeStatistics(scores);

        // expected output

        System.out.println("Values:");
        System.out.println("The maximum grade is "+" "+ gradeStats.getMaximumGrade());
        System.out.println("The minimum grade is "+" " + gradeStats.getMinimumGrade());
        System.out.println("The average grade is " + String.format("%.6f", gradeStats.getAverageGrade()));

        System.out.println("\n");
       System.out.println("Graph:");
       System.out.println("\n");

       int [] stats = gradeStats.getGradeDistributions();

       gradeStats.barGraph(stats);

       scanner.close();


    }

}


class GradeStatistics {
    private int [] arr ;


    public GradeStatistics(int [] arr){
        this.arr = arr;
    }

    //the maximum grade
    public int getMaximumGrade(){
        int max = arr[0];

        for(int i=1 ; i < arr.length; i++){
            if ( arr[i] >= max){
                max = arr[i];
            }
        }
        return max;
    }

    // the minimum grade
    public int getMinimumGrade(){
        int min = arr[0];

        for(int i=1 ; i < arr.length; i++){
            if ( arr[i] <= min){
                min = arr[i];
            }
        }
        return min;
    }

    // the average grade
    public float getAverageGrade(){
        int totalSum = 0;

        for ( int i=0 ; i < arr.length ; i++) {
            totalSum += arr[i];
        }

        return  (float)  totalSum / arr.length;

    }

   // distributions for grades
    public int []  getGradeDistributions( ){

        int [] stats = new int[5];

        for (int i=0 ; i < arr.length ; i++){

            if ( arr[i] >= 0 && arr[i] <= 20){
                stats[0] += 1;
            }

            if ( arr[i] >= 21 && arr[i] <= 40){
                stats[1] += 1;
            }

            if ( arr[i] >= 41 && arr[i] <= 60){
                stats[2] += 1;
            }
            if ( arr[i] >= 61 && arr[i] <= 80){
                stats[3] += 1;
            }

            if ( arr[i] > 80){
                stats[4] += 1;
            }

        }

        return  stats;
    }



    public void barGraph(int[] stats) {
        // count for the maximum number of rows in the graph
        int max = 0;
        for (int count : stats) {
            if (count > max) {
                max = count;
            }
        }

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

        // bottom label with ranges
        System.out.print("      +-----------+---------+---------+---------+---------+\n");
        System.out.print("      I  0-20   I  21-40  I  41-60  I  61-80  I  81-100 I\n");
    }



}